package be.kyanvde.auth;

/**
 * @author kyanv
 * @file BuildTeamAuthSession.java
 * @description
 * @created 21/08/2024
 */

public class BuildTeamAuthSession implements AuthSession {

    private final String token;

    public BuildTeamAuthSession(String token) {
        this.token = token;
    }

    @Override
    public AuthenticationToken getToken() {
        return new AuthenticationToken(this.token);
    }

    @Override
    public boolean isExpired() {
        return false;
    }
}
