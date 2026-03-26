package org.artyomlbch.beangrapvisualizer.visualizer.core.cache;

import org.artyomlbch.beangrapvisualizer.visualizer.model.BeanGraph;
import org.springframework.stereotype.Component;

@Component
public class BeanGraphCache {
    private BeanGraph cachedGraph;

    public void put(BeanGraph graph) {
        this.cachedGraph = graph;
    }

    public BeanGraph get() {
        if (cachedGraph == null) {
            throw new IllegalStateException("Graph is not initialized yet.");
        }
        return cachedGraph;
    }
}
