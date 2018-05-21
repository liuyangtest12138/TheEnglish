package com.liuy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

/**
 * @author Administrator
 */
@SpringBootApplication
@EnableScheduling
public class TheEnglishApplication {

	public static void main(String[] args) {
		SpringApplication.run(TheEnglishApplication.class, args);
	}

	public TaskScheduler taskScheduler(){
        ThreadPoolTaskScheduler threadPoolTaskScheduler = new ThreadPoolTaskScheduler();
        // 线程池大小
        threadPoolTaskScheduler.setPoolSize(10);
        // 线程名字前缀
        threadPoolTaskScheduler.setThreadNamePrefix("springBoot-task");
        return threadPoolTaskScheduler;
    }
}
