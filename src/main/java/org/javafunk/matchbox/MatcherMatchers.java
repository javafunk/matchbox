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
import org.javafunk.matchbox.implementations.MatchesMatcher;
import org.javafunk.matchbox.implementations.MismatchMessageMatcher;

class MatcherMatchers {
    private MatcherMatchers() {}

    public static <T> Matcher<Matcher<? super T>> mismatchesSampleWithMessage(T sample, String descriptionContains) {
        return new MismatchMessageMatcher<T>(sample, descriptionContains);
    }

    public static <T> Matcher<Matcher<? super T>> matches(T sample) {
        return new MatchesMatcher<T>(sample);
    }
}