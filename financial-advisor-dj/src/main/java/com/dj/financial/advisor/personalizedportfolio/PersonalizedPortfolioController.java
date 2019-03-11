package com.dj.financial.advisor.personalizedportfolio;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.dj.financial.advisor.portfolio.RecommendedPortfolio;
import com.dj.financial.advisor.portfolio.RecommendedPortfolioService;

/**
 * REST controller for balancing user's portfolio
 * 
 * @author deepikajyotula
 *
 */
@RestController
public class PersonalizedPortfolioController {
	
    private static final Logger logger = LoggerFactory.getLogger(PersonalizedPortfolioController.class);
    
    private final PersonalizedPortfolioService personalizedPortfolioService;
    private final RecommendedPortfolioService recommendedPortfolioService;

	@Autowired
	public PersonalizedPortfolioController(PersonalizedPortfolioService personalizedPortfolioService, RecommendedPortfolioService recommendedPortfolioService) {
		this.personalizedPortfolioService = personalizedPortfolioService;
		this.recommendedPortfolioService = recommendedPortfolioService;
	}
	
	/**
	 * This method (flagged with @Async annotation) will run on a separate thread
	 * 
	 * 
	 * As this is a complex query with a number of input parameters 
	 * Using POST with request body makes more sense than GET
	 * 
	 * @param risk
	 * @return
	 */
	@RequestMapping(value="/advisorapi/v1.0/recommendedportfolios/{risk}/personalizedportfolio", method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Object  getPersonalizedPortfolio(@PathVariable int risk, @RequestBody @Valid ClientInvestment clientInvestment) {
		logger.info("Get Personalized Portfolio");
				
		RecommendedPortfolio recommendedPortfolio = recommendedPortfolioService.getRecommendedPortfolios(risk);
				
		return personalizedPortfolioService.calculatePersonalizedPotfolio( recommendedPortfolio, clientInvestment);

	}

}
