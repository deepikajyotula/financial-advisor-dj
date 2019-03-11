package com.dj.financial.advisor.personalizedportfolio;

import java.util.concurrent.CompletableFuture;

import org.springframework.stereotype.Component;

import com.dj.financial.advisor.portfolio.RecommendedPortfolio;

/**
 * Interface PersonalizedPortfolioService 
 * 
 * @author deepikajyotula
 *
 */
@Component
public interface PersonalizedPortfolioService {
	
	CompletableFuture<Object> calculatePersonalizedPotfolio(RecommendedPortfolio recommendedPortfolio, ClientInvestment clientInvestment);
	
}
