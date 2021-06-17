package com.aiden.cj.runner;

import com.aiden.cj.config.ScheduleComponent;
import com.aiden.cj.job.AwardExpiredJob;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

@Configuration
@Log4j2
public class ServerRunner implements CommandLineRunner {
    @Autowired
    private ScheduleComponent schedule;
    @Override
    public void run(String... args) throws Exception {
        // 定时任务添加
        schedule.addJobAndStart(AwardExpiredJob.class, "0 0 1 * * ?",AwardExpiredJob.class.getName());

    }
}
