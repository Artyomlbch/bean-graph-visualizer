package org.artyomlbch.beangrapvisualizer.visualizer.core.repository.impl;

import org.artyomlbch.beangrapvisualizer.visualizer.core.repository.BeanGraphRepository;
import org.artyomlbch.beangrapvisualizer.visualizer.core.storage.InMemoryBeanGraphStorage;
import org.artyomlbch.beangrapvisualizer.visualizer.model.BeanGraph;
import org.springframework.stereotype.Repository;

@Repository
public class InMemoryBeanGraphRepository implements BeanGraphRepository {

    private volatile BeanGraph cachedGraph;

    @Override
    public void write(BeanGraph graph) {
        this.cachedGraph = graph;
    }

    @Override
    public BeanGraph getGraph() {
        if (this.cachedGraph == null) {
            throw new IllegalStateException("BeanGraph is not initialized yet!");
        }
        return this.cachedGraph;
    }

}
