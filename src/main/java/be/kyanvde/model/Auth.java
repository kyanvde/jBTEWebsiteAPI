package be.kyanvde.model;

/**
 * @author kyanv
 * @file Auth.java
 * @description
 * @created 21/08/2024
 */

public record Auth(
        Exp exp,
        Iat iat
) {}

record Exp(
        long unix,
        String readable
) {}

record Iat(
        long unix,
        String readable
) {}