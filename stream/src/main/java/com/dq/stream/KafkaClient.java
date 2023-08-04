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
        Properties properties = new Properties();
        properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "10.241.130.152:9092");
        properties.put(ConsumerConfig.GROUP_ID_CONFIG, "LOG_GROUP_d7b4855573b04f4989e7e52ab22b161b");
        properties.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "latest");
        try (KafkaConsumer<String, String> consumer = new KafkaConsumer<>(properties, new StringDeserializer(), new StringDeserializer())) {
            consumer.subscribe(Collections.singleton("BONREE_ONE_CONTROLLER_ZABBIX_METRIC_TOPIC_2"));
            int size = 0;
            while (true) {
                ConsumerRecords<String, String> poll = consumer.poll(Duration.of(2, ChronoUnit.SECONDS));
                for (ConsumerRecord<String, String> next : poll) {
                    System.out.println("key: " + next.key());
                    System.out.println("value: " + next.value());
                    System.out.println(size++);
                    if (size >= 1) {
                        break;
                    }
                }
                consumer.commitAsync();
            }
        }


    }
}
