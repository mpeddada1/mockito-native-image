package com.example;

import static com.google.common.truth.Truth.assertThat;
import org.junit.Test;
import org.mockito.Mockito;

public class MyMockitoTest {

    @Test
    public void testMethod(){
        Sample2 sample = (Sample2)Mockito.mock(Sample2.class);
        org.mockito.Mockito.when(sample.testMethod()).thenReturn("Hello Graal!");
        System.out.println(sample.getClass());
        System.out.println(sample.testMethod());
        assertThat("hi").isEqualTo("hi");
    }
}

