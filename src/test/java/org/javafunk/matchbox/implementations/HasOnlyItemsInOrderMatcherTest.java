package org.javafunk.matchbox.implementations;

import org.hamcrest.Matcher;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.javafunk.funk.Literals.iterableWith;
import static org.javafunk.matchbox.IterableMatchers.hasOnlyItemsInOrder;
import static org.javafunk.matchbox.MatcherMatchers.mismatchesSampleWithMessage;

public class HasOnlyItemsInOrderMatcherTest {
    @Test
    public void shouldReportOnMismatchedItemsInHasOnlyItemsInOrder() {
        // Given
        Iterable<Integer> actual = iterableWith(1, 2, 5, 6);
        Iterable<Integer> expected = iterableWith(1, 2, 3, 4);

        // When
        Matcher<Iterable<Integer>> matcher = hasOnlyItemsInOrder(expected);

        // Then
        assertThat(matcher, mismatchesSampleWithMessage(actual, "got <1>, <2>, <5>, <6>\n" +
                "expected but didn't get <3>, <4>\n" +
                "got but didn't expect <5>, <6>"));
    }

    @Test
    public void shouldReportOnTooManyItemsInHasOnlyItemsInOrder() {
        // Given
        Iterable<Integer> actual = iterableWith(1, 2, 5, 6);
        Iterable<Integer> expected = iterableWith(1, 2);

        // When
        Matcher<Iterable<Integer>> matcher = hasOnlyItemsInOrder(expected);

        // Then
        assertThat(matcher, mismatchesSampleWithMessage(actual, "got <1>, <2>, <5>, <6>\n" +
                "got collection with size <4> rather than <2>\n" +
                "got but didn't expect <5>, <6>"));
    }

    @Test
    public void shouldReportOnTooFewItemsInHasOnlyItemsInOrder() {
        // Given
        Iterable<Integer> actual = iterableWith(1, 2);
        Iterable<Integer> expected = iterableWith(1, 2, 4, 5);

        // When
        Matcher<Iterable<Integer>> matcher = hasOnlyItemsInOrder(expected);

        // Then
        assertThat(matcher, mismatchesSampleWithMessage(actual, "got <1>, <2>\n" +
                "got collection with size <2> rather than <4>\n" +
                "expected but didn't get <4>, <5>"));
    }

    @Test
    public void shouldReportOnIncorrectOrderInHasOnlyItemsInOrder() {
        // Given
        Iterable<Integer> actual = iterableWith(1, 2, 4, 3);
        Iterable<Integer> expected = iterableWith(1, 2, 3, 4);

        // When
        Matcher<Iterable<Integer>> matcher = hasOnlyItemsInOrder(expected);

        // Then
        assertThat(matcher, mismatchesSampleWithMessage(actual, "got <1>, <2>, <4>, <3>\n" +
                "first item out of order <4> at index 2"));
    }
}
