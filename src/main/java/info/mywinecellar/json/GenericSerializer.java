/*
 * My-Wine-Cellar, copyright 2019
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 */

package info.mywinecellar.json;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

public class GenericSerializer<T> extends JsonSerializer<List<T>> {

    protected GenericSerializer() {
        super();
    }

    @Override
    public void serialize(List<T> value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        gen.writeStartArray();
        value.forEach(dto -> {
            try {
                gen.writeStartObject();

                String fieldName = Optional.of(dto.getClass().getSimpleName().toLowerCase())
                        .filter(name -> name.length() != 0)
                        .filter(name -> name.endsWith("dto"))
                        .map(name -> name.substring(0, name.length() - 3))
                        .orElse(dto.getClass().getSimpleName().toLowerCase());

                gen.writeObjectField(fieldName, dto);
                gen.writeEndObject();
            } catch (IOException e) {
                System.out.println(e.getMessage());
                e.printStackTrace(System.out);
            }
        });
        gen.writeEndArray();
    }
}
