/*
 * Copyright (C) 2012 Matchbox committers.
 * All rights reserved.
 *
 * The software in this package is published under the terms of the BSD
 * style license a copy of which has been included with this distribution in
 * the LICENSE.txt file.
 */
package org.javafunk.matchbox.implementations;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.StringDescription;
import org.hamcrest.TypeSafeDiagnosingMatcher;

public class MismatchMessageMatcher<T> extends TypeSafeDiagnosingMatcher<Matcher<T>> {
    private final T sample;
    private final String descriptionContains;

    public MismatchMessageMatcher(T sample, String descriptionContains) {
        this.sample = sample;
        this.descriptionContains = descriptionContains;
    }

    @Override
    protected boolean matchesSafely(Matcher<T> matcher, Description description) {
        if (matcher.matches(sample)) {
            description.appendText("matcher matched");
            return false;
        }

        Description actualDescription = new StringDescription();
        matcher.describeMismatch(sample, actualDescription);
        description.appendText("got description ").appendValue(actualDescription);

        return actualDescription.toString().equals(descriptionContains);
    }

    @Override
    public void describeTo(Description description) {
        description.appendText("Matcher to mismatch ").appendValue(sample)
                .appendText(" and give description containing ").appendValue(descriptionContains);
    }
}
