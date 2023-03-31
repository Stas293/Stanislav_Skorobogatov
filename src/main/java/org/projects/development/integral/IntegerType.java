package org.projects.development.integral;

import org.projects.development.numeric.IntegralType;

public class IntegerType extends IntegralType {
    public String getName() {
        return "int";
    }

    public Object parse(String value) throws NumberFormatException {
        return Integer.parseInt(value);
    }
}
