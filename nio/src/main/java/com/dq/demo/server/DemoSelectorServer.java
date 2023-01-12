package com.dq.demo.server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;

public class DemoSelectorServer {

    private static final Logger LOG = LoggerFactory.getLogger(DemoSelectorServer.class);

    /**
     * accept 在有连接请求时触发
     * connect 与客户端建立连接之后触发
     * read 读事件
     * write 可写事件
     */
    public static void main(String[] args) throws IOException {
        // 创建selector,管理多个channel
        Selector selector = Selector.open();
        ServerSocketChannel ssc = ServerSocketChannel.open();
        ssc.configureBlocking(false);

        // 将channel注册到selector上
        SelectionKey sscKey = ssc.register(selector, 0, null);
        sscKey.interestOps(SelectionKey.OP_ACCEPT);
        ssc.bind(new InetSocketAddress(9527));

        while (true) {
            selector.select();
            Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
            while (iterator.hasNext()) {
                SelectionKey key = iterator.next();
                // 区分事件类型
                if (key.isAcceptable()) {
                    ServerSocketChannel channel = (ServerSocketChannel) key.channel();
                    SocketChannel sc = channel.accept();
                    sc.configureBlocking(false);
                    SelectionKey scKey = sc.register(selector, 0, null);
                    scKey.interestOps(SelectionKey.OP_READ);
                    LOG.info("scKey: {}", scKey);
                } else if (key.isReadable()) {
                    try {
                        LOG.info("READ: {}", key);
                        SocketChannel channel = (SocketChannel) key.channel();
                        ByteBuffer buffer = ByteBuffer.allocate(4);
                        int read = channel.read(buffer);
                        if (read == -1) {
                            key.cancel();
                        } else {
                            buffer.flip();
                            LOG.info("content is {}", StandardCharsets.UTF_8.decode(buffer));
                            //buffer.clear();45
                        }
                    } catch (IOException e) {
                        key.cancel();
                        e.printStackTrace();
                    }
                }
                iterator.remove();
            }
        }
    }
}
