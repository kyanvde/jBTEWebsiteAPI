package be.kyanvde.model;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

/**
 * @author kyanv
 * @file UUIDListSerializer.java
 * @description
 * @created 21/08/2024
 */

public class UUIDListSerializer extends JsonSerializer<List<UUID>> {

    @Override
    public void serialize(List<UUID> value, JsonGenerator gen, SerializerProvider serializers)
            throws IOException {
        gen.writeStartArray();
        for (UUID uuid : value) {
            gen.writeStartObject();
            gen.writeStringField("id", uuid.toString());
            gen.writeEndObject();
        }
        gen.writeEndArray();
    }
}
