package me.itzg.selfmonitorkafkaproducer;

import com.google.common.util.concurrent.RateLimiter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.Lifecycle;
import org.springframework.context.SmartLifecycle;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.concurrent.Executors;

/**
 * @author Geoff Bourne
 * @since Apr 2018
 */
@Component
@Slf4j
public class Producer implements SmartLifecycle {
    private final RateLimiter rateLimiter;
    private KafkaTemplate<String, String> kafkaTemplate;
    private boolean running;

    @Autowired
    public Producer(KafkaTemplate<String,String> kafkaTemplate,
                    Properties properties) {
        this.kafkaTemplate = kafkaTemplate;
        rateLimiter = RateLimiter.create(properties.rateLimit);
    }

    @Override
    public void start() {
        log.info("Starting");
        Executors.newSingleThreadExecutor()
                .submit(this::run);
        running = true;
    }

    private void run() {
        while (running) {
            rateLimiter.acquire();

            kafkaTemplate.send("test", Instant.now().toString());
        }
    }

    @Override
    public void stop() {
        running = false;
    }

    @Override
    public boolean isRunning() {
        return running;
    }

    @Override
    public boolean isAutoStartup() {
        return true;
    }

    @Override
    public void stop(Runnable runnable) {
        running = false;
        runnable.run();
    }

    @Override
    public int getPhase() {
        return Integer.MAX_VALUE;
    }

    @Component
    @ConfigurationProperties("app.producer")
    public static class Properties {
        double rateLimit = 0.5;
    }
}
