package org.projects.development.reference;

import lombok.SneakyThrows;
import org.projects.development.base.ReferenceType;

public class ClassType implements ReferenceType {
    private final String className;

    public ClassType(String className) {
        this.className = className;
    }

    public String getName() {
        return className;
    }

    public boolean isReference() {
        return true;
    }

    @SneakyThrows
    public Class<?> getClassObject() {
        return Class.forName(className);
    }
}
