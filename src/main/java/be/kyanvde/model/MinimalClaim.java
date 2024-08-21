package be.kyanvde.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.util.List;
import java.util.UUID;

/**
 * @author kyanv
 * @file MinimalClaim.java
 * @description
 * @created 21/08/2024
 */

public record MinimalClaim(
    List<String> area,
    boolean active,
    boolean finished,
    String name,
    String description,
    String city,
    @JsonSerialize(using = UUIDListSerializer.class) @JsonDeserialize(using = UUIDListDeserializer.class) List<UUID> builders
) {}
