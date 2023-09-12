package com.dq.stream;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;

public class KafkaProducerUtil {
    private static final String PASSWORD = "bonree123";
    private KafkaProducerUtil() {

    }

    public static KafkaProducer<String, String> getProducer(String bootstrapServer) {
        Properties properties = new Properties();
        properties.setProperty("bootstrap.servers", bootstrapServer);
        properties.setProperty("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        properties.setProperty("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        properties.setProperty("security.protocol", "SSL");
        properties.put("ssl.truststore.location", "C:\\Users\\bonree\\Desktop\\201-kafka\\kafka.truststore.jks");
        properties.put("ssl.truststore.password", PASSWORD);
        properties.put("ssl.keystore.location", "C:\\Users\\bonree\\Desktop\\201-kafka\\kafka.keystore.jks");
        properties.put("ssl.keystore.password", PASSWORD);
        properties.put("ssl.key.password", PASSWORD);
        properties.put("ssl.endpoint.identification.algorithm", "");
        return new KafkaProducer<>(properties, new StringSerializer(), new StringSerializer());
    }

    public static ProducerRecord<String, String> createRecord(String locustPerf, String message) {
        return new ProducerRecord<>(locustPerf, message);
    }
}
