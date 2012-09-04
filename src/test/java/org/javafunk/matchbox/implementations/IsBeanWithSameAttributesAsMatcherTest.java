package org.javafunk.matchbox.implementations;

import org.hamcrest.Matcher;
import org.junit.Test;

import static java.lang.String.format;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.javafunk.matchbox.MatcherMatchers.matches;
import static org.javafunk.matchbox.MatcherMatchers.mismatchesSampleWithMessage;
import static org.javafunk.matchbox.ObjectMatchers.isBeanWithSameAttributesAs;
import static org.javafunk.matchbox.implementations.IsBeanWithSameAttributesAsMatcherTest.Bean.bean;

public class IsBeanWithSameAttributesAsMatcherTest {
    @Test
    public void shouldMatchBeanWithSameProperties() {
        // Given
        Bean actual = bean("A", "B", "C", "D");
        Bean expected = bean("A", "B", "C", "D");

        // When
        Matcher<Bean> matcher = isBeanWithSameAttributesAs(expected);

        // Then
        assertThat(matcher, matches(actual));
    }

    @Test
    public void shouldMismatchBeanWithDifferentProperties() {
        // Given
        Bean actual = bean("A", "B", "C", "D");
        Bean expected = bean("A", "B", "C", "E");

        // When
        Matcher<Bean> matcher = isBeanWithSameAttributesAs(expected);

        // Then
        assertThat(matcher, mismatchesSampleWithMessage(actual, "got      Bean <Bean<attribute1=<A>, attribute2=<B>, attribute3=<C>, attribute4=<E>, >>\n" +
                "Mismatch: expected property \"attribute4\" = \"E\"\n" +
                "            actual property \"attribute4\" = \"D\""));
    }

    public static class Bean {
        private String attribute1;
        private String attribute2;
        private String attribute3;
        private String attribute4;

        private Bean(String attribute1, String attribute2, String attribute3, String attribute4) {
            this.attribute1 = attribute1;
            this.attribute2 = attribute2;
            this.attribute3 = attribute3;
            this.attribute4 = attribute4;
        }

        public static Bean bean(String attribute1, String attribute2, String attribute3, String attribute4) {
            return new Bean(attribute1, attribute2, attribute3, attribute4);
        }

        public String getAttribute1() {
            return attribute1;
        }

        public String getAttribute2() {
            return attribute2;
        }

        public String getAttribute3() {
            return attribute3;
        }

        public String getAttribute4() {
            return attribute4;
        }

        @Override
        public String toString() {
            return format("Bean<attribute1=<%s>, attribute2=<%s>, attribute3=<%s>, attribute4=<%s>, >", attribute1, attribute2, attribute3, attribute4);
        }
    }
}
