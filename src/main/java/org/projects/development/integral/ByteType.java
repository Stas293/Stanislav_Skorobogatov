package org.projects.development.integral;

import org.projects.development.numeric.IntegralType;

public class ByteType extends IntegralType {
    public String getName() {
        return "byte";
    }

    public Object parse(String value) throws NumberFormatException {
        return Byte.parseByte(value);
    }
}
