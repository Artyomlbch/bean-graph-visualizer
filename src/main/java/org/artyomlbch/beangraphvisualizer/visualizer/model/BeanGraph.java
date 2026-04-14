package org.artyomlbch.beangrapvisualizer.visualizer.model;

import java.util.ArrayList;
import java.util.List;

public class BeanGraph {
    private final List<BeanNode> nodes = new ArrayList<>();
    private final List<BeanNode> soloNodes = new ArrayList<>();

    private final List<BeanEdge> edges = new ArrayList<>();

    public void addNode(BeanNode node) { this.nodes.add(node); }
    public void addSoloNode(BeanNode node) { this.soloNodes.add(node); }

    public void addEdge(BeanEdge edge) { this.edges.add(edge); }

    public List<BeanNode> getNodes() {
        return nodes;
    }
    public List<BeanNode> getSoloNodes() { return soloNodes; }

    public List<BeanEdge> getEdges() {
        return edges;
    }
}
