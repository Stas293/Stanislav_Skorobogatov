package org.projects.development.reference;

import org.projects.development.base.Type;
import org.projects.development.base.ReferenceType;

public class ArrayType implements ReferenceType {
    private final Type componentType;

    public ArrayType(Type componentType) {
        this.componentType = componentType;
    }

    public String getName() {
        return componentType.getName() + "[]";
    }

    public Type getComponentType() {
        return componentType;
    }

    public boolean isReference() {
        return true;
    }
}
