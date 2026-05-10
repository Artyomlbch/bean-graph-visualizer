package org.artyomlbch.beangraphvisualizer.visualizer.core.serializer.impl;

import org.artyomlbch.beangraphvisualizer.visualizer.core.serializer.GraphSerializer;
import org.artyomlbch.beangraphvisualizer.visualizer.model.BeanGraph;
import org.springframework.stereotype.Component;
import tools.jackson.databind.ObjectMapper;

@Component
public class JsonGraphSerializer implements GraphSerializer {

    private final ObjectMapper objectMapper;

    public JsonGraphSerializer(ObjectMapper objectMapper) {
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

    @Override
    public String getFormat() {
        return "json";
    }
}
