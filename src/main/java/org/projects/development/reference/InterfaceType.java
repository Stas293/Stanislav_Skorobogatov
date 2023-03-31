package org.projects.development.reference;

import org.projects.development.base.ReferenceType;

public class InterfaceType implements ReferenceType {
    private final String interfaceName;

    public InterfaceType(String interfaceName) {
        this.interfaceName = interfaceName;
    }

    public String getName() {
        return interfaceName;
    }

    public boolean isReference() {
        return true;
    }
}
