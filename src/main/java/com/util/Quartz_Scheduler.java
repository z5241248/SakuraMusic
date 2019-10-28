package com.util;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

public class Quartz_Scheduler
{
    public static void init(int second) throws Exception
    {
        // 1、创建调度器Scheduler
        SchedulerFactory schedulerFactory = new StdSchedulerFactory();
        Scheduler scheduler = schedulerFactory.getScheduler();

        // 2、创建JobDetail实例，并与Job类绑定(Job执行内容)
        JobDetail jobDetail = JobBuilder.newJob(Quartz_SealJob.class)
                .withIdentity("job1", "group1").build();

        // 3、构建Trigger实例,每隔1s执行一次
        Trigger trigger = TriggerBuilder.newTrigger().withIdentity("trigger1", "triggerGroup1")
                        .startNow()//立即生效
                        .withSchedule(SimpleScheduleBuilder.simpleSchedule()
                        .withIntervalInSeconds(second)//每隔5s执行一次
                        .repeatForever()).build();//一直执行

        //4、执行
        scheduler.scheduleJob(jobDetail, trigger);
        System.out.println("--------scheduler start ! ------------");
        scheduler.start();

        //设置运行多久后睡眠
//        TimeUnit.MINUTES.sleep(1);
//        scheduler.shutdown();
//        System.out.println("--------scheduler shutdown ! ------------");
    }
}
