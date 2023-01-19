package com.example;

import org.mockito.Mockito;

public class Sample {

    public Sample() {
    }

    public static void main(String[] var0) {
        Sample var1 = (Sample)Mockito.mock(Sample.class);
        Mockito.when(var1.m()).thenReturn("Hello Graal!");
        System.out.println(var1.getClass());
        System.out.println(var1.m());
    }

    public String m() {
        return null;
    }
}
