package com.belgium.cps.web.marushkai.entities;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by unlim_000 on 29.01.2017.
 */

public class Gizmo {

    private String field1;
    private String field2;
    private List<GizmoChild> children;


    public String getField1() {
        return field1;
    }

    public void setField1(String field1) {
        this.field1 = field1;
    }

    public String getField2() {
        return field2;
    }

    public void setField2(String field2) {
        this.field2 = field2;
    }

    public List<GizmoChild> getChildren() {
        if(children == null) {
            children = new ArrayList<GizmoChild>();
            children.add(new GizmoChild());
        }
        return children;
    }

    public void setChildren(List<GizmoChild> children) {
        this.children = children;
    }

}
