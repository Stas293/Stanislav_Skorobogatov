package org.projects.development.reference;

import lombok.SneakyThrows;
import org.projects.development.base.ReferenceType;

public class ObjectType implements ReferenceType {
    private final String className;

    public ObjectType(String className) {
        this.className = className;
    }

    public String getName() {
        return className;
    }

    public boolean isReference() {
        return true;
    }

    @SneakyThrows
    public Object newInstance() {
        Class<?> clazz = Class.forName(className);
        return clazz.getDeclaredConstructor().newInstance();
    }
}

