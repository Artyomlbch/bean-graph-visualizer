package org.artyomlbch.beangraphvisualizer.visualizer.model;

public record BeanNode(
        String id,
        String fullClassName,
        BeanScope scope,
        Boolean isSystem
) {
}
