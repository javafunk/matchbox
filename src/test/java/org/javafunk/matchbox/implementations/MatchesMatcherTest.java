/*
 * Copyright (C) 2012 Matchbox committers.
 * All rights reserved.
 *
 * The software in this package is published under the terms of the BSD
 * style license a copy of which has been included with this distribution in
 * the LICENSE.txt file.
 */
package org.javafunk.matchbox.implementations;

import org.hamcrest.*;
import org.junit.Test;

import java.lang.reflect.Method;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class MatchesMatcherTest {
    @Test
    public void shouldMatchIfSuppliedMatcherMatchesSample() throws Exception {
        // Given
        Integer sample = 15;
        Matcher<Integer> targetMatcher = is(15);
        Matcher<Matcher<? super Integer>> matchesMatcher = new MatchesMatcher<Integer>(sample);

        // When
        Boolean matches = matchesMatcher.matches(targetMatcher);

        // Then
        assertThat(matches, is(true));
    }

    @Test
    public void shouldNotMatchIfSuppliedMatcherDoesNotMatchSample() throws Exception {
        // Given
        Integer sample = 12;
        Matcher<Integer> targetMatcher = is(15);
        Matcher<Matcher<? super Integer>> matchesMatcher = new MatchesMatcher<Integer>(sample);

        // When
        Boolean matches = matchesMatcher.matches(targetMatcher);

        // Then
        assertThat(matches, is(false));
    }

    @Test
    public void shouldDescribeMismatchToDescriptionDetailingActual() throws Exception {
        // Given
        Integer sample = 12;
        TypeSafeDiagnosingMatcher<Matcher<? super Integer>> matchesMatcher = new MatchesMatcher<Integer>(sample);

        Matcher<Integer> targetMatcher = is(15);
        Description targetDescription = new StringDescription();
        targetMatcher.describeMismatch(sample, targetDescription);

        Method matchesSafelyMethod = matchesMatcher.getClass().getDeclaredMethod("matchesSafely", Matcher.class, Description.class);
        matchesSafelyMethod.setAccessible(true);

        Description actual = new StringDescription();
        Description expected = new StringDescription()
                .appendText("got mismatch, description ")
                .appendValue(targetDescription);

        // When
        matchesSafelyMethod.invoke(matchesMatcher, targetMatcher, actual);

        // Then
        assertThat(actual.toString(), is(expected.toString()));
    }

    @Test
    public void shouldDescribeToDescriptionDetailingSample() throws Exception {
        // Given
        Integer sample = 5;
        Description actual = new StringDescription();
        Description expected = new StringDescription()
                .appendText("matcher to match ")
                .appendValue(sample);
        Matcher<Matcher<? super Integer>> matchesMatcher = new MatchesMatcher<Integer>(sample);

        // When
        matchesMatcher.describeTo(actual);

        // Then
        assertThat(actual.toString(), is(expected.toString()));
    }
}
