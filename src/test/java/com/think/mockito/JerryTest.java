package com.think.mockito;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import java.util.List;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

/**
 * @author chenhaipeng
 * @version 1.0
 * @date 2017/08/21 11:00
 */
public class JerryTest {

    //  当需要整体Mock，只有少部分方法执行真正部分时，选用这种方式
    @Test
    public void callRealMethodTest() {

        Jerry jerry = Mockito.mock(Jerry.class);


        Mockito.doCallRealMethod().when(jerry).goHome();
//        Mockito.doCallRealMethod().when(jerry).doSomeThingB("doSomeThingB");

        jerry.goHome();

        verify(jerry, times(1)).doSomeThingA();
        verify(jerry, times(1)).doSomeThingB("go home");
    }


    // 当需要整体执行真正部分，只有少部分方法执行mock，选用这种方式
    @Test
    public void spyTest() {
        Jerry spyJack = Mockito.spy(new Jerry());
        // 用thenReturn 会走go()方法体，然后将返回值Mock掉
        Mockito.when(spyJack.go("go spy")).thenReturn(false);
        Assert.assertFalse(spyJack.go("go spy"));
        // 用doReturn 不走go()方法体
        Mockito.doReturn(false).when(spyJack).go("if go spy");
        Assert.assertFalse(spyJack.go("if go spy"));
    }


    @Test
    public void argumentCaptorTest() {
        List mock = Mockito.mock(List.class);
        List mock2 = Mockito.mock(List.class);
        mock.add("John");
        mock2.add("Brian");
        mock2.add("Jim");

        ArgumentCaptor argument = ArgumentCaptor.forClass(String.class);

        verify(mock).add(argument.capture());
        assertEquals("John", argument.getValue());

        verify(mock2, times(2)).add(argument.capture());

        assertEquals("Jim", argument.getValue());
        assertArrayEquals(new Object[]{"John","Brian","Jim"},argument.getAllValues().toArray());
    }






}