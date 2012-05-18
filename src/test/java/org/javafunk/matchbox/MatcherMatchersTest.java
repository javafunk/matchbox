package org.javafunk.matchbox;

import org.hamcrest.Matcher;
import org.javafunk.matchbox.implementations.MatchesMatcher;
import org.javafunk.matchbox.implementations.MismatchMessageMatcher;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.javafunk.matchbox.MatcherMatchers.matches;
import static org.javafunk.matchbox.MatcherMatchers.mismatchesSampleWithMessage;

public class MatcherMatchersTest {
    @Test
    public void shouldReturnAMatchesMatcherOverTheSuppliedSample() throws Exception {
        // Given
        Matcher<Matcher<? super Integer>> expected = new MatchesMatcher<Integer>(5);

        // When
        Matcher<Matcher<? super Integer>> actual = matches(5);

        // Then
        assertThat(actual, equalTo(expected));
    }

    @Test
    public void shouldReturnAMismatchMessageMatcherOverTheSuppliedSampleAndMismatchMessage() throws Exception {
        // Given
        Matcher<Matcher<? super Integer>> expected = new MismatchMessageMatcher<Integer>(10, "some mismatch message");

        // When
        Matcher<Matcher<? super Integer>> actual = mismatchesSampleWithMessage(10, "some mismatch message");

        // Then
        assertThat(actual, equalTo(expected));
    }
}
