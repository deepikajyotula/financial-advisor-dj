package com.dj.financial.advisor.portfolio;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * API business logic for Recommended Portfolio
 * 
 * @author deepikajyotula
 *
 */
@Service
public class RecommendedPortfolioServiceImpl implements RecommendedPortfolioService {
	
    private static final Logger logger = LoggerFactory.getLogger(RecommendedPortfolioServiceImpl.class);
	
	public Map<Integer, RecommendedPortfolio> recommendedPortfolios;
	
	/**
	 * 
	 * Considering fixed Recommended Portfolios for risks 1 to 10
	 * 
	 * Improvements: Ideally should be in a database. 
	 * 
	 */
	public RecommendedPortfolioServiceImpl() {
		logger.info("Loading recommendedPortfolios...");
		
		recommendedPortfolios = new HashMap<>();
		
		recommendedPortfolios.put(1, new RecommendedPortfolio(1, 80, 20, 0, 0, 0)); //Every row should add up to 100%.
		recommendedPortfolios.put(2, new RecommendedPortfolio(2, 70, 15, 15, 0, 0));
		recommendedPortfolios.put(3, new RecommendedPortfolio(3, 60, 15, 15, 10, 0));
		recommendedPortfolios.put(4, new RecommendedPortfolio(4, 50, 20, 20, 10, 0));
		recommendedPortfolios.put(5, new RecommendedPortfolio(5, 40, 20, 20, 20, 0));
		recommendedPortfolios.put(6, new RecommendedPortfolio(6, 35, 25, 5, 30, 5));
		recommendedPortfolios.put(7, new RecommendedPortfolio(7, 20, 25, 25, 25, 5));
		recommendedPortfolios.put(8, new RecommendedPortfolio(8, 10, 20, 40, 20, 10));
		recommendedPortfolios.put(9, new RecommendedPortfolio(9, 5, 15, 40, 25, 15));
		recommendedPortfolios.put(10, new RecommendedPortfolio(10, 0, 5, 25, 30, 40));
	}

	@Async
	public CompletableFuture<RecommendedPortfolio> getAsynchRecommendedPortfolios(int risk) {
		return CompletableFuture.completedFuture(recommendedPortfolios.get(risk));
	}
	
	public RecommendedPortfolio getRecommendedPortfolios(int risk) {
		return recommendedPortfolios.get(risk);
	}
	
}
