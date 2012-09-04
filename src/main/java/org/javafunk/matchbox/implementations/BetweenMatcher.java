/*
 * Copyright (C) 2012 Matchbox committers.
 * All rights reserved.
 *
 * The software in this package is published under the terms of the BSD
 * style license a copy of which has been included with this distribution in
 * the LICENSE.txt file.
 */
package org.javafunk.matchbox.implementations;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

import static org.apache.commons.lang.builder.ToStringStyle.SHORT_PREFIX_STYLE;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.Matchers.lessThanOrEqualTo;

public class BetweenMatcher<T extends Comparable<T>> extends TypeSafeMatcher<T> {
    private final T low;
    private final T high;

    public BetweenMatcher(T low, T high) {
        this.low = low;
        this.high = high;
    }

    public static <T extends Comparable<T>> Matcher<T> between(T low, T high) {
        return new BetweenMatcher<T>(low, high);
    }

    @Override
    protected boolean matchesSafely(T number) {
        return greaterThanOrEqualTo(low).matches(number) && lessThanOrEqualTo(high).matches(number);
    }

    @Override
    public void describeTo(Description description) {
        description.appendText("value between ").appendValue(low).appendText(" and ").appendValue(high).appendText(" inclusive.");
    }

    @Override
    public boolean equals(Object object) {
        return EqualsBuilder.reflectionEquals(this, object);
    }

    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this);
    }
}
