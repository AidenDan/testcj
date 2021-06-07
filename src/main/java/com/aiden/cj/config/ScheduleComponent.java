package com.aiden.cj.config;

import lombok.extern.log4j.Log4j2;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 动态计划任务管理工具
 */
@Component
@Log4j2
public class ScheduleComponent {


    private final Scheduler scheduler;

    @Autowired
    public ScheduleComponent(Scheduler scheduler) {
        this.scheduler = scheduler;
    }

    /**
     * 任务只运行一次
     * @param cls
     * @param group
     * @throws Exception
     */
    public void addJobAndStartOnlyOne(Class<? extends Job> cls, String group) throws Exception {
    	JobConfig jobConfig = new JobConfig(cls, null, group);

		scheduler.scheduleJob(jobConfig.getJobDetail(), jobConfig.getNotRepeatTrigger());
    }

    public void addJobAndStartWithTrigger(Class<? extends Job> cls, String group, Trigger trigger) throws Exception {
    	JobConfig jobConfig = new JobConfig(cls, null, group);

		scheduler.scheduleJob(jobConfig.getJobDetail(), trigger);
    }

    /**
     * 动态添加计划任务
     * @param cls
     * @param cron
     * @param group
     * @throws Exception
     */
    public void addJobAndStart(Class<? extends Job> cls, String cron, String group) throws Exception {
    	JobConfig jobConfig = new JobConfig(cls,cron,group);

		scheduler.scheduleJob(jobConfig.getJobDetail(), jobConfig.getCronTrigger());
    }

    /**
     * 删除计划任务
     * @param cls
     * @param group
     * @throws SchedulerException
     */
    public void deleteJob(Class<? extends Job> cls, String group) throws SchedulerException {
        JobKey jobKey = new JobKey(cls.getName(), group);
        JobDetail jobDetail = scheduler.getJobDetail(jobKey);
        if (jobDetail == null) {
            return;
        }
        scheduler.deleteJob(jobKey);
    }

    /**
     * 检查任务是否存在
     * @param cls
     * @param group
     * @return
     * @throws SchedulerException
     */
    public boolean checkJobExist(Class<? extends Job> cls, String group) throws SchedulerException {
        JobKey jobKey = new JobKey(cls.getName(), group);
        return scheduler.checkExists(jobKey);
    }
}
