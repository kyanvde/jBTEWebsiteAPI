package be.kyanvde.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.time.ZonedDateTime;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * @author kyanv
 * @file Claim.java
 * @description A record representation of a claim.
 * @created 21/08/2024
 */

public record Claim(
        UUID id,
        String externalId,
        UUID ownerId,
        List<String> area,
        String center,
        long size,
        int buildings,
        boolean active,
        boolean finished,
        String name,
        String description,
        String city,
        String osmName,
        UUID buildTeamId,
        Date createdAt,
        @JsonIgnore Object _count,
        @JsonIgnore Object owner,
        @JsonIgnore List<UUID> builders
) {}
