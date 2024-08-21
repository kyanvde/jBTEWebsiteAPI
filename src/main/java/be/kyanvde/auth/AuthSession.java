package be.kyanvde.auth;

/**
 * @author kyanv
 * @file AuthSession.java
 * @description
 * @created 21/08/2024
 */

public interface AuthSession {

    AuthenticationToken getToken();
    boolean isExpired();
}
