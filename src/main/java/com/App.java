package com;

import com.util.Quartz_Scheduler;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan
public class App 
{
    public static void main( String[] args ) throws Exception
    {
        SpringApplication.run(App.class,args);

//        设置间隔执行时间,每小时执行一次查询
        Quartz_Scheduler.init(3600);
    }
}
