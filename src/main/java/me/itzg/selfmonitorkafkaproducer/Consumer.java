package me.itzg.selfmonitorkafkaproducer;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * @author Geoff Bourne
 * @since Apr 2018
 */
@Component
@Slf4j
public class Consumer {
    @KafkaListener(topics = "${consumer-topic:test}")
    public void listen(ConsumerRecord<String,String> cr) throws Exception {
        log.info("Consumed: {}", cr);
    }
}
