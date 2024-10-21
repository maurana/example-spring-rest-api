package com.crud.utils;

import java.io.IOException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
 
public class BooleanDeserializer extends JsonDeserializer<Boolean> {
 
    @Override
    public Boolean deserialize(JsonParser p, DeserializationContext ctx) throws IOException, JsonProcessingException {
        String val = p.getText().trim();
        if (!"true".equals(val) && !"false".equals(val)) {
            throw new JsonProcessingException("Invalid boolean value: " + val) {};
        }
        return Boolean.parseBoolean(val);
    }
}