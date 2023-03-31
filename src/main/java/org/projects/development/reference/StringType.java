package org.projects.development.reference;

import org.projects.development.base.ReferenceType;

public class StringType implements ReferenceType {
    public String getName() {
        return "String";
    }

    public boolean isReference() {
        return true;
    }
}