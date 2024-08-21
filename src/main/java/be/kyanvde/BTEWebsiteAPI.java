package be.kyanvde;

import be.kyanvde.auth.AuthSession;
import be.kyanvde.exception.BTEWebsiteAPIException;
import be.kyanvde.model.*;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

public class BTEWebsiteAPI {

    private final static HttpClient httpClient = HttpClient.newHttpClient();
    private final static String baseURL = "https://api.buildtheearth.net/api/v1";
    private final static ObjectMapper mapper = new ObjectMapper();

    /**
     *
     * @return A HealthCheck wrapper object containing the status code and status message.
     */
    public static HealthCheck healthCheck() {
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(baseURL.concat("/healthcheck")))
                    .GET()
                    .build();

            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

            String responseBody = response.body();
            int statusCode = response.statusCode();

            ObjectMapper mapper = new ObjectMapper();
            JsonNode jsonNode = mapper.readTree(responseBody);
            String statusMessage = jsonNode.get("message").asText();

            return new HealthCheck(statusCode, statusMessage);
        } catch (IOException | InterruptedException e) {
            throw new BTEWebsiteAPIException("Something went wrong while calling a GET request on /healthcheck");
        }
    }

    public static ResponseEntity<Account> getAccount(AuthSession authSession) {
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(baseURL.concat("/account")))
                    .setHeader("Authorization", "Bearer " + authSession.getToken())
                    .GET()
                    .build();

            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

            String responseBody = response.body();
            int statusCode = response.statusCode();

            return new ResponseEntity<>(statusCode, mapper.readValue(responseBody, Account.class));
        } catch (IOException | InterruptedException e) {
            throw new BTEWebsiteAPIException(e.getMessage());
        }
    }

    public static ResponseEntity<Claim> getClaim(AuthSession authSession, UUID buildTeamId, UUID claimId) {
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(baseURL.concat("/public/buildteams/"+buildTeamId+"/claims/"+claimId)))
                    .setHeader("Authorization", "Bearer " + authSession.getToken())
                    .GET()
                    .build();

            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

            String responseBody = response.body();
            int statusCode = response.statusCode();

            return new ResponseEntity<>(statusCode, mapper.readValue(response.body(), Claim.class));
        } catch (IOException | InterruptedException e) {
            throw new BTEWebsiteAPIException(e.getMessage());
        }
    }

    public static ResponseEntity<String> createClaim(AuthSession authSession, MinimalClaim claim, UUID buildTeamId, CoordType coordType) {
        try {
            // Convert the Claim object to JSON
            ObjectMapper mapper = new ObjectMapper();
            String claimJson = mapper.writeValueAsString(claim);

            // Build the HTTP request
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(String.format("%s/public/buildteams/%s/claims?coordType=%s", baseURL, buildTeamId, coordType.getLowercase())))
                    .setHeader("Authorization", "Bearer " + authSession.getToken())
                    .setHeader("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(claimJson))
                    .build();

            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            return ResponseEntity.of(response.statusCode(), response.body());
        } catch (IOException | InterruptedException e) {
            throw new BTEWebsiteAPIException("Error creating claim: " + e.getMessage());
        }
    }

    public static ResponseEntity<User> searchUser(String minecraftName) {
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(baseURL.concat("/users/search?minecraft="+minecraftName)))
                    .GET()
                    .build();

            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

            String responseBody = response.body();
            int statusCode = response.statusCode();
            return ResponseEntity.of(statusCode, Arrays.stream(mapper.readValue(responseBody, User[].class)).findFirst().get());
        } catch (IOException | InterruptedException e) {
            throw new BTEWebsiteAPIException(e.getMessage());
        }
    }


}