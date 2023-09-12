package com.dq.collector.hbase.point;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public interface Buffer {

    int NULL = -1;

    int BOOLEAN_FALSE = 0;
    int BOOLEAN_TRUE = 1;

    byte[] EMPTY = new byte[0];

    String UTF8 = StandardCharsets.UTF_8.name();

    Charset UTF8_CHARSET = StandardCharsets.UTF_8;

    void putPadBytes(byte[] bytes, int totalLength);

    void putPrefixedBytes(byte[] bytes);

    void put2PrefixedBytes(byte[] bytes);

    void put4PrefixedBytes(byte[] bytes);

    void putPadString(String string, int totalLength);

    void putPrefixedString(String string);

    void put2PrefixedString(String string);

    void put4PrefixedString(String string);

    void putByte(byte v);


    void putBoolean(boolean v);


    void putInt(int v);


    /**
     * put value using the variable-length encoding especially for constants
     * the size using variable-length encoding is bigger than using fixed-length int when v is negative.
     * if there are a lot of negative value in a buffer, it's very inefficient.
     * instead use putSVar in that case.
     * putVar compared to putSVar has a little benefit to use a less cpu due to no zigzag operation.
     * it has more benefit to use putSVar whenever v is negative.
     * consume 1~10 bytes ( integer's max value consumes 5 bytes, integer's min value consumes 10 bytes)
     * @param v
     */
    void putVInt(int v);


    /**
     * put value using variable-length encoding
     * useful for same distribution of constants and negatives value
     * consume 1~5 bytes ( integer's max value consumes 5 bytes, integer's min value consumes 5 bytes)

     * @param v
     */
    void putSVInt(int v);


    void putShort(short v);


    void putLong(long v);



    /**
     * put value using the variable-length encoding especially for constants
     * the size using variable-length encoding is bigger than using fixed-length int when v is negative.
     * if there are a lot of negative value in a buffer, it's very inefficient.
     * instead use putSVar in that case.
     * @param v
     */
    void putVLong(long v);



    /**
     * put value using variable-length encoding
     * useful for same distribution of constants and negatives value
     * @param v
     */
    void putSVLong(long v);


    void putDouble(double v);


    /**
     * put value using the variable-length encoding especially for constants
     * the size using variable-length encoding is bigger than using fixed-length int when v is negative.
     * if there are a lot of negative value in a buffer, it's very inefficient.
     * instead use putSVar in that case.
     * @param v
     */
    void putVDouble(double v);


    /**
     * put value using variable-length encoding
     * useful for same distribution of constants and negatives value
     * @param v
     */
    void putSVDouble(double v);

    void putBytes(byte[] v);

    byte getByte(int index);

    byte readByte();

    int readUnsignedByte();

    boolean readBoolean();

    int readInt();

    int readVInt();

    int readSVInt();

    short readShort();

    long readLong();

    long readVLong();

    long readSVLong();

    double readDouble();

    double readVDouble();

    double readSVDouble();

    byte[] readPadBytes(int totalLength);

    String readPadString(int totalLength);

    String readPadStringAndRightTrim(int totalLength);

    byte[] readPrefixedBytes();

    byte[] read2PrefixedBytes();

    byte[] read4PrefixedBytes();

    String readPrefixedString();

    String read2PrefixedString();

    String read4PrefixedString();

    void setByte(int offset, byte value);

    byte[] getBuffer();

    byte[] copyBuffer();

    byte[] getInternalBuffer();

    ByteBuffer wrapByteBuffer();

    void setOffset(int offset);

    int getOffset();

    int remaining();

    boolean hasRemaining();
}
