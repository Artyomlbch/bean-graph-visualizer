package org.artyomlbch.beangraphvisualizer.visualizer.model;

public record BeanEdge(
        BeanNode source,
        BeanNode target,
        InjectionType injectionType
) {
}
