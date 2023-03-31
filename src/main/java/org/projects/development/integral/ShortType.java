package org.projects.development.integral;

import org.projects.development.numeric.IntegralType;

public class ShortType extends IntegralType {
    public String getName() {
        return "short";
    }

    public Object parse(String value) throws NumberFormatException {
        return Short.parseShort(value);
    }
}
