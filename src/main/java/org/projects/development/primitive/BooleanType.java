package org.projects.development.primitive;

import org.projects.development.base.PrimitiveType;

public class BooleanType extends PrimitiveType {
    public String getName() {
        return "boolean";
    }

    public Object parse(String value) throws NumberFormatException {
        return Boolean.parseBoolean(value);
    }
}
