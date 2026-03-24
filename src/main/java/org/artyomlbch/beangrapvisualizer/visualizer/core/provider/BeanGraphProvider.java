package org.artyomlbch.beangrapvisualizer.visualizer.core.provider;

import org.artyomlbch.beangrapvisualizer.visualizer.model.BeanGraph;
import org.springframework.stereotype.Component;

@Component
public class BeanGraphProvider {
    private BeanGraph cachedGraph;

    public void save(BeanGraph graph) {
        this.cachedGraph = graph;
    }

    public BeanGraph get() {
        if (cachedGraph == null) {
            throw new IllegalStateException("Graph is not initialized yet.");
        }
        return cachedGraph;
    }
}
