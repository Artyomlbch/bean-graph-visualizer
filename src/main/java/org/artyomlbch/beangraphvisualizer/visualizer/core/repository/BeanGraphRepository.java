package org.artyomlbch.beangrapvisualizer.visualizer.core.repository;

import org.artyomlbch.beangrapvisualizer.visualizer.model.BeanGraph;

public interface BeanGraphRepository {
    void write(BeanGraph graph);
    BeanGraph getGraph();
}
