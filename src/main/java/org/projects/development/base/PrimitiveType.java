package org.projects.development.base;

public abstract class PrimitiveType implements Type {
    public boolean isPrimitive() {
        return true;
    }

    public abstract Object parse(String value) throws NumberFormatException;
}
