package com.dj.financial.advisor.portfolio;

import java.util.concurrent.CompletableFuture;

import org.springframework.stereotype.Component;

/**
 * Interface RecommendedPortfolioService
 * 
 * @author deepikajyotula
 *
 */
@Component
public interface RecommendedPortfolioService {
	
	CompletableFuture<RecommendedPortfolio> getAsynchRecommendedPortfolios(int risk);
	
	RecommendedPortfolio getRecommendedPortfolios(int risk);

}
