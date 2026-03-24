package org.artyomlbch.beangrapvisualizer.visualizer.core.filter.impl;

import org.artyomlbch.beangrapvisualizer.visualizer.core.filter.BeansGraphFilter;
import org.artyomlbch.beangrapvisualizer.visualizer.core.filter.criteria.GraphFilterCriteria;
import org.artyomlbch.beangrapvisualizer.visualizer.model.BeanGraph;
import org.artyomlbch.beangrapvisualizer.visualizer.model.GraphElementType;
import org.springframework.stereotype.Component;

@Component
public class ElementsGraphFilter implements BeansGraphFilter {
    @Override
    public boolean isApplicable(GraphFilterCriteria criteria) {
        return criteria.elementType() != null && criteria.elementType() != GraphElementType.ALL;
    }

    @Override
    public BeanGraph apply(BeanGraph originalGraph, GraphFilterCriteria criteria) {
        BeanGraph filteredGraph = new BeanGraph();

        if (criteria.elementType() == GraphElementType.NODES_ONLY) {
            originalGraph.getNodes().forEach(filteredGraph::addNode);

        } else if (criteria.elementType() == GraphElementType.EDGES_ONLY) {
            originalGraph.getEdges().forEach(filteredGraph::addEdge);
        }

        return filteredGraph;
    }
}
