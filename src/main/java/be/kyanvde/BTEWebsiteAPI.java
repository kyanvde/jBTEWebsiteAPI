package be.kyanvde;

import be.kyanvde.exception.BTEWebsiteAPIException;
import be.kyanvde.model.HealthCheck;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class BTEWebsiteAPI {

    private final static HttpClient httpClient = HttpClient.newHttpClient();
    private final static String baseURl = "https://api.buildtheearth.net/api/v1";


    /**
     *
     * @return A HealthCheck wrapper object containing the status code and status message.
     */
    public static HealthCheck healthCheck() {
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(baseURl.concat("/healthcheck")))
                    .GET()
                    .build();

            System.out.println(request.toString());

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
}