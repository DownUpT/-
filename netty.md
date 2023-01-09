# Netty

## 1.NIO
NIO(New-IO/No-blocking IO)

### 1.NIO三大组件
### 1.1 Channel & Buffer
Channel是一个读写数据的双向通道，取别与常用的stream比如InputStream和OutputStream都是单向的，可以将Channel的数据读入Buffer，或者是将Buffer的数据写入channel，channel比stream更为底层：
- 常见的channel：
- FileChannel
- DatagramChannel
- SocketChannel
- ServerSocketChannel

Buffer是用来缓冲读写数据，常见的Buffer有：
- ByteBuffer
    - MappedByteBuffer
    - DirectByteBuffer
    - HeapByteBuffer
- ShortBuffer
- IntBuffer
- LongBuffer
- DoubleBuffer
- CharBuffer

### 1.2 Selector
古早版本服务器多线程设计


### 1.3 短连接和长连接

Selector适合连接数多，但是流量底的场景

