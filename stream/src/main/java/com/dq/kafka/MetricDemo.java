package com.dq.kafka;

import com.dq.util.JacksonUtils;
import com.google.common.collect.Lists;
import lombok.Getter;
import org.apache.commons.lang3.RandomStringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.TimeUnit;

@Getter
public class MetricDemo {
    String metricName;
    String metricType;
    double metricValue;
    String metricDesc;
    String metricUnit;
    String hostname;
    String ip;
    Map<String, String> batchTag;
    long time;

    private static final Random RANDOM = new Random();

    public void setMetricName(String metricName) {
        this.metricName = metricName;
    }

    public void setMetricType(String metricType) {
        this.metricType = metricType;
    }

    public void setMetricValue(double metricValue) {
        this.metricValue = metricValue;
    }

    public void setMetricDesc(String metricDesc) {
        this.metricDesc = metricDesc;
    }

    public void setHostname(String hostname) {
        this.hostname = hostname;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public void setBatchTag(Map<String, String> batchTag) {
        this.batchTag = batchTag;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public void setMetricUnit(String metricUnit) {
        this.metricUnit = metricUnit;
    }


    public static MetricDemo randomMetric() {
        MetricDemo metricDemo = new MetricDemo();
        metricDemo.setMetricDesc(RandomStringUtils.random(20, "abcdefghigklmnopqrstuvwxyz"));
        String metricName = createMetricName();
        metricDemo.setMetricName(metricName);
        metricDemo.setMetricType(METRIC_TYPE.get(RANDOM.nextInt(8)));
        if (metricName.contains("cpu")) {
            metricDemo.setMetricValue(RANDOM.nextDouble());
            metricDemo.setMetricUnit("");
        } else if (metricName.contains("memory")) {
            metricDemo.setMetricValue(RANDOM.nextInt(16 * 1024 ));
            metricDemo.setMetricUnit("MB");
        } else if (metricName.contains("disk")) {
            metricDemo.setMetricValue(RANDOM.nextInt(1024 * 1024));
            metricDemo.setMetricUnit("MB");
        }
        metricDemo.setHostname(RandomStringUtils.random(5, "abcdefghigklmnopqrstuvwxyz"));
        metricDemo.setIp(ip());
        metricDemo.setBatchTag(createMap());
        metricDemo.setTime(System.currentTimeMillis());
        return metricDemo;
    }

    public static String createMetricName() {
        String point = ".";
        int secondIndex = RANDOM.nextInt(3);
        int thirdIndex = RANDOM.nextInt(3);
        return FIRST_ + point + SECOND_.get(secondIndex) + point + THIRD_.get(thirdIndex);
    }

    public static Map<String, String> createMap() {
        Map<String, String> labels = new HashMap<>();
        labels.put("group", GROUP_LIST.get(RANDOM.nextInt(6)));
        labels.put("personInCharge", PERSON_IN_CHARGE.get(RANDOM.nextInt(7)));
        return labels;
    }

    public static String ip() {
        String point = ".";
        int first = RANDOM.nextInt(256);
        int second = RANDOM.nextInt(256);
        int third = RANDOM.nextInt(256);
        int fourth = RANDOM.nextInt(256);
        return first + point + second + point + third + point + fourth;
    }

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 100; i++) {
            System.out.println(JacksonUtils.obj2JsonString(randomMetric()));
            TimeUnit.MILLISECONDS.sleep(10);
        }
    }

    public static final String FIRST_ = "system";
    public static final List<String> SECOND_ = Lists.newArrayList("cpu", "memory", "disk");
    public static final List<String> THIRD_ = Lists.newArrayList("used", "free", "total");
    public static final List<String> GROUP_LIST = Lists.newArrayList(
            "中台能力重心",
            "APM",
            "DEM",
            "CLOUD",
            "ONE",
            "BIGDATA");

    public static final List<String> PERSON_IN_CHARGE = Lists.newArrayList(
            "Mr.L",
            "Ms.W",
            "Mr.Zg",
            "Ms.C",
            "Mr.K",
            "Ms.A",
            "Ms.B");

    public static final List<String> METRIC_TYPE = Lists.newArrayList(
            "unknown",
            "gauge",
            "counter",
            "stateset",
            "info",
            "histogram",
            "gaugehistogram",
            "summary");
}
