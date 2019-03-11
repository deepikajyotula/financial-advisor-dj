package com.dj.financial.advisor.portfolio;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST controller 
 * Recommends an ideal portfolio for the given risk
 * 
 * @author deepikajyotula
 *
 */
@RestController
public class RecommendedPortfolioController {
	
    private static final Logger logger = LoggerFactory.getLogger(RecommendedPortfolioController.class);
    
    private final RecommendedPortfolioService recommendedPortfolioService;

	@Autowired
	public RecommendedPortfolioController(RecommendedPortfolioService recommendedPortfolioService) {
		this.recommendedPortfolioService = recommendedPortfolioService;
	}

	/**
	 * A client provides the advisor his/her risk preference (1 being very risk averse, and 10 being insensitive to risk).
	 * Return to the client the recommended portfolio based on his/her risk preference
	 * 
	 * mapping a request to a URI 
	 * http://localhost:8080/recommendedportfolio?risk=2
	 * 
	 * @param risk
	 * @return
	 */
	@RequestMapping(value="/advisorapi/v1.0/recommendedportfolios/{risk}", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Object getRecommendedPortfolio(@PathVariable int risk) {
		logger.info("Get Recommended Portfolio for risk: " + risk);
		
		if(risk<1 || risk>10) {
			throw new RecommendedPortfolioNotFoundException("Recommended Portfolio not found for risk: " + risk);
		}
						
		return recommendedPortfolioService.getAsynchRecommendedPortfolios(risk);
	}

}
