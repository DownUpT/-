package com.dq.demo.netty.eventLoop;

import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.util.concurrent.Future;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

public class TestEventLoop {

    private static final Logger LOGGER = LoggerFactory.getLogger(TestEventLoop.class);

    public static void main(String[] args) {
        EventLoopGroup nioGroup = new NioEventLoopGroup(2);

        for (int i = 0; i < 4; i++) {
            LOGGER.info("Group is: {}", nioGroup.next());
        }

        nioGroup.next().submit(() -> LOGGER.info("普通任务提交"));
        nioGroup.next().execute(() -> LOGGER.info("普通任务执行"));

        nioGroup.next().scheduleAtFixedRate(() -> LOGGER.info("延迟0s, 定时1s定时任务设置"), 0, 1, TimeUnit.SECONDS);

        LOGGER.info("main");

    }
}
