package com.dj.financial.advisor.personalizedportfolio;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CompletableFuture;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.dj.financial.advisor.portfolio.RecommendedPortfolio;

/**
 * API business logic for Personalized Portfolio
 * 
 * @author deepikajyotula
 *
 */
@Service
public class PersonalizedPortfolioServiceImpl implements PersonalizedPortfolioService {
	
	private static final Logger logger = LoggerFactory.getLogger(PersonalizedPortfolioController.class);
	
	/**
	 * Tightly coupled to fixed investment categories
	 * 
	 * Improvements: Can be improved to make investment categories changed or new ones added.
	 */
	private static Map<Integer, String> invCatNames;
	static {
		invCatNames = new HashMap<>();
		invCatNames.put(0, "Bonds");
		invCatNames.put(1, "Large Cap");
		invCatNames.put(2, "Mid Cap");
		invCatNames.put(3, "Foreign");
		invCatNames.put(4, "Small Cap");
	}
	
	/*{
	 *  "risk": 5,
		"bonds" :{
			"currentAmount":50000,
			"difference": +10000,
			"newAmount": 60000
		}
		"largeCap" :{
			"currentAmount":25000,
			"difference": -10000,
			"newAmount": 15000
		}
		"midCap" :{
			"currentAmount":10000,
			"difference": +5000,
			"newAmount": 15000
		}
		"foreign" :{
			"currentAmount":15000,
			"difference": -5000,
			"newAmount": 10000
		}
		"smallCap" :{
			"currentAmount":0,
			"difference": +0,
			"newAmount": 0
		}
		"description":"Transfer $10000 from Large Cap to Bonds. Transfer $5000 from Foreign to Mid Cap."
	}*/
	@Async
	public CompletableFuture<Object> calculatePersonalizedPotfolio(RecommendedPortfolio recommendedPortfolio, ClientInvestment clientInvestment) {
		logger.info("Balancing portfolio for user with risk " + recommendedPortfolio.getRisk());
		
		double clientTotalInvestment = clientInvestment.getTotal();	
		
		Map<String, Object> response = new HashMap<>();
		response.put("risk", recommendedPortfolio.getRisk());
		
		Map<String, Double> balancedBonds = calculateEachInvestment(clientTotalInvestment, clientInvestment.getBonds(), recommendedPortfolio.getBonds());
		response.put("bonds", balancedBonds);
		
		Map<String, Double> balancedLargeCap = calculateEachInvestment(clientTotalInvestment, clientInvestment.getLargeCap(), recommendedPortfolio.getLargeCap());
		response.put("largeCap", balancedLargeCap);
		
		Map<String, Double> balancedMidCap = calculateEachInvestment(clientTotalInvestment, clientInvestment.getMidCap(), recommendedPortfolio.getMidCap());
		response.put("midCap", balancedMidCap);
		
		Map<String, Double> balancedForeign = calculateEachInvestment(clientTotalInvestment, clientInvestment.getForeign(), recommendedPortfolio.getForeign());
		response.put("foreign", balancedForeign);
		
		Map<String, Double> balancedSmallCap = calculateEachInvestment(clientTotalInvestment, clientInvestment.getSmallCap(), recommendedPortfolio.getSmallCap());
		response.put("smallCap", balancedSmallCap);
		
		double[] diffAmountsArr = {balancedBonds.get("difference"), 
									balancedLargeCap.get("difference"), 
									balancedMidCap.get("difference"),
									balancedForeign.get("difference"),
									balancedSmallCap.get("difference")};
		
		response.put("description", transferLogic(diffAmountsArr));
		
		//Improvements: Will be more appropriate if org.json JSON is returned instead of using Object serialized and sent through network
		logger.info("response: " + response);
		
		return CompletableFuture.completedFuture(response);		
	}
	
	/*{
		"currentAmount":50000,
		"difference": +10000,
		"newAmount": 60000
	}*/
	private Map<String, Double> calculateEachInvestment(double total, double curAmount, int recPercent) {
		Map<String, Double> balancedInvestment = new HashMap<>();
		
		double newAmount = (total * recPercent)/100;
		double diff = newAmount - curAmount;
		
		balancedInvestment.put("currentAmount", curAmount);
		balancedInvestment.put("difference", diff);
		balancedInvestment.put("newAmount", newAmount);
		
		return balancedInvestment;
	}
	
	/**
	 * all are processed when all elements of the array have 0 value
	 * 
	 * @param arr
	 * @return
	 */
	private static boolean checkAllProcessed(double[] arr) {
		for(int i=0; i<arr.length; i++) {
			if(arr[i] != 0) {
				return false;
			}
		}
		return true;
	}
	
	/**
	 * Generates a String for the Transfer of amounts to be done between investment categories
	 * ensuring 
	 * the number of transactions recommended to be minimum possible to match the ideal portfolio.
	 * 
	 * @param arr
	 * @return
	 */
	protected String transferLogic(double[] arr) { //The total of all values in the array is 0
		StringBuffer sb = new StringBuffer();
		
		boolean allProcessed = false;
				
		while( !allProcessed) {
			
			allProcessed = checkAllProcessed(arr);
		
			if( !allProcessed) {				
				int maxIndex = 0;
				double max = arr[0];
				int minIndex = 0;
				double min = arr[0];
				
				for(int i=0; i<arr.length; i++) {
					if(arr[i] > max) {
						max = arr[i];
						maxIndex = i;
					}
					if(arr[i] < min) {
						min = arr[i];
						minIndex = i;
					}
				}
				//Now as the total of all values in the array is 0
				//max will contain highest positive number
				//min will contain highest negative number
				
				double maxMinDiff = max + min;
				if(maxMinDiff == 0) {
					sb.append("Transfer $" + max + " from " + invCatNames.get(minIndex) + " to " + invCatNames.get(maxIndex) + ". \n");
					arr[minIndex] = 0;
					arr[maxIndex] = 0;
				} 
				else if(maxMinDiff < 0) { // positive amount < negative amount
					sb.append("Transfer $" + max + " from " + invCatNames.get(minIndex) + " to " + invCatNames.get(maxIndex) + ". \n");
					arr[minIndex] = maxMinDiff;
					arr[maxIndex] = 0;
				}
				else if(maxMinDiff > 0) { // positive amount > negative amount
					sb.append("Transfer $" + (0-min) + " from " + invCatNames.get(minIndex) + " to " + invCatNames.get(maxIndex) + ". \n");
					arr[minIndex] = 0;
					arr[maxIndex] = maxMinDiff;
				}
			}
		}
		
		return sb.toString();
	}

}
