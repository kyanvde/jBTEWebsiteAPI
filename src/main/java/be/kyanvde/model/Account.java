package be.kyanvde.model;

import java.net.URL;
import java.util.List;
import java.util.UUID;

/**
 * @author kyanv
 * @file Account.java
 * @description A record representation of the account returned by the website API.
 * @created 11/08/2024
 */

public record Account(
        UUID id,
        UUID ssoId,
        long discordId,
        String username,
        String email,
        boolean emailVerified,
        URL avatar,
        Auth auth,
        List<Permission> permissions
) {}
