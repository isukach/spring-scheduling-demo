package com.springgears.scheduling.tasks;

import java.time.Instant;
import java.time.ZoneId;
import java.util.Date;
import javax.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.Trigger;
import org.springframework.scheduling.TriggerContext;
import org.springframework.stereotype.Component;

/**
 * @author Igor Sukach
 * @since 26/01/2019
 */
@Slf4j
@Component
public class ManualScheduler {

    private final TaskScheduler taskScheduler;

    public ManualScheduler(TaskScheduler taskScheduler) {
        this.taskScheduler = taskScheduler;
    }

    @PostConstruct
    public void schedule() {
        taskScheduler.schedule(() -> log.info("Manually scheduled"),
                Date.from(Instant.now().plusSeconds(10)));
    }

    @PostConstruct
    public void scheduleByTrigger() {
        taskScheduler.schedule(() -> log.info("Manually scheduled with trigger"),
                new EveryFiveSecondsWithDelayTrigger(3));
    }

    public class EveryFiveSecondsWithDelayTrigger implements Trigger {

        private int delaySeconds;

        public EveryFiveSecondsWithDelayTrigger(int delaySeconds) {
            this.delaySeconds = delaySeconds;
        }

        @Override
        public Date nextExecutionTime(TriggerContext triggerContext) {
            Date lastScheduledExecutionTime = triggerContext.lastScheduledExecutionTime();
            if (lastScheduledExecutionTime == null) {
                return Date.from(Instant.now().plusSeconds(delaySeconds));
            }
            return plusFiveSeconds(lastScheduledExecutionTime);
        }

        private Date plusFiveSeconds(Date lastScheduledExecutionTime) {
            return Date.from(lastScheduledExecutionTime.toInstant()
                    .atZone(ZoneId.systemDefault()).plusSeconds(5).toInstant());
        }
    }
}
