package org.artyomlbch.beangraphvisualizer.visualizer.core.filter.impl;

import org.artyomlbch.beangraphvisualizer.visualizer.core.filter.Filter;
import org.artyomlbch.beangraphvisualizer.visualizer.model.BeanGraph;
import org.artyomlbch.beangraphvisualizer.visualizer.model.BeanNode;
import org.artyomlbch.beangraphvisualizer.visualizer.model.BeanScope;
import org.artyomlbch.beangraphvisualizer.visualizer.model.filter.ScopeType;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class BeanScopeGraphFilter implements Filter {

    private final ScopeType scopeType;

    public BeanScopeGraphFilter(ScopeType scopeType) {
        this.scopeType = scopeType;
    }

    @Override
    public BeanGraph apply(BeanGraph originalGraph) {
        BeanGraph filteredGraph = new BeanGraph();

        // 2. Определяем целевую область видимости (SINGLETON или PROTOTYPE)
        BeanScope targetScope = (this.scopeType == ScopeType.SINGLETON_ONLY)
                ? BeanScope.SINGLETON
                : BeanScope.PROTOTYPE;

        // 3. Отбираем связанные узлы
        List<BeanNode> filteredNodes = originalGraph.getNodes().stream()
                .filter(node -> node.scope() == targetScope)
                .toList();
        filteredNodes.forEach(filteredGraph::addNode);

        // 4. Отбираем изолированные узлы
        originalGraph.getSoloNodes().stream()
                .filter(node -> node.scope() == targetScope)
                .forEach(filteredGraph::addSoloNode);

        // 5. Собираем ID всех разрешенных узлов
        Set<String> allowedIds = filteredNodes.stream()
                .map(BeanNode::id)
                .collect(Collectors.toSet());

        // 6. Переносим только те связи (ребра), где И источник, И цель остались в графе
        originalGraph.getEdges().stream()
                .filter(edge -> allowedIds.contains(edge.source().id()) &&
                        allowedIds.contains(edge.target().id()))
                .forEach(filteredGraph::addEdge);

        return filteredGraph;
    }
}