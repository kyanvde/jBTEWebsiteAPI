package be.kyanvde.model;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @author kyanv
 * @file UUIDListDeserializer.java
 * @description
 * @created 21/08/2024
 */

public class UUIDListDeserializer extends JsonDeserializer<List<UUID>> {

    @Override
    public List<UUID> deserialize(JsonParser p, DeserializationContext ctxt)
            throws IOException, JsonProcessingException {
        JsonNode node = p.getCodec().readTree(p);
        List<UUID> uuidList = new ArrayList<>();

        if (node.isArray()) {
            for (JsonNode item : node) {
                uuidList.add(UUID.fromString(item.get("id").asText()));
            }
        }

        return uuidList;
    }
}
