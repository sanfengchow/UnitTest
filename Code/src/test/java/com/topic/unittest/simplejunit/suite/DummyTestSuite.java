package com.topic.unittest.simplejunit.suite;

import com.topic.unittest.simplejunit.JUnitDemoTest;
import com.topic.unittest.simplejunit.StringHelperTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({JUnitDemoTest.class, StringHelperTest.class})
public class DummyTestSuite {

}
