package be.kyanvde;

import be.kyanvde.auth.BuildTeamAuthSession;
import be.kyanvde.model.*;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

class BTEWebsiteAPITest {

    @Test
    void healthCheckReturnsCorrectly() {
        HealthCheck healthCheck = BTEWebsiteAPI.healthCheck();
        System.out.println(healthCheck);
        assertThat(healthCheck).isNotNull();
    }

    @Test
    void healthCheckHasCorrectValuesAssumingAPIIsFullyOperational() {
        HealthCheck healthCheck = BTEWebsiteAPI.healthCheck();

        assertThat(healthCheck.statusCode()).isEqualTo(200);
        assertThat(healthCheck.statusMessage()).isEqualTo("OK");
    }

    @Test
    void accountCanBeRetrievedFromAPI() {
        Account account = BTEWebsiteAPI.getAccount(new BuildTeamAuthSession("eyJhbGciOiJSUzI1NiIsInR5cCIgOiAiSldUIiwia2lkIiA6ICJrVTNJazdCYk8xUUFsdzZseWZmVVZJUUs5ZEVaUDhSTHZSY1kwOFMzYmcwIn0.eyJleHAiOjE3MzQ2MjEzNTAsImlhdCI6MTcyNDI1MzM1MCwianRpIjoiMmQzYjE5MjQtMmI2YS00YjQ1LTk5MzktMDdjOWVhMTVmNGQ0IiwiaXNzIjoiaHR0cHM6Ly9hdXRoLmJ1aWxkdGhlZWFydGgubmV0L3JlYWxtcy93ZWJzaXRlIiwiYXVkIjoiYWNjb3VudCIsInN1YiI6IjcwMDNkNDMyLTAzMTctNDQ0MC04MWQ4LWQ0MGJjYTE0MWE1MyIsInR5cCI6IkJlYXJlciIsImF6cCI6ImJhY2tlbmQiLCJzZXNzaW9uX3N0YXRlIjoiNDE4ZGJhOTgtYTY2YS00NGRkLTk1NjctOTJiOTI1NDEyZGU0IiwicmVhbG1fYWNjZXNzIjp7InJvbGVzIjpbImJ0ZV9zdGFmZiIsIm9mZmxpbmVfYWNjZXNzIiwiZGVmYXVsdC1yb2xlcy1idGUgZGV2IHRlYW0iLCJ1bWFfYXV0aG9yaXphdGlvbiJdfSwicmVzb3VyY2VfYWNjZXNzIjp7ImFjY291bnQiOnsicm9sZXMiOlsibWFuYWdlLWFjY291bnQiLCJtYW5hZ2UtYWNjb3VudC1saW5rcyIsInZpZXctcHJvZmlsZSJdfX0sInNjb3BlIjoiZW1haWwgbWluZWNyYWZ0IHJvbGVzIHByb2ZpbGUiLCJzaWQiOiI0MThkYmE5OC1hNjZhLTQ0ZGQtOTU2Ny05MmI5MjU0MTJkZTQiLCJtaW5lY3JhZnQiOiJmcmlrYW5kZWx3b3JzdCIsImVtYWlsX3ZlcmlmaWVkIjp0cnVlLCJuYW1lIjoiS3lhbiBWYW4gZGVuIEV5bmRlIiwibWluZWNyYWZ0X3ZlcmlmaWVkIjpmYWxzZSwicHJlZmVycmVkX3VzZXJuYW1lIjoiZnJpa2FuZGVsd29yc3QiLCJnaXZlbl9uYW1lIjoiS3lhbiIsImZhbWlseV9uYW1lIjoiVmFuIGRlbiBFeW5kZSIsImVtYWlsIjoia3lhbnZkZUBpY2xvdWQuY29tIn0.PC5-iVgA6f6VjNPXckHWQt5Gi2Zyg-r-pEvGfQ4swMhkhpjFwjICzrew5wD9KTIKuaHVSYwls6-q8pFlDAMZ550VF9yugXdiefXmiF7HzNGMlu-hio_XDknpEwLYk5zfKkPAFDTo2IGEYOTcaUhtozaur_qIFWhfXNlgFtDuaSDWUDVjFLpSS6Scw6eRTs_N2HKpQtvEeJB_p__lQ9a9WGb1f-2CUL2mT_FAznd1nBlGAUtt4MVaaoY-kpkkQxTbq7fkz9LpNzhTfrag7aDFPTPqqBwnSvIgZ2vJCywbnUQIBjF_9Eu_NEMnwCEoqAiO1sJJrmxBhIZZkZcOOsb6jg")).getBody();
        assertThat(account.id()).isNotNull();
    }

    @Test
    void canRetrieveClaim() {
        Claim claim = BTEWebsiteAPI.getClaim(
                new BuildTeamAuthSession("7ebfadbe0440e58781f051f731da2e79a1d610189c"),
                UUID.fromString("8cb65869-e5e0-45da-b214-eb561eae35dc"),
                UUID.fromString("5517512b-f127-48bf-8c06-b360d4046663")
        ).getBody();

        assertThat(claim.buildings()).isEqualTo(353);

    }

    @Test
    void canCreateClaim() {
        ResponseEntity<String> responseEntity = BTEWebsiteAPI.createClaim(
                new BuildTeamAuthSession("7ebfadbe0440e58781f051f731da2e79a1d610189c"),
                new MinimalClaim(
                        List.of("51.05546321348403, 4.695392804279988", "51.066617271761096, 4.695824910939413", "51.06643467378005, 4.724837760552459", "51.054825216533224, 4.723527781848434"),
                        true,
                        false,
                        "Test claim",
                        "Test claim from java wrapper.",
                        "Urk",
                        List.of(UUID.fromString("b4d15976-43e3-4792-8db2-31823746aba1"))
                ),
                UUID.fromString("8cb65869-e5e0-45da-b214-eb561eae35dc"),
                CoordType.STRING_ARRAY_REVERSE
        );
        assertThat(responseEntity.getStatusCode()).isEqualTo(200);
    }

    @Test
    void canSearchUser() {
        ResponseEntity<User> response = BTEWebsiteAPI.searchUser("frikandelworst");

        assertThat(response.getStatusCode()).isEqualTo(200);
        assertThat(response.getBody()).isNotNull();
    }

}