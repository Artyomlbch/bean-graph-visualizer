package org.artyomlbch.beangraphvisualizer.visualizer.core.serializer;

import org.artyomlbch.beangraphvisualizer.visualizer.model.BeanGraph;

public interface Serializer {
    String serialize(BeanGraph graph);
}
