/*
 * Copyright (C) 2012 Matchbox committers.
 * All rights reserved.
 *
 * The software in this package is published under the terms of the BSD
 * style license a copy of which has been included with this distribution in
 * the LICENSE.txt file.
 */
package org.javafunk.matchbox;

import org.hamcrest.Matcher;
import org.javafunk.matchbox.implementations.*;

import static java.util.Arrays.asList;
import static org.javafunk.funk.Literals.listFrom;

class IterableMatchers {
    private IterableMatchers() {}

    public static <T> Matcher<Iterable<T>> hasOnlyItemsInAnyOrder(T... items) {
        return hasOnlyItemsInAnyOrder(listFrom(items));
    }

    public static <T> Matcher<Iterable<T>> hasOnlyItemsInAnyOrder(Iterable<T> expectedItems) {
        return new HasOnlyItemsInAnyOrderMatcher<T>(expectedItems);
    }

    public static <T> Matcher<Iterable<T>> hasOnlyItemsInOrder(T... items) {
        return hasOnlyItemsInOrder(listFrom(items));
    }

    public static <T> Matcher<Iterable<T>> hasOnlyItemsInOrder(Iterable<T> expectedItems) {
        return new HasOnlyItemsInOrderMatcher<T>(expectedItems);
    }

    public static <T> Matcher<Iterable<T>> hasAllElementsSatisfying(SelfDescribingPredicate<T> predicate) {
        return new HasAllElementsSatisfyingMatcher<T>(predicate);
    }

    public static <T> Matcher<Iterable<T>> hasSomeElementsSatisfying(SelfDescribingPredicate<T> predicate) {
        return new HasSomeElementsSatisfyingMatcher<T>(predicate);
    }
}
