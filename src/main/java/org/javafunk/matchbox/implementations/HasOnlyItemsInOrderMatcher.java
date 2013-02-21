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
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeDiagnosingMatcher;

import java.util.Iterator;

import static com.google.common.base.Objects.equal;
import static java.util.Arrays.asList;
import static org.javafunk.matchbox.Matchers.hasOnlyItemsInAnyOrder;

public class HasOnlyItemsInOrderMatcher<E> extends TypeSafeDiagnosingMatcher<Iterable<E>> {
    private final Iterable<E> expectedItems;

    public HasOnlyItemsInOrderMatcher(Iterable<E> expectedItems) {
        this.expectedItems = expectedItems;
    }

    public static <T> Matcher<Iterable<T>> hasOnlyItemsInOrder(T... items) {
        return hasOnlyItemsInOrder(asList(items));
    }

    public static <T> Matcher<Iterable<T>> hasOnlyItemsInOrder(Iterable<T> expectedItems) {
        return new HasOnlyItemsInOrderMatcher<T>(expectedItems);
    }

    @Override
    protected boolean matchesSafely(Iterable<E> actualItems, Description description) {
        Matcher<Iterable<E>> orderAgnosticMatcher = hasOnlyItemsInAnyOrder(expectedItems);
        if (!orderAgnosticMatcher.matches(actualItems)) {
            orderAgnosticMatcher.describeMismatch(actualItems, description);
            return false;
        }

        Iterator<E> actualItemIterator = actualItems.iterator();
        int index = 0;
        for (E expectedItem : expectedItems) {
            Integer expectedItemIndex = index;
            index += 1;
            E actualItem = actualItemIterator.next();
            if (!equal(expectedItem, actualItem)){
                description
                        .appendText("got ")
                        .appendValueList("", ", ", "", actualItems)
                        .appendText("\n")
                        .appendText("first item out of order ")
                        .appendValue(actualItem)
                        .appendText(" at index ")
                        .appendText(String.valueOf(expectedItemIndex));
                return false;
            }
        }
        return true;
    }

    @Override
    public void describeTo(Description description) {
        description.appendText("Collection containing exactly ")
                .appendValueList("", ", ", "", expectedItems)
                .appendText(" in order.");
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
