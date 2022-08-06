package br.com.alura.ecommerce;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;

public interface ConsumerFunction<T> {
    void consume(ConsumerRecord<String, T> record);
}
