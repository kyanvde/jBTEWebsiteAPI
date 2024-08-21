package be.kyanvde.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * @author kyanv
 * @file User.java
 * @description
 * @created 21/08/2024
 */

public record User(
        UUID id,
        UUID ssoId,
        long discordId,
        String username,
        URL avatar,
        @JsonIgnore Object _count,
        String minecraft,
        boolean minecraftVerified,
        Date createdAt,
        String discordName
) {}
