package com.dj.financial.advisor.portfolio;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exception class to send 404 Not found if unknown risk
 * 
 * @author deepikajyotula
 *
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class RecommendedPortfolioNotFoundException extends RuntimeException {

	private static final long serialVersionUID = -6648187242609549001L;
	
	public RecommendedPortfolioNotFoundException(String exception) {
		super(exception);
	}

}
