package org.artyomlbch.beangrapvisualizer.visualizer.core.filter.impl;

import org.artyomlbch.beangrapvisualizer.visualizer.core.filter.BeanGraphFilter;
import org.artyomlbch.beangrapvisualizer.visualizer.model.*;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class PackageGraphFilter implements BeanGraphFilter {

    private final String packageName;

    public PackageGraphFilter(String packageName) {
        this.packageName = packageName;
    }

    @Override
    public BeanGraph apply(BeanGraph originalGraph) {
        BeanGraph filteredGraph = new BeanGraph();

        List<BeanNode> filteredNodes = originalGraph.getNodes().stream()
                .filter(node -> node.getFullClassName().startsWith(packageName))
                .toList();

        filteredNodes.forEach(filteredGraph::addNode);

        Set<String> allowedIds = filteredNodes.stream().map(BeanNode::getId).collect(Collectors.toSet());

        originalGraph.getEdges().stream()
                .filter(edge -> allowedIds.contains(edge.getSource().getId()))
                .forEach(filteredGraph::addEdge);

        return filteredGraph;
    }
}