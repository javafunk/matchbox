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
import org.javafunk.matchbox.implementations.HasOnlyItemsInAnyOrderMatcher;
import org.javafunk.matchbox.implementations.HasOnlyItemsInOrderMatcher;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.javafunk.funk.Literals.iterableWith;
import static org.javafunk.matchbox.IterableMatchers.hasOnlyItemsInAnyOrder;
import static org.javafunk.matchbox.IterableMatchers.hasOnlyItemsInOrder;

public class IterableMatchersTest {
    @Test
    public void shouldReturnHasOnlyItemsInAnyOrderMatcherWithSuppliedElements() throws Exception {
        // Given
        Matcher<Iterable<String>> expected = new HasOnlyItemsInAnyOrderMatcher<String>(iterableWith("a", "b", "c"));

        // When
        Matcher<Iterable<String>> actual = hasOnlyItemsInAnyOrder("a", "b", "c");

        // Then
        assertThat(actual, equalTo(expected));
    }

    @Test
    public void shouldReturnHasOnlyItemsInAnyOrderMatcherWithAllElementsFromSuppliedIterable() throws Exception {
        // Given
        Matcher<Iterable<String>> expected = new HasOnlyItemsInAnyOrderMatcher<String>(iterableWith("a", "e", "i", "o", "u"));

        // When
        Matcher<Iterable<String>> actual = hasOnlyItemsInAnyOrder(iterableWith("a", "e", "i", "o", "u"));

        // Then
        assertThat(actual, equalTo(expected));
    }

    @Test
    public void shouldReturnHasOnlyItemsInOrderMatcherWithSuppliedElements() throws Exception {
        // Given
        Matcher<Iterable<Integer>> expected = new HasOnlyItemsInOrderMatcher<Integer>(iterableWith(1, 2, 3));

        // When
        Matcher<Iterable<Integer>> actual = hasOnlyItemsInOrder(1, 2, 3);

        // Then
        assertThat(actual, equalTo(expected));
    }

    @Test
    public void shouldReturnHasOnlyItemsInOrderMatcherWithAllElementsFromSuppliedIterable() throws Exception {
        // Given
        Matcher<Iterable<Integer>> expected = new HasOnlyItemsInOrderMatcher<Integer>(iterableWith(1, 2, 3));

        // When
        Matcher<Iterable<Integer>> actual = hasOnlyItemsInOrder(iterableWith(1, 2, 3));

        // Then
        assertThat(actual, equalTo(expected));
    }
}
