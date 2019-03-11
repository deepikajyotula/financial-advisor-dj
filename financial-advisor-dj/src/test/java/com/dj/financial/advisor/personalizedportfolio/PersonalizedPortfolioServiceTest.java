package com.dj.financial.advisor.personalizedportfolio;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * 
 * @author deepikajyotula
 *
 */
public class PersonalizedPortfolioServiceTest {

	@Test
	public void test() {
		PersonalizedPortfolioServiceImpl service = new PersonalizedPortfolioServiceImpl();
		
		double[] arr1 = {-72.0, 64.0, -176.0, 140.0, 44.0};
		String transferStr = service.transferLogic(arr1);		
		assertEquals("Transfer $140.0 from Mid Cap to Foreign. \nTransfer $64.0 from Bonds to Large Cap. \nTransfer $36.0 from Mid Cap to Small Cap. \nTransfer $8.0 from Bonds to Small Cap. \n", transferStr);
	
		double[] arr2 = {24000.0, -19000.0, -5000.0, 0, 0};
		transferStr = service.transferLogic(arr2);		
		assertEquals("Transfer $19000.0 from Large Cap to Bonds. \nTransfer $5000.0 from Mid Cap to Bonds. \n", transferStr);
		
		double[] arr3 = {20000, -10000, -5000, -5000, 0};
		transferStr = service.transferLogic(arr3);		
		assertEquals("Transfer $10000.0 from Large Cap to Bonds. \nTransfer $5000.0 from Mid Cap to Bonds. \nTransfer $5000.0 from Foreign to Bonds. \n", transferStr);
	}

}
