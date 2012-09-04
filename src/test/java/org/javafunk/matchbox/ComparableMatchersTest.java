package org.javafunk.matchbox;

import org.hamcrest.Matcher;
import org.javafunk.matchbox.implementations.BetweenMatcher;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.javafunk.matchbox.ComparableMatchers.between;

public class ComparableMatchersTest {
    @Test
    public void shouldReturnABetweenMatcherWithTheSuppliedLimits() throws Exception {
        // Given
        Matcher<String> expected = new BetweenMatcher<String>("a", "f");

        // When
        Matcher<String> actual = between("a", "f");

        // Then
        assertThat(actual, equalTo(expected));
    }
}
