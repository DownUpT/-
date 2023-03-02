package com.dq.demo.lock;


import sun.misc.Unsafe;

import java.lang.reflect.Field;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.LongAdder;

public class Performance extends Thread {

    private static LongAdder longAdder = new LongAdder();

    private static AtomicInteger atomicInteger = new AtomicInteger(0);

    private static int $synchronized = 0;

    public static volatile int cas = 0;

    private static long casOffset;

    public static Unsafe UNSAFE;

    static {
        try {
            Field theUnsafe = Unsafe.class.getDeclaredField("theUnsafe");
            theUnsafe.setAccessible(true);
            UNSAFE = (Unsafe) theUnsafe.get(null);
            casOffset = UNSAFE.staticFieldOffset(Performance.class.getDeclaredField("cas"));
        } catch (IllegalAccessException | NoSuchFieldException e) {
            e.printStackTrace();
        }
    }

    public void cas() {
        boolean bl = false;
        int tmp;
        while (!bl) {
            tmp = cas;
            bl = UNSAFE.compareAndSwapInt(Performance.class, casOffset, tmp, tmp + 1);
        }
    }

    public void atomicInteger() {
        UNSAFE.getAndAddInt(this, casOffset, 1);
    }

    public void run() {
        int i = 1;
        while (i <= 10000000) {
//        while (i <= 1) {
            //atomicInteger.incrementAndGet();
//            atomicInteger();
//            longAdder.increment();
            cas();
//            synchronized (longAdder) {
//                ++$synchronized;
//            }
            i++;
        }
    }

    public static void main(String[] args) throws InterruptedException {

        long start = System.currentTimeMillis();
        //100个线程
        for (int i = 1; i <= 60; i++) {
            new Performance().start();
        }
        while (Thread.activeCount() > 2) {
            Thread.yield();
            System.out.println(Thread.activeCount());
            TimeUnit.SECONDS.sleep(1);
            System.out.println(cas);
        }

        System.out.println(System.currentTimeMillis() - start);
        System.out.println($synchronized);
        System.out.println(atomicInteger);
        System.out.println(longAdder);
        System.out.println(cas);
    }
}
