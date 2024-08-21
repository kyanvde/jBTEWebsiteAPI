package be.kyanvde.model;

import java.util.UUID;

/**
 * @author kyanv
 * @file Permission.java
 * @description A record representation of a permission of the website API.
 * @created 21/08/2024
 */

public record Permission(
    UUID id,
    UUID userId,
    UUID buildTeamId,
    String permissionId,
    String permission,
    BuildTeam buildTeam,
    String buildTeamSlug
) {}

record BuildTeam(String slug) {}
