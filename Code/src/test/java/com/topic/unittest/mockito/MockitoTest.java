package com.topic.unittest.mockito;

import static org.mockito.Mockito.*;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.ArgumentMatcher;
import org.mockito.Mockito;

/**
 * MockitoTest测试案例
 *
 * @author Smart Chow
 * @version [0.0.1, 2019-12-18 14:10]
 * @since 0.0.1
 */
public class MockitoTest {

    @Test
    public void verifyTest() {
        // 模拟创建一个List对象
        List<Integer> mock = mock(List.class);
        // 调用mock对象的方法
        mock.add(1);
        mock.clear();
        // 验证方法是否执行
        verify(mock).add(1);
        verify(mock).clear();
    }

    @Test
    public void when_thenReturn() {
        List<Integer> mock = mock(List.class);
        when(mock.get(0)).thenReturn(888);

        Assert.assertEquals(Integer.valueOf(888), mock.get(0));
    }

    @Test
    public void when_thenReturnThenReturn() {
        // mock一个Iterator类
        Iterator iterator = mock(Iterator.class);
        // 预设当iterator调用next()时第一次返回hello，第n次都返回world
        Mockito.when(iterator.next()).thenReturn("hello").thenReturn("world");
        // 使用mock的对象
        String result = iterator.next() + " " + iterator.next() + " " + iterator.next();
        // 验证结果
        Assert.assertEquals("hello world world", result);
    }

    @Test(expected = IOException.class) // 期望报IO异常
    public void when_thenThrow() throws IOException {
        OutputStream mock = Mockito.mock(OutputStream.class);
        // 预设当流关闭时抛出异常
        Mockito.doThrow(new IOException()).when(mock).close();
        mock.close();
    }

    @Test
    public void answer_with_callback() {
        List mockList = Mockito.mock(List.class);
        // 使用Answer来生成我们我们期望的返回
        when(mockList.get(Mockito.anyInt())).thenAnswer(invocation -> {
            Object[] args = invocation.getArguments();
            return "hello world:" + args[0];
        });
        Assert.assertEquals("hello world:0", mockList.get(0));
        Assert.assertEquals("hello world:999", mockList.get(999));
    }

    @Test
    public void testDoAnswer() {
        List<String> mock = Mockito.mock(List.class);
        Mockito.doAnswer(invocation -> {
            Object[] args = invocation.getArguments();
            Integer num = (Integer)args[0];
            if (num > 3) {
                return "大于三";
            } else {
                return "小于三";
            }
        }).when(mock).get(Mockito.anyInt());
        Assert.assertEquals("大于三", mock.get(4));
        Assert.assertEquals("小于三", mock.get(2));
    }

    /**
     * Mockito.anyInt() 任何 int 值 ； <br/>
     * Mockito.anyLong() 任何 long 值 ； <br/>
     * Mockito.anyString() 任何 String 值 ； <br/>
     *
     * Mockito.any(XXX.class) 任何 XXX 类型的值 等等。
     *
     */
    @Test
    public void with_arguments() {
        B b = Mockito.mock(B.class);
        // 预设根据不同的参数返回不同的结果
        Mockito.when(b.getSex(1)).thenReturn("男");
        Mockito.when(b.getSex(2)).thenReturn("女");
        Assert.assertEquals("男", b.getSex(1));
        Assert.assertEquals("女", b.getSex(2));
        // 对于没有预设的情况会返回默认值
        Assert.assertEquals(null, b.getSex(0));
    }

    @Test
    public void with_unspecified_arguments() {
        List list = Mockito.mock(List.class);
        // 匹配任意参数
        Mockito.when(list.get(Mockito.anyInt())).thenReturn(1);
        Mockito.when(list.contains(Mockito.argThat(new IsValid()))).thenReturn(true);
        Assert.assertEquals(1, list.get(1));
        Assert.assertEquals(1, list.get(999));
        Assert.assertTrue(list.contains(1));
        Assert.assertTrue(!list.contains(3));
    }

    class IsValid implements ArgumentMatcher {
        @Override
        public boolean matches(Object obj) {
            return obj.equals(1) || obj.equals(2);
        }
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void spy_on_real_objects() {
        List list = new LinkedList();
        list.add(23);
        List spy = Mockito.spy(list);
        // 下面预设的spy.get(0)会报错，因为会调用真实对象的get(0)，所以会抛出越界异常
        // Mockito.when(spy.get(0)).thenReturn(3);

        // 使用doReturn-when可以避免when-thenReturn调用真实对象api
        // Mockito.doReturn(999).when(spy).get(999);
        when(spy.get(999)).thenReturn(999);
        // 预设size()期望值
        Mockito.when(spy.size()).thenReturn(100);
        // 调用真实对象的api
        spy.add(1);
        spy.add(2);

        Assert.assertEquals(100, spy.size());
        Assert.assertEquals(1, spy.get(0));
        Assert.assertEquals(2, spy.get(1));
        Assert.assertEquals(999, spy.get(999));
    }

    @Test
    public void Test() {
        A a = Mockito.mock(A.class);
        // void 方法才能调用doNothing()
        Mockito.when(a.getName()).thenReturn("bb");
        Assert.assertEquals("bb", a.getName());
        // 等价于Mockito.when(a.getName()).thenCallRealMethod();
        Mockito.doCallRealMethod().when(a).getName();
        Assert.assertEquals("zhangsan", a.getName());
    }

    class A {
        public String getName() {
            return "zhangsan";
        }
    }

    class B {
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getSex(Integer sex) {
            if (sex == 1) {
                return "man";
            } else {
                return "woman";
            }
        }
    }
}
