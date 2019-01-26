package com.springgears.scheduling.config;

import org.springframework.boot.task.TaskSchedulerBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author Igor Sukach
 * @since 26/01/2019
 */
@Configuration
@EnableScheduling
public class SchedulingConfiguration {

    @Bean
    public TaskScheduler taskScheduler() {
        return new TaskSchedulerBuilder()
                .poolSize(10)
                .threadNamePrefix("custom-")
                .build();
    }
}
