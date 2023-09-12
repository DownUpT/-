package com.dq.stream;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.Collections;
import java.util.Properties;

public class KafkaClient {


    public static void main(String[] args) {
        System.setProperty("org.jboss.security.ignoreHttpsHost", "true");
        Properties properties = new Properties();
        properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "10.241.3.201:9093");
        properties.put(ConsumerConfig.GROUP_ID_CONFIG, "test_data_off_3");
        properties.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
        properties.setProperty("security.protocol", "SSL");
        properties.put("ssl.truststore.location", "C:\\Users\\bonree\\Desktop\\201-kafka\\kafka.truststore.jks");
        properties.put("ssl.truststore.password", "bonree123");
        properties.put("ssl.keystore.location", "C:\\Users\\bonree\\Desktop\\201-kafka\\kafka.keystore.jks");
        properties.put("ssl.keystore.password", "bonree123");
        properties.put("ssl.key.password", "bonree123");
        properties.put("ssl.endpoint.identification.algorithm", "");
        //properties.put("ssl.protocol", "TLSv1.3");
        //properties.put("ssl.enabled.protocols", "TLSv1.2, TLSv1.3");
        try (KafkaConsumer<String, String> consumer = new KafkaConsumer<>(properties, new StringDeserializer(), new StringDeserializer())) {
            consumer.subscribe(Collections.singleton("test_cluster3"));
            int size = 0;
            lo: while (true) {
                ConsumerRecords<String, String> poll = consumer.poll(Duration.of(2, ChronoUnit.SECONDS));
                for (ConsumerRecord<String, String> next : poll) {
                    System.out.println("=============key: " + next.key());
                    System.out.println("=============value: " + next.value());
                    System.out.println(size++);
                    if (size >= 1) {
                        break lo;
                    }
                }
                consumer.commitAsync();
            }
        }


    }
}
