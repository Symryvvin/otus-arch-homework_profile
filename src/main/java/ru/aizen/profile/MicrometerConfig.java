package ru.aizen.profile;

import io.micrometer.core.instrument.Meter;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.config.MeterFilter;
import io.micrometer.core.instrument.distribution.DistributionStatisticConfig;
import org.springframework.boot.actuate.autoconfigure.metrics.MeterRegistryCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;

@Configuration
public class MicrometerConfig {

    @Bean
    MeterRegistryCustomizer<MeterRegistry> metricsCommonTag() {
        return registry -> registry.config().meterFilter(
                new MeterFilter() {
                    @Override
                    public DistributionStatisticConfig configure(Meter.Id id, DistributionStatisticConfig config) {
                        if (id.getType() == Meter.Type.TIMER && id.getName().matches("^(http|hystrix){1}.*")) {
                            return DistributionStatisticConfig.builder()
                                    .percentilesHistogram(true)
                                    .percentiles(0.5, 0.90, 0.95, 0.99)
                                    .sla(Duration.ofMillis(50).toNanos(),
                                            Duration.ofMillis(100).toNanos(),
                                            Duration.ofMillis(200).toNanos(),
                                            Duration.ofMillis(1).toNanos(),
                                            Duration.ofMillis(5).toNanos())
                                    .minimumExpectedValue(Duration.ofMillis(1).toNanos())
                                    .maximumExpectedValue(Duration.ofMillis(5).toNanos())
                                    .build()
                                    .merge(config);
                        } else {
                            return config;
                        }
                    }
                });
    }
}
