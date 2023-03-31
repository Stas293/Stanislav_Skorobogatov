package org.projects.development.integral;

import org.projects.development.numeric.IntegralType;

public class LongType extends IntegralType {
    public String getName() {
        return "long";
    }

    public Object parse(String value) throws NumberFormatException {
        return Long.parseLong(value);
    }
}
