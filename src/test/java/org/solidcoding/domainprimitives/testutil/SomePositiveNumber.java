package org.solidcoding.domainprimitives.testutil;

import org.solidcoding.domainprimitives.DomainPrimitive;

public class SomePositiveNumber extends DomainPrimitive<Integer> {

    public SomePositiveNumber(Integer value) {
        super(value, SomePositiveNumber.class);
    }

    @Override
    protected boolean isValid(Integer value) {
        return value >= 0;
    }

}
