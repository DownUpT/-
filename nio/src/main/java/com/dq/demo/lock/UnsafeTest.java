package com.dq.demo.lock;

import sun.misc.Unsafe;

import java.lang.reflect.Field;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class UnsafeTest extends Thread {

    private static Unsafe unsafe;
    private static long valueOffset;
    private static AtomicInteger atomicInteger = new AtomicInteger(0);
    private static Cas CA = new Cas(0);

    static {
        try {
            Field theUnsafe = Unsafe.class.getDeclaredField("theUnsafe");
            theUnsafe.setAccessible(true);
            unsafe = (Unsafe) theUnsafe.get(null);
            valueOffset = unsafe.staticFieldOffset(UnsafeTest.class.getDeclaredField("cas"));
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    private static volatile int cas = 0;

    public void cas() {
        boolean bl = false;
        int tmp;
        while (!bl) {
            tmp = cas;
            System.out.println(unsafe.getIntVolatile(UnsafeTest.class, valueOffset));
            System.out.println(this);
            bl = unsafe.compareAndSwapInt(UnsafeTest.class, valueOffset, tmp, tmp + 1);
        }
    }

    public void run() {
        int i = 0;
        // 10000000
        while (i < 1000000) {
//            atomicInteger.getAndIncrement();
//            cas();
            CA.cas(1);
            i++;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        long start = System.currentTimeMillis();

        for (int i = 0; i < 60; i++) {
            new UnsafeTest().start();
        }

        while (Thread.activeCount() > 2) {
            Thread.yield();
        }
        System.out.println("cost: " + (System.currentTimeMillis() - start));
        System.out.println("number: " + atomicInteger.get());
        System.out.println("number: " + cas);
        System.out.println("number: " + CA.getValue());

        ConcurrentHashMap<String, String> map = new ConcurrentHashMap<>();

    }

}

class Cas {
    private static final Unsafe unsafe;

    static {
        try {
            Field theUnsafe = Unsafe.class.getDeclaredField("theUnsafe");
            theUnsafe.setAccessible(true);
            unsafe = (Unsafe) theUnsafe.get(null);
        } catch (IllegalAccessException | NoSuchFieldException e) {
            throw new Error(e);
        }

    }

    private static final long valueOffset;

    static {
        try {
            valueOffset = unsafe.objectFieldOffset
                    (AtomicInteger.class.getDeclaredField("value"));
        } catch (Exception e) {
            throw new Error(e);
        }
    }

    private volatile int value;

    public Cas() {
        this(0);
    }

    public Cas(int initValue) {
        this.value = initValue;
    }

    public void cas(int delta) {
//        unsafe.getAndAddInt(this, valueOffset, delta);

        int var5;
        do {
            var5 = unsafe.getIntVolatile(this, valueOffset);
        } while (!unsafe.compareAndSwapInt(this, valueOffset, var5, var5 + delta));
//        System.out.println("middle: " + (middle - nanoTime) + " end: " + (System.nanoTime() - middle));
    }

    public int getValue() {
        return value;
    }
    /**
     *  a  sdsd sa
     *  a  riewhio
     *  a  sdfhjldjfl
     *
     *
     */
}

