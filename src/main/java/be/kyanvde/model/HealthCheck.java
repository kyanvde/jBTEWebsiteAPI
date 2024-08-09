package be.kyanvde.model;

/**
 * @author kyanv
 * @file HealthCheck.java
 * @description A record representation of the healthCheck returned by the website API.
 * @created 9/08/2024
 */

public record HealthCheck(int statusCode, String statusMessage) {
}
