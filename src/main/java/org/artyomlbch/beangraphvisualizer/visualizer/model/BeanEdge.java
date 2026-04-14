package org.artyomlbch.beangrapvisualizer.visualizer.model;

public class BeanEdge {
    private BeanNode source;
    private BeanNode target;
    private InjectionType injectionType;

    public BeanEdge(BeanNode source, BeanNode target, InjectionType injectionType) {
        this.injectionType = injectionType;
        this.target = target;
        this.source = source;
    }

    public BeanNode getSource() { return source; }
    public void setSource(BeanNode source) { this.source = source; }

    public BeanNode getTarget() { return target; }
    public void setTarget(BeanNode target) { this.target = target; }

    public InjectionType getInjectionType() { return injectionType; }
    public void setInjectionType(InjectionType injectionType) { this.injectionType = injectionType; }
}
