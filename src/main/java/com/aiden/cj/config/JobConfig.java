package com.aiden.cj.config;

import org.quartz.*;


public class JobConfig {
    private String identity;
    private String group;
    private String cron;
    private Class<? extends Job> cls;

    public JobConfig(Class<? extends Job> cls, String cron, String group) {
        this.identity = cls.getName();
        this.group = group;
        this.cron = cron;
        this.cls = cls;
    }
    public JobConfig(Class<? extends Job> cls, String cron, String group, String identity) {
        this.identity = identity;
        this.group = group;
        this.cron = cron;
        this.cls = cls;
    }

    public JobDetail getJobDetail() {
        return JobBuilder.newJob(this.cls).withIdentity(this.identity, this.group).build();
    }

    public CronTrigger getCronTrigger() {
        return TriggerBuilder.newTrigger()
                .withIdentity(this.identity, this.group)
                .withSchedule(CronScheduleBuilder.cronSchedule(this.cron))
                .build();
    }

    public SimpleTrigger getNotRepeatTrigger() {
        return TriggerBuilder.newTrigger()
                .withIdentity(this.identity, this.group)
                .withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(1).withRepeatCount(0))
                .build();
    }

    public SimpleTrigger getRepeatTrigger(int repeatCount) {
        return TriggerBuilder.newTrigger()
                .withIdentity(this.identity, this.group)
                .withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(1).withRepeatCount(repeatCount))
                .build();
    }
}
