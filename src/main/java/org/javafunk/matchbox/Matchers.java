package org.javafunk.matchbox;

import org.hamcrest.Matcher;
import org.javafunk.matchbox.implementations.*;

import java.util.HashSet;
import java.util.Set;

import static java.util.Arrays.asList;

public class Matchers {
    private Matchers() {}

    public static <T extends Comparable<T>> Matcher<T> between(T low, T high) { return BetweenMatcher.between(low, high); }

    public static <T> Matcher<Iterable<T>> hasOnlyItemsInAnyOrder(T... items) { return HasOnlyItemsInAnyOrderMatcher.hasOnlyItemsInAnyOrder(items); }
    public static <T> Matcher<Iterable<T>> hasOnlyItemsInAnyOrder(Iterable<T> expectedItems) { return HasOnlyItemsInAnyOrderMatcher.hasOnlyItemsInAnyOrder(expectedItems); }
    public static <T> Matcher<Iterable<T>> hasOnlyItemsInOrder(T... items) { return HasOnlyItemsInOrderMatcher.hasOnlyItemsInOrder(items); }
    public static <T> Matcher<Iterable<T>> hasOnlyItemsInOrder(Iterable<T> expectedItems) { return HasOnlyItemsInOrderMatcher.hasOnlyItemsInOrder(expectedItems); }
    public static <T> Matcher<Iterable<T>> hasAllElementsSatisfying(SelfDescribingPredicate<T> predicate) { return HasAllElementsSatisfyingMatcher.hasAllElementsSatisfying(predicate); }
    public static <T> Matcher<Iterable<T>> hasSomeElementsSatisfying(SelfDescribingPredicate<T> predicate) { return HasSomeElementsSatisfyingMatcher.hasSomeElementsSatisfying(predicate); }

    public static <T> Matcher<Matcher<? super T>> mismatchesSampleWithMessage(T sample, String mismatchMessage) { return MismatchMessageMatcher.mismatchesSampleWithMessage(sample, mismatchMessage); }
    public static <T> Matcher<Matcher<? super T>> successfullyMatches(T sample) { return MatchesMatcher.successfullyMatches(sample); }

    public static <T> Matcher<T> isBeanWithSameAttributesAs(T expectedObject) { return IsBeanWithSameAttributesAsMatcher.isBeanWithSameAttributesAs(expectedObject); }
    public static <T> Matcher<T> isBeanWithSameAttributesAs(T expectedObject, Set<String> ignoreProperties) { return IsBeanWithSameAttributesAsMatcher.isBeanWithSameAttributesAs(expectedObject, ignoreProperties); }

    public static Set<String> ignoring(String... ignoreProperties) { return new HashSet<String>(asList(ignoreProperties)); }
}
