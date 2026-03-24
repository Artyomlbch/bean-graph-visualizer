package org.artyomlbch.beangrapvisualizer.visualizer.core.filter.impl;

import org.artyomlbch.beangrapvisualizer.visualizer.core.filter.BeansGraphFilter;
import org.artyomlbch.beangrapvisualizer.visualizer.core.filter.criteria.GraphFilterCriteria;
import org.artyomlbch.beangrapvisualizer.visualizer.model.*;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class PackageGraphFilter implements BeansGraphFilter {

    @Override
    public boolean isApplicable(GraphFilterCriteria criteria) {
        return criteria.packageName() != null && !criteria.packageName().isBlank();
    }

    @Override
    public BeanGraph apply(BeanGraph originalGraph, GraphFilterCriteria criteria) {
        BeanGraph filteredGraph = new BeanGraph();
        String pkg = criteria.packageName();

        List<BeanNode> filteredNodes = originalGraph.getNodes().stream()
                .filter(node -> node.getFullClassName().startsWith(pkg))
                .toList();

        filteredNodes.forEach(filteredGraph::addNode);

        Set<String> allowedIds = filteredNodes.stream().map(BeanNode::getId).collect(Collectors.toSet());

        originalGraph.getEdges().stream()
                .filter(edge -> allowedIds.contains(edge.getSource().getId()))
                .forEach(filteredGraph::addEdge);

        return filteredGraph;
    }
}