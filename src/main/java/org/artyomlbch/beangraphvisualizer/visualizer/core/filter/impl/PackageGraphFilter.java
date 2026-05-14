package org.artyomlbch.beangraphvisualizer.visualizer.core.filter.impl;

import org.artyomlbch.beangraphvisualizer.visualizer.core.filter.Filter;
import org.artyomlbch.beangraphvisualizer.visualizer.model.*;

import java.util.Set;
import java.util.stream.Collectors;

public class PackageGraphFilter implements Filter {

    private final String packageName;

    public PackageGraphFilter(String packageName) {
        this.packageName = packageName;
    }

    @Override
    public BeanGraph apply(BeanGraph originalGraph) {
        BeanGraph filteredGraph = new BeanGraph();

        originalGraph.getNodes().stream()
                .filter(node -> node.fullClassName().startsWith(packageName))
                .forEach(filteredGraph::addNode);

        originalGraph.getSoloNodes().stream()
                .filter(node -> node.fullClassName().startsWith(packageName))
                .forEach(filteredGraph::addSoloNode);

        Set<String> allowedDependenciesIds = filteredGraph.getNodes().stream().map(BeanNode::id).collect(Collectors.toSet());
        originalGraph.getEdges().stream()
                .filter(edge -> allowedDependenciesIds.contains(edge.source().id()))
                .forEach(filteredGraph::addEdge);

        return filteredGraph;
    }
}