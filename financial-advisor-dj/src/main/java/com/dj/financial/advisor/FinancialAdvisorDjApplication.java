package com.dj.financial.advisor;

import java.util.concurrent.Executor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

/**
 * FinancialAdvisor Java REST Application
 * - can at most handle 2 unique clients at the same time.
 * - Recommends an ideal portfolio
 * - Recommends changes to a user’s actual portfolio
 * 
 * @author deepikajyotula
 *
 */
@EnableAsync
@SpringBootApplication
public class FinancialAdvisorDjApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(FinancialAdvisorDjApplication.class, args);		
	}
	
	/**
	 * Limiting the number of concurrent threads to 2
	 * 
	 * @EnableAsync allows @Async methods in a background thread pool.
	 * 
	 * @return
	 */
	@Bean
    public Executor taskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(2);
        executor.setMaxPoolSize(2);
        executor.setQueueCapacity(25);
        executor.setThreadNamePrefix("finadv-");
        executor.initialize();
        return executor;
    }

}
