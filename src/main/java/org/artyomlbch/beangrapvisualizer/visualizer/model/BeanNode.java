package org.artyomlbch.beangrapvisualizer.visualizer.model;

public class BeanNode {
    private String id;
    private String label;
    private String fullClassName;
    private BeanScope scope;
    private Boolean isSystem;

    public BeanNode(String id, String label, String fullClassName, BeanScope scope, Boolean isSystem) {
        this.id = id;
        this.label = label;
        this.fullClassName = fullClassName;
        this.scope = scope;
        this.isSystem = isSystem;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getLabel() { return label; }
    public void setLabel(String label) { this.label = label; }

    public String getFullClassName() { return fullClassName; }
    public void setFullClassName(String fullClassName) { this.fullClassName = fullClassName; }

    public BeanScope getScope() { return scope; }
    public void setScope(BeanScope scope) { this.scope = scope; }

    public Boolean isSystem() { return isSystem; }
    public void setSystem(Boolean system) { isSystem = system; }
}
