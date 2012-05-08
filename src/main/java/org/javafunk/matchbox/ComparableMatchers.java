package org.javafunk.matchbox;

import org.hamcrest.Matcher;
import org.javafunk.matchbox.implementations.BetweenMatcher;

public class ComparableMatchers {
    public static <T extends Comparable<T>> Matcher<? super T> between(final T low, final T high) {
        return new BetweenMatcher<T>(low, high);
    }
}
