package com.dq.collector.change;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

public class TestChangeProto {

    static ChangeField changeField;

    @BeforeAll
    public static void initPb() {
        ChangeField.Builder builder = ChangeField.newBuilder();
        builder.addKeyValues(KeyValue.newBuilder().setKey("first").setValue("1").build());
        builder.addKeyValues(KeyValue.newBuilder().setKey("second").setValue("2").build());
        changeField = builder.build();
    }

    @Test
    void testAdd() {
        List<KeyValue> keyValuesList = changeField.getKeyValuesList();
        for (KeyValue keyValue : keyValuesList) {
            System.out.println("before:" + keyValue.getKey() + ":" + keyValue.getValue());
        }

        ChangeField.Builder builder = ChangeField.newBuilder(changeField);
        builder.addKeyValues(KeyValue.newBuilder().setKey("third").setValue("3").build());

        ChangeField build = builder.build();
        for (KeyValue keyValue : build.getKeyValuesList()) {
            System.out.println("after:" + keyValue.getKey() + ":" + keyValue.getValue());
        }

        int serializedSize = build.getSerializedSize();
        System.out.println(serializedSize);
        Assertions.assertEquals(3, build.getKeyValuesList().size());
    }
}
