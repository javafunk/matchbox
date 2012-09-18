/*
 * Copyright (C) 2012 Matchbox committers.
 * All rights reserved.
 *
 * The software in this package is published under the terms of the BSD
 * style license a copy of which has been included with this distribution in
 * the LICENSE.txt file.
 */
package org.javafunk.matchbox.implementations;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.javafunk.matchbox.SelfDescribingPredicate;

public class HasAllElementsSatisfyingMatcher<T> extends TypeSafeMatcher<Iterable<T>> {
    private final SelfDescribingPredicate<T> predicate;

    public HasAllElementsSatisfyingMatcher(SelfDescribingPredicate<T> predicate) {
        this.predicate = predicate;
    }

    public static <T> Matcher<Iterable<T>> hasAllElementsSatisfying(SelfDescribingPredicate<T> predicate) {
        return new HasAllElementsSatisfyingMatcher<T>(predicate);
    }

    @Override
    protected boolean matchesSafely(Iterable<T> items) {
        for (T item : items) {
            if (!predicate.evaluate(item)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public void describeTo(Description description) {
        description.appendText("Collection with all items matching predicate ").appendValue(predicate.describe());
    }
}
