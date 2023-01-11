package com.dq.demo.client;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;

public class DemoBlockClient {
  public static void main(String[] args) throws IOException {
    SocketChannel socketChannel = SocketChannel.open();
    socketChannel.connect(new InetSocketAddress("localhost", 9527));
    socketChannel.write(ByteBuffer.wrap("中国o".getBytes(StandardCharsets.UTF_8)));
    socketChannel.close();
    System.out.println("waiting");

  }
}
