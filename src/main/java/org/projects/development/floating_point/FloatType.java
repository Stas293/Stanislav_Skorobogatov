package org.projects.development.floating_point;

import org.projects.development.numeric.FloatingPointType;

public class FloatType extends FloatingPointType {
    public String getName() {
        return "float";
    }

    public Object parse(String value) throws NumberFormatException {
        return Float.parseFloat(value);
    }
}
