package org.projects.development.floating_point;

import org.projects.development.numeric.FloatingPointType;

public class DoubleType extends FloatingPointType {
    public String getName() {
        return "double";
    }

    public Object parse(String value) throws NumberFormatException {
        return Double.parseDouble(value);
    }
}
