package org.javafunk.matchbox.implementations;

import org.hamcrest.Matcher;
import org.junit.Test;

import static java.util.Arrays.asList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.javafunk.matchbox.Matchers.hasOnlyItemsInOrder;
import static org.javafunk.matchbox.Matchers.mismatchesSampleWithMessage;
import static org.javafunk.matchbox.Matchers.successfullyMatches;

public class HasOnlyItemsInOrderMatcherTest {
    @Test
    public void shouldReportOnMismatchedItemsInHasOnlyItemsInOrder() {
        // Given
        Iterable<Integer> actual = asList(1, 2, 5, 6);
        Iterable<Integer> expected = asList(1, 2, 3, 4);

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
        Iterable<Integer> actual = asList(1, 2, 5, 6);
        Iterable<Integer> expected = asList(1, 2);

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
        Iterable<Integer> actual = asList(1, 2);
        Iterable<Integer> expected = asList(1, 2, 4, 5);

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
        Iterable<Integer> actual = asList(1, 2, 4, 3);
        Iterable<Integer> expected = asList(1, 2, 3, 4);

        // When
        Matcher<Iterable<Integer>> matcher = hasOnlyItemsInOrder(expected);

        // Then
        assertThat(matcher, mismatchesSampleWithMessage(actual, "got <1>, <2>, <4>, <3>\n" +
                "first item out of order <4> at index 2"));
    }

    @Test
    public void shouldAllowNullsToBeItems() {
        // Given
        Iterable<Integer> actual = asList(1, 2, null, 3);
        Iterable<Integer> expected = asList(1, 2, null, 3);

        // When
        Matcher<Iterable<Integer>> matcher = hasOnlyItemsInOrder(expected);

        // Then
        assertThat(matcher, successfullyMatches(actual));
    }

    @Test
    public void shouldAllowOnlyNullsToBeItems() {
        // Given
        Iterable<Integer> actual = asList(null, null);
        Iterable<Integer> expected = asList(null, null);

        // When
        Matcher<Iterable<Integer>> matcher = hasOnlyItemsInOrder(expected);

        // Then
        assertThat(matcher, successfullyMatches(actual));
    }

    @Test
    public void shouldReportOnMismatchedNullItemsInHasOnlyItemsInOrder() {
        // Given
        Iterable<Integer> actual = asList(1, 2, null, null);
        Iterable<Integer> expected = asList(1, 2, 3, 4);

        // When
        Matcher<Iterable<Integer>> matcher = hasOnlyItemsInOrder(expected);

        // Then
        assertThat(matcher, mismatchesSampleWithMessage(actual, "got <1>, <2>, null, null\n" +
                "expected but didn't get <3>, <4>\n" +
                "got but didn't expect null, null"));
    }

    @Test
    public void shouldReportOnTooManyNullItemsInHasOnlyItemsInOrder() {
        // Given
        Iterable<Integer> actual = asList(1, 2, null, null);
        Iterable<Integer> expected = asList(1, 2);

        // When
        Matcher<Iterable<Integer>> matcher = hasOnlyItemsInOrder(expected);

        // Then
        assertThat(matcher, mismatchesSampleWithMessage(actual, "got <1>, <2>, null, null\n" +
                "got collection with size <4> rather than <2>\n" +
                "got but didn't expect null, null"));
    }

    @Test
    public void shouldReportOnTooFewNullItemsInHasOnlyItemsInOrder() {
        // Given
        Iterable<Integer> actual = asList(1, 2);
        Iterable<Integer> expected = asList(1, 2, null, null);

        // When
        Matcher<Iterable<Integer>> matcher = hasOnlyItemsInOrder(expected);

        // Then
        assertThat(matcher, mismatchesSampleWithMessage(actual, "got <1>, <2>\n" +
                "got collection with size <2> rather than <4>\n" +
                "expected but didn't get null, null"));
    }

    @Test
    public void shouldReportOnTooFewNullAndNonNullItemsInHasOnlyItemsInOrder() {
        // Given
        Iterable<Integer> actual = asList(1, 2);
        Iterable<Integer> expected = asList(1, 2, null, 4);

        // When
        Matcher<Iterable<Integer>> matcher = hasOnlyItemsInOrder(expected);

        // Then
        assertThat(matcher, mismatchesSampleWithMessage(actual, "got <1>, <2>\n" +
                "got collection with size <2> rather than <4>\n" +
                "expected but didn't get null, <4>"));
    }

    @Test
    public void shouldReportOnIncorrectNullItemsOrderInHasOnlyItemsInOrder() {
        // Given
        Iterable<Integer> actual = asList(1, 2, 3, null);
        Iterable<Integer> expected = asList(1, 2, null, 3);

        // When
        Matcher<Iterable<Integer>> matcher = hasOnlyItemsInOrder(expected);

        // Then
        assertThat(matcher, mismatchesSampleWithMessage(actual, "got <1>, <2>, <3>, null\n" +
                "first item out of order <3> at index 2"));
    }
}
