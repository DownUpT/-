package com.dq.kafka;

import com.bonree.integration.core.opentelemetry.trace.v1.ResourceSpans;
import com.bonree.integration.core.opentelemetry.trace.v1.ScopeSpans;
import com.bonree.integration.core.opentelemetry.trace.v1.Span;
import com.bonree.integration.core.opentelemetry.trace.v1.TracesData;
import com.dq.util.HttpUtils;
import com.google.common.collect.Maps;
import com.google.protobuf.ByteString;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TraceDemo {

    public static void main(String[] args) {
        getDataFromDemo();
    }

    public static void getDataFromDemo() {
        List<Map<String, Object>> demos = generateDemo();

        //for (Map<String, Object> demo : demos) {
        Map<String, Object> demo = demos.get(0);
        Map<String, Object> demo1 = demos.get(1);
            int i = 0;
            // demo 是一个网络设备的调用链信息
            // 首先产生一个服务A
            getA(demo, ++i);

            String c = getC(demo, ++i, demo.get("parentSpanId").toString());

            // 然后产生一个网络设备B
            String spanIdOfB = getB(demo, c);
            String spanIdOfc = getB2(demo1, spanIdOfB);

            //String c = getC(demo, ++i, demo.get("parentSpanId").toString());

            // 最后产生一个服务C
            // String c = getC(demo, ++i, spanIdOfB);

            //getD(demo, i, c);

            //getE(demo, i, c);

            //System.out.println(i);
            //break;
        //}
    }

    private static void getA(Map<String, Object> demo, int i) {
        TracesData.Builder builder = TracesData.newBuilder();
        ResourceSpans.Builder addResourceSpansBuilder = builder.addResourceSpansBuilder();
        com.bonree.integration.core.opentelemetry.resource.v1.Resource.Builder resourceBuilder =
                addResourceSpansBuilder.getResourceBuilder();
        String serviceName = "/msps-fstore/carefullyChose/v1/queryGoodChoiceList.do";
        resourceBuilder.addAttributesBuilder().setKey("service.name").getValueBuilder().setStringValue(serviceName);
        resourceBuilder.addAttributesBuilder().setKey("http.url").getValueBuilder().setStringValue("http://myConfigUAT81:8080/msps-fstore/carefullyChose/v1/queryGoodChoiceList.do");
        ScopeSpans.Builder scopeSpansBuilder = addResourceSpansBuilder.addScopeSpansBuilder();
        Span.Builder spansBuilder = scopeSpansBuilder.addSpansBuilder();
        spansBuilder.setTraceId(ByteString.fromHex(demo.get("traceId").toString()));
        spansBuilder.setSpanId(ByteString.fromHex(demo.get("parentSpanId").toString()));
        spansBuilder.setName(serviceName);
        long startTime = (Long) demo.get("startTime") * 1000000L;
        spansBuilder.setStartTimeUnixNano(startTime);
        spansBuilder.setEndTimeUnixNano(startTime + 1000000L);
        Map<String, String> header = Maps.newHashMap();
        header.put("GUID", "f630aa99-a4a4-4487-a738-d908f1d9815f");
        HttpUtils.INSTANCE.sendPostByte("http://10.241.20.49:4318/v1/traces", header,
                builder.build().toByteArray());
    }

    private static String getB(Map<String, Object> demo, String c) {
        TracesData.Builder builder = TracesData.newBuilder();
        ResourceSpans.Builder addResourceSpansBuilder = builder.addResourceSpansBuilder();
        com.bonree.integration.core.opentelemetry.resource.v1.Resource.Builder resourceBuilder =
                addResourceSpansBuilder.getResourceBuilder();
        String serviceName = demo.get("deviceName").toString();
        resourceBuilder.addAttributesBuilder().setKey("service.name").getValueBuilder().setStringValue(serviceName);
        resourceBuilder.addAttributesBuilder().setKey("transaction_id").getValueBuilder().setStringValue(demo.get("transaction_id").toString());
        resourceBuilder.addAttributesBuilder().setKey("spv").getValueBuilder().setStringValue(demo.get("spv").toString());
        resourceBuilder.addAttributesBuilder().setKey("cap").getValueBuilder().setStringValue(demo.get("cap").toString());
        ScopeSpans.Builder scopeSpansBuilder = addResourceSpansBuilder.addScopeSpansBuilder();
        Span.Builder spansBuilder = scopeSpansBuilder.addSpansBuilder();
        spansBuilder.setTraceId(ByteString.fromHex(demo.get("traceId").toString()));
        String spanId = "cc000022";
        spansBuilder.setSpanId(ByteString.fromHex(spanId));
        spansBuilder.setParentSpanId(ByteString.fromHex(c));
        spansBuilder.setName(serviceName);
        long startTime = (Long) demo.get("startTime") * 1000000L;
        spansBuilder.setStartTimeUnixNano(startTime);
        spansBuilder.setEndTimeUnixNano(startTime + (long) demo.get("duration") * 1000000L);
        Map<String, String> header = Maps.newHashMap();
        header.put("GUID", "f630aa99-a4a4-4487-a738-d908f1d9815f");
        HttpUtils.INSTANCE.sendPostByte("http://10.241.20.49:4318/v1/traces", header,
                builder.build().toByteArray());
        return spanId;
    }

    private static String getB2(Map<String, Object> demo, String c) {
        TracesData.Builder builder = TracesData.newBuilder();
        ResourceSpans.Builder addResourceSpansBuilder = builder.addResourceSpansBuilder();
        com.bonree.integration.core.opentelemetry.resource.v1.Resource.Builder resourceBuilder =
                addResourceSpansBuilder.getResourceBuilder();
        String serviceName = demo.get("deviceName").toString();
        resourceBuilder.addAttributesBuilder().setKey("service.name").getValueBuilder().setStringValue(serviceName);
        resourceBuilder.addAttributesBuilder().setKey("transaction_id").getValueBuilder().setStringValue(demo.get("transaction_id").toString());
        resourceBuilder.addAttributesBuilder().setKey("spv").getValueBuilder().setStringValue(demo.get("spv").toString());
        resourceBuilder.addAttributesBuilder().setKey("cap").getValueBuilder().setStringValue(demo.get("cap").toString());
        ScopeSpans.Builder scopeSpansBuilder = addResourceSpansBuilder.addScopeSpansBuilder();
        Span.Builder spansBuilder = scopeSpansBuilder.addSpansBuilder();
        spansBuilder.setTraceId(ByteString.fromHex(demo.get("traceId").toString()));
        String spanId = "cc000002";
        spansBuilder.setSpanId(ByteString.fromHex(spanId));
        spansBuilder.setParentSpanId(ByteString.fromHex(c));
        spansBuilder.setName(serviceName);
        long startTime = (Long) demo.get("startTime") * 1000000L;
        spansBuilder.setStartTimeUnixNano(startTime);
        spansBuilder.setEndTimeUnixNano(startTime + (long) demo.get("duration") * 1000000L);
        Map<String, String> header = Maps.newHashMap();
        header.put("GUID", "f630aa99-a4a4-4487-a738-d908f1d9815f");
        HttpUtils.INSTANCE.sendPostByte("http://10.241.20.49:4318/v1/traces", header,
                builder.build().toByteArray());
        return spanId;
    }

    private static String getC(Map<String, Object> demo, int i, String spanIdOfB) {
        TracesData.Builder builder = TracesData.newBuilder();
        ResourceSpans.Builder addResourceSpansBuilder = builder.addResourceSpansBuilder();
        com.bonree.integration.core.opentelemetry.resource.v1.Resource.Builder resourceBuilder =
                addResourceSpansBuilder.getResourceBuilder();
        //String serviceName = "service" + i;
        String serviceName = "shelf";
        resourceBuilder.addAttributesBuilder().setKey("service.name").getValueBuilder().setStringValue(serviceName);
        resourceBuilder.addAttributesBuilder().setKey("http.url").getValueBuilder().setStringValue("/msps-fstore");
        ScopeSpans.Builder scopeSpansBuilder = addResourceSpansBuilder.addScopeSpansBuilder();
        Span.Builder spansBuilder = scopeSpansBuilder.addSpansBuilder();
        spansBuilder.setTraceId(ByteString.fromHex(demo.get("traceId").toString()));
        String spanId = "cc000003";
        spansBuilder.setSpanId(ByteString.fromHex(spanId));
        spansBuilder.setParentSpanId(ByteString.fromHex(spanIdOfB));
        spansBuilder.setName(serviceName);
        long startTime = (Long) demo.get("startTime") * 1000000L;
        spansBuilder.setStartTimeUnixNano(startTime);
        spansBuilder.setEndTimeUnixNano(startTime + 7 * 1000000L);
        Map<String, String> header = Maps.newHashMap();
        header.put("GUID", "f630aa99-a4a4-4487-a738-d908f1d9815f");
        HttpUtils.INSTANCE.sendPostByte("http://10.241.20.49:4318/v1/traces", header,
                builder.build().toByteArray());
        return spanId;
    }

    private static String getD(Map<String, Object> demo, int i, String spanIdOfB) {
        TracesData.Builder builder = TracesData.newBuilder();
        ResourceSpans.Builder addResourceSpansBuilder = builder.addResourceSpansBuilder();
        com.bonree.integration.core.opentelemetry.resource.v1.Resource.Builder resourceBuilder =
                addResourceSpansBuilder.getResourceBuilder();
        //String serviceName = "service" + i;
        String serviceName = "outreach";
        resourceBuilder.addAttributesBuilder().setKey("service.name").getValueBuilder().setStringValue(serviceName);
        ScopeSpans.Builder scopeSpansBuilder = addResourceSpansBuilder.addScopeSpansBuilder();
        Span.Builder spansBuilder = scopeSpansBuilder.addSpansBuilder();
        spansBuilder.setTraceId(ByteString.fromHex(demo.get("traceId").toString()));
        String spanId = "cc000004";
        spansBuilder.setSpanId(ByteString.fromHex(spanId));
        spansBuilder.setParentSpanId(ByteString.fromHex(spanIdOfB));
        spansBuilder.setName(serviceName);
        long startTime = (Long) demo.get("startTime") * 1000000L;
        spansBuilder.setStartTimeUnixNano(startTime + 23 * 1000000L);
        spansBuilder.setEndTimeUnixNano(startTime + 25 * 1000000L);
        Map<String, String> header = Maps.newHashMap();
        header.put("GUID", "f630aa99-a4a4-4487-a738-d908f1d9815f");
        HttpUtils.INSTANCE.sendPostByte("http://10.241.20.49:4318/v1/traces", header,
                builder.build().toByteArray());
        return spanId;
    }

    private static String getE(Map<String, Object> demo, int i, String spanIdOfB) {
        TracesData.Builder builder = TracesData.newBuilder();
        ResourceSpans.Builder addResourceSpansBuilder = builder.addResourceSpansBuilder();
        com.bonree.integration.core.opentelemetry.resource.v1.Resource.Builder resourceBuilder =
                addResourceSpansBuilder.getResourceBuilder();
        //String serviceName = "service" + i;
        String serviceName = "Jedis/pexpire";
        resourceBuilder.addAttributesBuilder().setKey("service.name").getValueBuilder().setStringValue(serviceName);
        ScopeSpans.Builder scopeSpansBuilder = addResourceSpansBuilder.addScopeSpansBuilder();
        Span.Builder spansBuilder = scopeSpansBuilder.addSpansBuilder();
        spansBuilder.setTraceId(ByteString.fromHex(demo.get("traceId").toString()));
        String spanId = "cc000008";
        spansBuilder.setSpanId(ByteString.fromHex(spanId));
        spansBuilder.setParentSpanId(ByteString.fromHex(spanIdOfB));
        spansBuilder.setName(serviceName);
        long startTime = (Long) demo.get("startTime") * 1000000L;
        spansBuilder.setStartTimeUnixNano(startTime);
        spansBuilder.setEndTimeUnixNano(startTime + 23 * 1000000L);
        Map<String, String> header = Maps.newHashMap();
        header.put("GUID", "f630aa99-a4a4-4487-a738-d908f1d9815f");
        HttpUtils.INSTANCE.sendPostByte("http://10.241.20.49:4318/v1/traces", header,
                builder.build().toByteArray());
        return spanId;
    }

    public static List<Map<String, Object>> generateDemo() {
        List<Map<String, Object>> demo = new ArrayList<>();
//        Map<String, Object> var1 = new HashMap<>();
//        var1.put("traceId", "0d46bf22-48e2-42b5-88ac-f379e9f3558f".replace("-", ""));
//        var1.put("parentSpanId", "aa0000000001");
//        var1.put("duration", 92L);
//        var1.put("startTime", System.currentTimeMillis());
//        var1.put("transaction_id", "721248579670400848:2274516205061341667:0");
//        var1.put("spv", "app3");
//        var1.put("cap", "cap1");
//        var1.put("deviceName", "[\"app3\", \"cap1\"]");
//        demo.add(var1);

//        Map<String, Object> var2 = new HashMap<>();
//        var2.put("traceId", "f096d7cf-3468-4a10-a53d-9d95262b02da".replace("-", ""));
//        var2.put("parentSpanId", "aa0000000002");
//        var2.put("duration", 179L);
//        var2.put("startTime", System.currentTimeMillis());
//        var2.put("transaction_id", "721248579670400848:2274516385449968099:0");
//        var2.put("spv", "app3");
//        var2.put("cap", "cap1");
//        var2.put("deviceName", "[\"app2\", \"cap1\"]");
//        demo.add(var2);
//
        Map<String, Object> var3 = new HashMap<>();
        var3.put("traceId", "ce1edf98-d437-4da3-ad2f-f7003bf952fa".replace("-", ""));
        var3.put("parentSpanId", "aa0000000003");
        var3.put("duration", 26L);
        var3.put("startTime", System.currentTimeMillis());
        var3.put("transaction_id", "721248579670400848:2274516583018463715:0");
        var3.put("spv", "app3");
        var3.put("cap", "cap1");
        var3.put("deviceName", "[\"app3\", \"cap3\"]");
        demo.add(var3);
//
//
        Map<String, Object> var4 = new HashMap<>();
        var4.put("traceId", "ce1edf98-d437-4da3-ad2f-f7003bf952fa".replace("-", ""));
        var4.put("parentSpanId", "aa0000000004");
        var4.put("duration", 28L);
        var4.put("startTime", System.currentTimeMillis() + 26000L);
        var4.put("transaction_id", "721248579670400848:2274516583018463715:0");
        var4.put("spv", "app3");
        var4.put("cap", "cap1");
        var4.put("deviceName", "[\"app3\", \"cap3\"]");
        demo.add(var4);
        return demo;
    }

}
