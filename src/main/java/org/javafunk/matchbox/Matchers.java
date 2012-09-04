package org.javafunk.matchbox;

import org.hamcrest.Matcher;

import java.util.HashSet;
import java.util.Set;

import static java.util.Arrays.asList;

public class Matchers {
    private Matchers() {}

    public static <T extends Comparable<T>> Matcher<T> between(T low, T high) { return ComparableMatchers.between(low, high); }

    public static <T> Matcher<Iterable<T>> hasOnlyItemsInAnyOrder(T... items) { return IterableMatchers.hasOnlyItemsInAnyOrder(items); }
    public static <T> Matcher<Iterable<T>> hasOnlyItemsInAnyOrder(Iterable<T> expectedItems) { return IterableMatchers.hasOnlyItemsInAnyOrder(expectedItems); }
    public static <T> Matcher<Iterable<T>> hasOnlyItemsInOrder(T... items) { return IterableMatchers.hasOnlyItemsInOrder(items); }
    public static <T> Matcher<Iterable<T>> hasOnlyItemsInOrder(Iterable<T> expectedItems) { return IterableMatchers.hasOnlyItemsInOrder(expectedItems); }
    public static <T> Matcher<Iterable<T>> hasAllElementsSatisfying(SelfDescribingPredicate<T> predicate) { return IterableMatchers.hasAllElementsSatisfying(predicate); }
    public static <T> Matcher<Iterable<T>> hasSomeElementsSatisfying(SelfDescribingPredicate<T> predicate) { return IterableMatchers.hasSomeElementsSatisfying(predicate); }

    public static <T> Matcher<Matcher<? super T>> mismatchesSampleWithMessage(T sample, String mismatchMessage) { return MatcherMatchers.mismatchesSampleWithMessage(sample, mismatchMessage); }
    public static <T> Matcher<Matcher<? super T>> matches(T sample) { return MatcherMatchers.matches(sample); }

    public static <T> Matcher<T> isBeanWithSameAttributesAs(T expectedObject) { return ObjectMatchers.isBeanWithSameAttributesAs(expectedObject); }
    public static <T> Matcher<T> isBeanWithSameAttributesAs(T expectedObject, Set<String> ignoreProperties) { return ObjectMatchers.isBeanWithSameAttributesAs(expectedObject, ignoreProperties); }

    public static Set<String> ignoring(String... ignoreProperties) { return new HashSet<String>(asList(ignoreProperties)); }
}
