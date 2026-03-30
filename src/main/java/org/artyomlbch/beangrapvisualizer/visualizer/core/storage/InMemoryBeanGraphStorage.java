package org.artyomlbch.beangrapvisualizer.visualizer.core.storage;

import org.artyomlbch.beangrapvisualizer.visualizer.model.BeanGraph;
import org.springframework.stereotype.Component;

@Component
public class InMemoryBeanGraphStorage {

    private BeanGraph beanGraph;

    public void save(BeanGraph beanGraph) {
        this.beanGraph = beanGraph;
    }

    public BeanGraph getBeanGraph() {
        return beanGraph;
    }

}
