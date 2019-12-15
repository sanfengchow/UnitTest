package com.topic.unittest.simplejunit;


import lombok.Data;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.junit.runners.MethodSorters;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotSame;


/**
 * 人员档案服务测试类
 *
 * @author Smart Chow
 * @version [0.0.1, 2019-12-01 16:27]
 * @since 0.0.1
 */
@RunWith(JUnit4.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class JUnitDemoTest {
    private Person personOne;
    private Person personTwo;

    @Before
    public void setup() {
        personOne = new Person();
        personOne.setName("张三");

        personTwo = new Person();
        personTwo.setName("张三");
    }

    @Test
    public void assertEqualsString() {
        String empName = "张三";
        assertEquals(empName, personOne.getName());

    }

    @Test
    public void assertEqualsObject() {
        assertEquals(personOne, personTwo);
    }

    @Test
    public void assertNotSameObject() {
        assertNotSame(personOne, personTwo);
    }

    @Test
    public void assertEqualsFloat() {
        assertEquals(3.33, 10.0 / 3.0, 0.01);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testException() {
        List list = new ArrayList();
        list.get(2);
    }

    @Test(timeout = 100)
    public void testPerformance() {
        for (int i = 0; i < 1000000; i++) {
            Arrays.sort(new int[]{i, i - 1, i + 1});
        }
    }
}

@Data
class Person {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}