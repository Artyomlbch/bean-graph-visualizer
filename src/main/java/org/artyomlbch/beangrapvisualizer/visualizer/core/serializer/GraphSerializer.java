package org.artyomlbch.beangrapvisualizer.visualizer.core.serializer;

import org.artyomlbch.beangrapvisualizer.visualizer.model.BeanGraph;

public interface GraphSerializer {
    String serialize(BeanGraph graph);
    String getFormat();
}
