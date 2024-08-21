package be.kyanvde.auth;

/**
 * @author kyanv
 * @file AuthenticationToken.java
 * @description
 * @created 21/08/2024
 */

public record AuthenticationToken(String token) {

    @Override
    public String toString() {
        return token();
    }
}
