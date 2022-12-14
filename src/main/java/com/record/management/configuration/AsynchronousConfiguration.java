package com.record.management.configuration;

import java.util.concurrent.Executor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Configuration
@EnableAsync
public class AsynchronousConfiguration {

	@Bean(name = "asyncExecutor")
	public Executor asyncExecutor() {
	    ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
	    executor.setCorePoolSize(2);
	    executor.setMaxPoolSize(2000);
	    executor.setQueueCapacity(1000);
	    executor.setThreadNamePrefix("RecordProcessing-");
	    executor.initialize();
	    return executor;
	}
	
}
