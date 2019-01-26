package com.springgears.scheduling.tasks;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @author Igor Sukach
 * @since 26/01/2019
 */
@Slf4j
@Component
public class ScheduledTasks {

    @Scheduled(fixedRate = 1000)
    public void scheduleWithFixedRate() {
        log.info("Fixed rate scheduler - called each second");
    }


    @Scheduled(fixedRate = 1000)
    public void scheduleWithFixedRate2() {
        log.info("Fixed rate 2 scheduler - called each second");
    }


    @Scheduled(fixedRate = 1000)
    public void scheduleWithFixedRate3() {
        log.info("Fixed rate 3 scheduler - called each second");
    }

    @Scheduled(fixedDelay = 2000, initialDelay = 1000)
    public void fixedDelay() {
        log.info("Fixed delay scheduler - delayed for a second, " +
                "then called two seconds after previous one finishes");
    }

    @Scheduled(cron = "0 0 12 * * ?")
    public void cron() {
        log.info("Cron scheduler - called every day at 12PM");
    }
}
