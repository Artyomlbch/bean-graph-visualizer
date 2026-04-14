package org.artyomlbch.beangrapvisualizer.visualizer.service;

import org.artyomlbch.beangrapvisualizer.visualizer.core.serializer.GraphSerializer;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SerializerService {

    private final List<GraphSerializer> serializers;

    public SerializerService(List<GraphSerializer> serializers) {
        this.serializers = serializers;
    }

    public GraphSerializer getGraphSerializer(String format) {
        return serializers.stream()
                .filter(s -> s.getFormat().equalsIgnoreCase(format))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Unsupported format: " + format));
    }
}
