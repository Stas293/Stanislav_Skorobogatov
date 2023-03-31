package org.projects.development.primitive;

import org.projects.development.base.PrimitiveType;

public class CharType extends PrimitiveType {
    public String getName() {
        return "char";
    }

    public Object parse(String value) throws IllegalArgumentException {
        if (value.length() != 1) {
            throw new IllegalArgumentException("Cannot convert string \"" + value + "\" to char");
        }
        return value.charAt(0);
    }
}
