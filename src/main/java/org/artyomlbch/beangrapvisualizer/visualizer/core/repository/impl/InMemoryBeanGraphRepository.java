package org.artyomlbch.beangrapvisualizer.visualizer.core.repository.impl;

import org.artyomlbch.beangrapvisualizer.visualizer.core.repository.BeanGraphRepository;
import org.artyomlbch.beangrapvisualizer.visualizer.core.storage.InMemoryBeanGraphStorage;
import org.artyomlbch.beangrapvisualizer.visualizer.model.BeanGraph;
import org.springframework.stereotype.Repository;

@Repository
public class InMemoryBeanGraphRepository implements BeanGraphRepository {

    private final InMemoryBeanGraphStorage graphStorage;

    public InMemoryBeanGraphRepository(InMemoryBeanGraphStorage graphStorage) {
        this.graphStorage = graphStorage;
    }

    @Override
    public void write(BeanGraph graph) {
        graphStorage.save(graph);
    }

    @Override
    public BeanGraph getGraph() {
        return graphStorage.getBeanGraph();
    }
}
