package com.dq.demo.server;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class DemoNoBlockServer {
    public static void main(String[] args) throws IOException {
        ByteBuffer buffer = ByteBuffer.allocate(16);
        ServerSocketChannel serverChannel = ServerSocketChannel.open();
        serverChannel.configureBlocking(false); // 切换为非阻塞模式
        serverChannel.bind(new InetSocketAddress(9527));
        List<SocketChannel> channels = new ArrayList<>();
        while (true) {
            //System.out.println("connecting...");
            SocketChannel channel = serverChannel.accept();
            if (channel != null) {
                channel.configureBlocking(false);
                System.out.println("connected to " + channel.toString());
                channels.add(channel);
            }

            for (SocketChannel socketChannel : channels) {
                int read = socketChannel.read(buffer);
                if (read > 0) {
                    buffer.flip();
                    System.out.println(StandardCharsets.UTF_8.decode(buffer));
                    buffer.clear();
                    System.out.println("after read..." + socketChannel);
                }
            }
        }

    }
}
