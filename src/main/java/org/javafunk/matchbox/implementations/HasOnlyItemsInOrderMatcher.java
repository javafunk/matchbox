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
import org.hamcrest.TypeSafeDiagnosingMatcher;
import org.javafunk.funk.datastructures.tuples.Pair;
import org.javafunk.matchbox.IterableMatchers;

import java.util.Iterator;

import static org.apache.commons.lang.builder.ToStringStyle.SHORT_PREFIX_STYLE;
import static org.javafunk.funk.Lazily.enumerate;

public class HasOnlyItemsInOrderMatcher<E> extends TypeSafeDiagnosingMatcher<Iterable<E>> {
    private final Iterable<E> expectedItems;

    public HasOnlyItemsInOrderMatcher(Iterable<E> expectedItems) {
        this.expectedItems = expectedItems;
    }

    @Override
    protected boolean matchesSafely(Iterable<E> actualItems, Description description) {
        Matcher<Iterable<E>> orderAgnosticMatcher = IterableMatchers.hasOnlyItemsInAnyOrder(expectedItems);
        if (!orderAgnosticMatcher.matches(actualItems)) {
            orderAgnosticMatcher.describeMismatch(actualItems, description);
            return false;
        }

        Iterator<E> actualItemIterator = actualItems.iterator();
        for (Pair<Integer, E> indexAndExpectedItem : enumerate(expectedItems)) {
            E actualItem = actualItemIterator.next();
            if (!indexAndExpectedItem.second().equals(actualItem)) {
                description
                        .appendText("got ")
                        .appendValueList("", ", ", "", actualItems)
                        .appendText("\n")
                        .appendText("first item out of order ")
                        .appendValue(actualItem)
                        .appendText(" at index ")
                        .appendText(String.valueOf(indexAndExpectedItem.first()));
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
