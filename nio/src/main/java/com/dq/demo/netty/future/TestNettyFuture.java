package com.dq.demo.netty.future;

import io.netty.channel.EventLoop;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

public class TestNettyFuture {

    private static final Logger LOGGER = LoggerFactory.getLogger(TestNettyFuture.class);

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        NioEventLoopGroup group = new NioEventLoopGroup();

        EventLoop eventLoop = group.next();

        Future<Integer> future = eventLoop.submit(() -> {
            LOGGER.info("执行netty计算");
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return 50;
        });

        LOGGER.info("等待结果");
        //LOGGER.info("结果是： {}.", future.get());
        future.addListener(new GenericFutureListener<Future<? super Integer>>() {
            @Override
            public void operationComplete(Future<? super Integer> future) throws Exception {
                LOGGER.info("接受结果: {}.", future.getNow());
            }
        });
        //TimeUnit.SECONDS.sleep(1);
    }
}
