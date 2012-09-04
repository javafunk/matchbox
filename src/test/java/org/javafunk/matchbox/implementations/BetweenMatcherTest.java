package org.javafunk.matchbox.implementations;

import org.hamcrest.Description;
import org.hamcrest.StringDescription;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.javafunk.matchbox.Matchers.matches;
import static org.javafunk.matchbox.Matchers.mismatchesSampleWithMessage;

public class BetweenMatcherTest {
    @Test
    public void shouldMatchIfComparableValueBetweenSuppliedValues() throws Exception {
        assertThat(betweenMatcher(5, 10), matches(7));
    }

    @Test
    public void shouldMatchIfComparableValueEqualToLowerBound() throws Exception {
        assertThat(betweenMatcher(5, 10), matches(5));
    }

    @Test
    public void shouldMatchIfComparableValueEqualToUpperBound() throws Exception {
        assertThat(betweenMatcher(5, 10), matches(10));
    }

    @Test
    public void shouldNotMatchIfComparableValueLessThanLowerBound() throws Exception {
        assertThat(betweenMatcher(5, 10), mismatchesSampleWithMessage(3, "was <3>"));
    }

    @Test
    public void shouldNotMatchIfComparableValueGreaterThanUpperBound() throws Exception {
        assertThat(betweenMatcher(5, 10), mismatchesSampleWithMessage(13, "was <13>"));
    }

    @Test
    public void shouldDescribeExpectedToSuppliedDescription() throws Exception {
        // Given
        Description actual = new StringDescription();
        BetweenMatcher<Integer> betweenMatcher = new BetweenMatcher<Integer>(5, 10);

        Description expected = new StringDescription();
        expected.appendText("value between ")
                .appendValue(5)
                .appendText(" and ")
                .appendValue(10)
                .appendText(" inclusive.");

        // When
        betweenMatcher.describeTo(actual);

        // Then
        assertThat(actual.toString(), is(expected.toString()));
    }

    private static BetweenMatcher<Integer> betweenMatcher(int lowerBound, int upperBound) {
        return new BetweenMatcher<Integer>(lowerBound, upperBound);
    }
}
