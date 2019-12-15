package com.topic.unittest.spike;

import org.hamcrest.core.Every;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.everyItem;
import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class HamcrestMatchersTest {

    @Test
    public void hamcrestCoreShowCase() {

        assertThat(true, is(true));
        assertThat(false, is(false));

        assertThat("2", is(equalTo("2")));
        assertThat(2, is(equalTo(2)));
        assertThat("aBcD", is(equalToIgnoringCase("abcd")));
        assertThat("", is(emptyString()));

        assertThat(2, is(notNullValue()));
        assertThat(2, greaterThan(1));
        assertThat(2.01, closeTo(2, 0.01));

        assertThat("xyz", is(anything()));

    }

    @Test
    public void basicHamcrestMatchers() {
        List<Integer> scores = Arrays.asList(99, 100, 101, 105);
        assertThat(scores, hasSize(4));
        assertThat(scores, hasItems(100, 101));
        assertThat(scores, everyItem(greaterThan(90)));
        assertThat(scores, everyItem(lessThan(200)));

        // String
        assertThat("", is(emptyString()));
        assertThat(null, is(nullValue()));

        // Array
        Integer[] marks = {1, 2, 3};

        assertThat(marks, arrayWithSize(3));
        assertThat(marks, arrayContainingInAnyOrder(2, 3, 1));

    }

}
