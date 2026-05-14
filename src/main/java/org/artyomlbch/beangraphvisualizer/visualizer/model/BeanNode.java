package org.artyomlbch.beangraphvisualizer.visualizer.model;

import org.artyomlbch.beangraphvisualizer.visualizer.model.filter.Stereotype;

public record BeanNode(
        String id,
        String fullClassName,
        BeanScope scope,
        Boolean isSystem,
        Stereotype stereotype
) {
}
