package com.dq.demo.server;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class DemoBlockServer {
    public static void main(String[] args) throws IOException {
        ServerSocketChannel serverChannel = ServerSocketChannel.open();
        ByteBuffer buffer = ByteBuffer.allocate(16);
        serverChannel.bind(new InetSocketAddress(9527));

        List<SocketChannel> channels = new ArrayList<>();

        while (true) {
            System.out.println("connecting...");
            SocketChannel channel = serverChannel.accept();

            channels.add(channel);

            System.out.println("connected to " + channel.toString());

            for (SocketChannel socketChannel : channels) {
                System.out.println("before read...");
                channel.read(buffer);
                buffer.flip();
                System.out.println(StandardCharsets.UTF_8.decode(buffer));
                buffer.clear();
                System.out.println("after read...");
            }
        }

    }
}
