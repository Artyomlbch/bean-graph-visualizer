package org.artyomlbch.beangraphvisualizer.visualizer.core.repository;

import org.artyomlbch.beangraphvisualizer.visualizer.model.BeanGraph;

public interface GraphRepository {
    void write(BeanGraph graph);
    BeanGraph getGraph();
}
