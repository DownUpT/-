package com.dq.demo.netty.future;

import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.util.concurrent.DefaultPromise;
import io.netty.util.concurrent.Promise;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

public class TestNettyPromise {

    private static final Logger LOGGER = LoggerFactory.getLogger(TestNettyPromise.class);

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        Promise<Integer> promise = new DefaultPromise<>(new NioEventLoopGroup().next());

        new Thread(() -> {
            LOGGER.info("执行promise计算");
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            promise.setSuccess(80);
        }).start();


        LOGGER.info("等待结果");
        LOGGER.info("结果是： {}.", promise.get());

    }
}
