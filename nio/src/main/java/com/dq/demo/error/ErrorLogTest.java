package com.dq.demo.error;

import com.dq.demo.netty.HelloServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

public class ErrorLogTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(ErrorLogTest.class);

    public static void main(String[] args) {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(1, 1, 10L, TimeUnit.SECONDS, new LinkedBlockingQueue<>());
        AtomicBoolean flag = new AtomicBoolean(true);
        LOGGER.info("START");
        executor.execute(() -> {
            try {
                while (flag.get()) {
//                    throw new NoClassDefFoundError();
                }
            } finally {
                LOGGER.info("end");
            }
        });

        new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(25);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            flag.set(false);
            LOGGER.info("切换");
        }).start();
    }
}
