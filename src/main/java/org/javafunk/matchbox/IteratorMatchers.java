package org.javafunk.matchbox;

import org.hamcrest.Matcher;
import org.javafunk.matchbox.implementations.IsIteratorWithSameElementsAsMatcher;

import java.util.Iterator;

public class IteratorMatchers {
    public static <E> Matcher<? super Iterator<E>> isIteratorWithSameElementsAs(final Iterator<E> expected) {
        return new IsIteratorWithSameElementsAsMatcher<E>(expected);
    }
}
