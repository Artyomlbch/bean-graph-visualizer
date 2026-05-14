package org.artyomlbch.beangraphvisualizer.visualizer.core.serializer.impl;

import org.artyomlbch.beangraphvisualizer.visualizer.core.serializer.Serializer;
import org.artyomlbch.beangraphvisualizer.visualizer.model.BeanGraph;
import org.springframework.stereotype.Component;
import tools.jackson.databind.ObjectMapper;

@Component
public class JsonSerializer implements Serializer {

    private final ObjectMapper objectMapper;

    public JsonSerializer(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public String serialize(BeanGraph graph) {
        try {
            return objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(graph);
        } catch (Exception e) {
            throw new RuntimeException("Failed to serialize graph to JSON", e);
        }
    }
}
