package org.artyomlbch.beangraphvisualizer.visualizer.core.filter.impl;

import org.artyomlbch.beangraphvisualizer.visualizer.core.filter.Filter;
import org.artyomlbch.beangraphvisualizer.visualizer.model.BeanEdge;
import org.artyomlbch.beangraphvisualizer.visualizer.model.BeanGraph;
import org.artyomlbch.beangraphvisualizer.visualizer.model.BeanNode;
import org.artyomlbch.beangraphvisualizer.visualizer.model.filter.Stereotype;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class BeanStereotypeGraphFilter implements Filter {

    private final Stereotype stereotype;

    public BeanStereotypeGraphFilter(Stereotype stereotype) {
        this.stereotype = stereotype;
    }

    @Override
    public BeanGraph apply(BeanGraph originalGraph) {
        BeanGraph filteredGraph = new BeanGraph();

        List<BeanNode> primaryNodes = originalGraph.getNodes().stream()
                .filter(node -> node.stereotype() == this.stereotype)
                .toList();
        primaryNodes.forEach(filteredGraph::addNode);

        Set<String> primaryIds = primaryNodes.stream()
                .map(BeanNode::id)
                .collect(Collectors.toSet());

        List<BeanEdge> outboundEdges = originalGraph.getEdges().stream()
                .filter(edge -> primaryIds.contains(edge.source().id()))
                .toList();
        outboundEdges.forEach(filteredGraph::addEdge);

        outboundEdges.stream()
                .map(BeanEdge::target)
                .filter(targetNode -> !primaryIds.contains(targetNode.id()))
                .forEach(filteredGraph::addNode);

        originalGraph.getSoloNodes().stream()
                .filter(node -> node.stereotype() == this.stereotype)
                .forEach(filteredGraph::addSoloNode);

        return filteredGraph;
    }
}
