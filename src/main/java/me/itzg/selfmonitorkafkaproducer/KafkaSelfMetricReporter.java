package me.itzg.selfmonitorkafkaproducer;

import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Tag;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.common.metrics.KafkaMetric;
import org.apache.kafka.common.metrics.MetricsReporter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.annotation.Priority;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author Geoff Bourne
 * @since Apr 2018
 */
@Slf4j
public class KafkaSelfMetricReporter implements MetricsReporter {

    private static final String PREFIX = "kafka-";
    private static final List<String> EXCLUDES = Arrays.asList(
            "version",
            "commit-id"
    );

    @Override
    public void init(List<KafkaMetric> list) {
        log.debug("init: {}", list);

        list.forEach(this::registerMetric);
    }

    @Override
    public void metricChange(KafkaMetric kafkaMetric) {
        log.debug("metricChange: {}", kafkaMetric.metricName());

        registerMetric(kafkaMetric);
    }

    private void registerMetric(KafkaMetric kafkaMetric) {
        if (EXCLUDES.contains(kafkaMetric.metricName().name())) {
            log.debug("Excluding metric: {}", kafkaMetric.metricName());
            return;
        }

        final List<Tag> tags = Stream.concat(
                kafkaMetric.metricName().tags().entrySet().stream()
                        .map(entry -> Tag.of(entry.getKey(), entry.getValue())),
                Stream.of(Tag.of("group", kafkaMetric.metricName().group()))
        )
                .collect(Collectors.toList());

        SpringHook.meterRegistry.gauge(PREFIX+kafkaMetric.metricName().name(), tags,
                kafkaMetric, value -> {
                    final Object rawValue = value.metricValue();
                    if (rawValue instanceof Number) {
                        final double v = ((Number) rawValue).doubleValue();
                        if (Double.isInfinite(v) || Double.isNaN(v)) {
                            return 0;
                        }
                        return v;
                    }
                    else {
                        log.trace("Metric {} provided non-numerical value", kafkaMetric.metricName());
                        return 0;
                    }
                });
    }

    @Override
    public void metricRemoval(KafkaMetric kafkaMetric) {
        log.debug("metricRemoval: {}", kafkaMetric.metricName());

    }

    @Override
    public void close() {
        log.debug("close");
    }

    @Override
    public void configure(Map<String, ?> map) {
        log.debug("configure: {}", map);
    }

    @Component @Priority(10)
    public static class SpringHook {
        static MeterRegistry meterRegistry;

        @Autowired
        public SpringHook(MeterRegistry meterRegistry) {
            SpringHook.meterRegistry = meterRegistry;
        }
    }
}
