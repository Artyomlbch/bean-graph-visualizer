package org.artyomlbch.beangraphvisualizer.visualizer.core.serializer;

import org.artyomlbch.beangraphvisualizer.visualizer.model.BeanGraph;

public interface GraphSerializer {
    String serialize(BeanGraph graph);
    String getFormat();
}
