package org.artyomlbch.beangrapvisualizer.visualizer.core.filter.impl;

import org.artyomlbch.beangrapvisualizer.visualizer.core.filter.BeansGraphFilter;
import org.artyomlbch.beangrapvisualizer.visualizer.core.filter.criteria.GraphFilterCriteria;
import org.artyomlbch.beangrapvisualizer.visualizer.model.BeanGraph;
import org.artyomlbch.beangrapvisualizer.visualizer.model.BeanNode;
import org.artyomlbch.beangrapvisualizer.visualizer.model.GraphType;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class TypeGraphFilter implements BeansGraphFilter {
    @Override
    public boolean isApplicable(GraphFilterCriteria criteria) {
        return criteria.graphType() != null && criteria.graphType() != GraphType.ALL;
    }

    @Override
    public BeanGraph apply(BeanGraph originalGraph, GraphFilterCriteria criteria) {
        BeanGraph filteredGraph = new BeanGraph();
        boolean filterSystem = (criteria.graphType() == GraphType.SYSTEM);

        List<BeanNode> filteredNodes = originalGraph.getNodes().stream()
                .filter(node -> node.isSystem() == filterSystem)
                .toList();

        filteredNodes.forEach(filteredGraph::addNode);

        Set<String> allowedIds = filteredNodes.stream().map(BeanNode::getId).collect(Collectors.toSet());

        originalGraph.getEdges().stream()
                .filter(edge -> allowedIds.contains(edge.getSource().getId()))
                .forEach(filteredGraph::addEdge);

        return filteredGraph;
    }
}
