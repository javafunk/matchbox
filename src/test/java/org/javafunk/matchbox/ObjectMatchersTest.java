package org.javafunk.matchbox;

import org.hamcrest.Matcher;
import org.javafunk.matchbox.implementations.IsBeanWithSameAttributesAsMatcher;
import org.javafunk.matchbox.testclasses.Bean;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.javafunk.matchbox.ObjectMatchers.ignoring;
import static org.javafunk.matchbox.ObjectMatchers.isBeanWithSameAttributesAs;
import static org.javafunk.matchbox.testclasses.Bean.bean;

public class ObjectMatchersTest {
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
