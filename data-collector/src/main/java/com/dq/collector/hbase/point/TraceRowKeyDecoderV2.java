package com.dq.collector.hbase.point;

import com.dq.collector.hbase.BytesUtils;
import com.navercorp.pinpoint.common.buffer.FixedBuffer;
import com.navercorp.pinpoint.common.server.bo.SpanBo;
import com.navercorp.pinpoint.common.server.bo.serializer.trace.v2.SpanDecoderV0;
import com.navercorp.pinpoint.common.server.bo.serializer.trace.v2.SpanDecodingContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Objects;

public class TraceRowKeyDecoderV2 {
    private static final Logger LOGGER = LoggerFactory.getLogger(TraceRowKeyDecoderV2.class);
    public static final int AGENT_ID_MAX_LEN = 24;
    public static final int DISTRIBUTE_HASH_SIZE = 1;

    private final int distributeHashSize;


    public TraceRowKeyDecoderV2() {
        this(DISTRIBUTE_HASH_SIZE);
    }

    public TraceRowKeyDecoderV2(int distributeHashSize) {
        this.distributeHashSize = distributeHashSize;
    }

    public TransactionId decodeRowKey(byte[] rowkey) {
        Objects.requireNonNull(rowkey, "rowkey");
        return readTransactionId(rowkey, distributeHashSize);
    }

    private TransactionId readTransactionId(byte[] rowKey, int offset) {

        String agentId = BytesUtils.toStringAndRightTrim(rowKey, offset, AGENT_ID_MAX_LEN);
        long agentStartTime = BytesUtils.bytesToLong(rowKey, offset + AGENT_ID_MAX_LEN);
        long transactionSequence = BytesUtils.bytesToLong(rowKey, offset + BytesUtils.LONG_BYTE_LENGTH + AGENT_ID_MAX_LEN);

        return new TransactionId(agentId, agentStartTime, transactionSequence);
    }

    public Span readQualifier(byte[] qualifier) {
        Span span = new Span();
        FixedBuffer2 buffer = new FixedBuffer2(qualifier);
        span.setApplicationID(buffer.readPrefixedString());
        span.setAgentId(buffer.readPrefixedString());
        span.setAgentStartTime(buffer.readVLong());
        if (!buffer.hasRemaining()) {
            // spanEventList.size() == 0
            return span;
        }
        int firstSpanEventSequence = buffer.readSVInt();
        //if (firstSpanEventSequence < 0) {
        //LOGGER.warn("sequence overflow. firstSpanEventSequence:{} basicSpan:{}", firstSpanEventSequence, span);
        //}
        span.setLocalAsyncIdBo(readQualifierLocalAsyncIdBo(buffer));
        return span;
    }

    private LocalAsyncIdBo readQualifierLocalAsyncIdBo(Buffer buffer) {
        final byte bitField = buffer.readByte();
        final int asyncId = buffer.readInt();
        final int asyncSequence = buffer.readVInt();
        return new LocalAsyncIdBo(asyncId, asyncSequence);
    }

    public MayBeValue readSpanValue2(byte[] rowValue) {
        FixedBuffer buffer = new FixedBuffer(rowValue);
        MayBeValue span = new MayBeValue();
        final byte version = buffer.readByte();

        span.setVersion(version);

        byte bitField = buffer.readByte();

        final short serviceType = buffer.readShort();
        span.setServiceType(serviceType);

        switch (BitFieldUtils.getBit(bitField, 0)) {
            case 0:
                span.setApplicationServiceType(serviceType);
                break;
            case 1:
                span.setApplicationServiceType(buffer.readShort());
                break;
            default:
                throw new IllegalStateException("applicationServiceType");
        }

        if (!isRoot(bitField)) {
            span.setParentSpanId(buffer.readLong());
        } else {
            span.setParentSpanId(-1);
        }


        final long startTimeDelta = buffer.readVLong();
        final long startTime = System.currentTimeMillis() - startTimeDelta;
        span.setStartTime(startTime);
        span.setElapsed(buffer.readVInt());

        try {


            span.setRpc(buffer.readPrefixedString());
            span.setEndPoint(buffer.readPrefixedString());
            span.setRemoteAddr(buffer.readPrefixedString());
            span.setApiId(buffer.readSVInt());
            System.out.println("+++++++++++++++++errorCode:" + buffer.readInt());
            System.out.println("+++++++++++++++++exceptionId:" + buffer.readSVInt());
            System.out.println("+++++++++++++++++exceptionMessage:" + buffer.readPrefixedString());
            System.out.println("+++++++++++++++++flag:" + buffer.readShort());
            System.out.println("+++++++++++++++++transaction info:" + buffer.readByte());
            span.setAcceptorHost(buffer.readPrefixedString());

        } catch (Exception e) {

        }
        return span;
    }

    public SpanBo readSpanValue(byte[] rowValue) {
        FixedBuffer buffer = new FixedBuffer(rowValue);
        SpanDecoderV0 spanDecoder = new SpanDecoderV0();
        SpanBo spanBo = new SpanBo();
        spanDecoder.readSpanValue(buffer, spanBo, new SpanDecodingContext());
        LOGGER.info("spanBo:{}", spanBo);
        return spanBo;
    }

    public boolean isRoot(int bitField) {
        return testBit(bitField);
    }

    private boolean testBit(int bitField) {
        return BitFieldUtils.testBit(bitField, 1);
    }
}
