package org.javafunk.matchbox;

import org.hamcrest.Matcher;
import org.javafunk.matchbox.implementations.*;
import org.javafunk.matchbox.testclasses.Bean;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.javafunk.funk.Literals.iterableWith;
import static org.javafunk.matchbox.implementations.BetweenMatcher.between;
import static org.javafunk.matchbox.implementations.HasOnlyItemsInAnyOrderMatcher.hasOnlyItemsInAnyOrder;
import static org.javafunk.matchbox.implementations.HasOnlyItemsInOrderMatcher.hasOnlyItemsInOrder;
import static org.javafunk.matchbox.implementations.MatchesMatcher.successfullyMatches;
import static org.javafunk.matchbox.implementations.MismatchMessageMatcher.mismatchesSampleWithMessage;
import static org.javafunk.matchbox.implementations.IsBeanWithSameAttributesAsMatcher.ignoring;
import static org.javafunk.matchbox.implementations.IsBeanWithSameAttributesAsMatcher.isBeanWithSameAttributesAs;
import static org.javafunk.matchbox.testclasses.Bean.bean;

public class MatchersTest {
    @Test
    public void shouldReturnABetweenMatcherWithTheSuppliedLimits() throws Exception {
        // Given
        Matcher<String> expected = new BetweenMatcher<String>("a", "f");

        // When
        Matcher<String> actual = between("a", "f");

        // Then
        assertThat(actual, equalTo(expected));
    }

    @Test
    public void shouldReturnHasOnlyItemsInAnyOrderMatcherWithSuppliedElements() throws Exception {
        // Given
        Matcher<Iterable<String>> expected = new HasOnlyItemsInAnyOrderMatcher<String>(iterableWith("a", "b", "c"));

        // When
        Matcher<Iterable<String>> actual = hasOnlyItemsInAnyOrder("a", "b", "c");

        // Then
        assertThat(actual, equalTo(expected));
    }

    @Test
    public void shouldReturnHasOnlyItemsInAnyOrderMatcherWithAllElementsFromSuppliedIterable() throws Exception {
        // Given
        Matcher<Iterable<String>> expected = new HasOnlyItemsInAnyOrderMatcher<String>(iterableWith("a", "e", "i", "o", "u"));

        // When
        Matcher<Iterable<String>> actual = hasOnlyItemsInAnyOrder(iterableWith("a", "e", "i", "o", "u"));

        // Then
        assertThat(actual, equalTo(expected));
    }

    @Test
    public void shouldReturnHasOnlyItemsInOrderMatcherWithSuppliedElements() throws Exception {
        // Given
        Matcher<Iterable<Integer>> expected = new HasOnlyItemsInOrderMatcher<Integer>(iterableWith(1, 2, 3));

        // When
        Matcher<Iterable<Integer>> actual = hasOnlyItemsInOrder(1, 2, 3);

        // Then
        assertThat(actual, equalTo(expected));
    }

    @Test
    public void shouldReturnHasOnlyItemsInOrderMatcherWithAllElementsFromSuppliedIterable() throws Exception {
        // Given
        Matcher<Iterable<Integer>> expected = new HasOnlyItemsInOrderMatcher<Integer>(iterableWith(1, 2, 3));

        // When
        Matcher<Iterable<Integer>> actual = hasOnlyItemsInOrder(iterableWith(1, 2, 3));

        // Then
        assertThat(actual, equalTo(expected));
    }

    @Test
    public void shouldReturnAMatchesMatcherOverTheSuppliedSample() throws Exception {
        // Given
        Matcher<Matcher<? super Integer>> expected = new MatchesMatcher<Integer>(5);

        // When
        Matcher<Matcher<? super Integer>> actual = successfullyMatches(5);

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

    @Test
    public void shouldReturnAnIsBeanWithSameAttributesAsMatcherWithTheSuppliedBean() throws Exception {
        // Given
        Bean bean = bean("A", "E", "I", "O");
        Matcher<Bean> expected = new IsBeanWithSameAttributesAsMatcher<Bean>(bean, new HashSet<String>());

        // When
        Matcher<Bean> actual = isBeanWithSameAttributesAs(bean);

        // Then
        assertThat(actual, equalTo(expected));
    }

    @Test
    public void shouldReturnAnIsBeanWithSameAttributesAsMatcherWithTheSuppliedBeanAndExclusions() throws Exception {
        // Given
        Bean bean = bean("A", "B", "C", "D");
        Set<String> ignores = ignoring("attribute2");
        Matcher<Bean> expected = new IsBeanWithSameAttributesAsMatcher<Bean>(bean, ignores);

        // When
        Matcher<Bean> actual = isBeanWithSameAttributesAs(bean, ignores);

        // Then
        assertThat(actual, equalTo(expected));
    }
}
