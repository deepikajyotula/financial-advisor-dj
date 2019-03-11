package com.dj.financial.advisor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Welcome class
 * 
 * @author deepikajyotula
 *
 */
@RestController
public class FinancialAdvisorDjService {
	
    private static final Logger logger = LoggerFactory.getLogger(FinancialAdvisorDjService.class);
    
	@RequestMapping(value="/advisorapi/v1.0/")
	public String index() {
		logger.info("Welcome to Financial Advisor");
		
		return "Welcome to Financial Advisor!";
	}
	
}
