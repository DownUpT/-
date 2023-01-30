package com.dq.demo.netty.future;

import com.dq.demo.netty.HelloServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class TestJdkFuture {

    private static final Logger LOGGER = LoggerFactory.getLogger(TestJdkFuture.class);

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService service = Executors.newFixedThreadPool(1);

        Future<Integer> future = service.submit(() -> {
            LOGGER.info("执行计算");
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return 50;
        });

        LOGGER.info("等待结果");
        LOGGER.info("结果是： {}.", future.get());
    }
}
