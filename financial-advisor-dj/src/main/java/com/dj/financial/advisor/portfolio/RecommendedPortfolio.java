package com.dj.financial.advisor.portfolio;

import java.io.Serializable;

/**
 * RecommendedPortfolio Bean
 * 
 * Supports only investment categories - Bonds,Large Cap, Mid Cap, Foreign, Small Cap.
 * 
 * @author deepikajyotula
 *
 */
public class RecommendedPortfolio implements Serializable {
	
	private static final long serialVersionUID = -4946082263341867709L;
	
	private int risk;
	private int bonds;
	private int largeCap;
	private int midCap;
	private int foreign;
	private int smallCap;
	
	public RecommendedPortfolio(int risk, int bonds, int largeCap, int midCap, int foreign, int smallCap) {
		this.risk = risk;
		this.bonds = bonds;
		this.largeCap = largeCap;
		this.midCap = midCap;
		this.foreign = foreign;
		this.smallCap = smallCap;
	}
	
	public int getRisk() {
		return risk;
	}
	
	public void setRisk(int risk) {
		this.risk = risk;
	}
	
	public int getBonds() {
		return bonds;
	}
	
	public void setBonds(int bonds) {
		this.bonds = bonds;
	}
	
	public int getLargeCap() {
		return largeCap;
	}
	
	public void setLargeCap(int largeCap) {
		this.largeCap = largeCap;
	}
	
	public int getMidCap() {
		return midCap;
	}
	
	public void setMidCap(int midCap) {
		this.midCap = midCap;
	}
	
	public int getForeign() {
		return foreign;
	}
	
	public void setForeign(int foreign) {
		this.foreign = foreign;
	}
	
	public int getSmallCap() {
		return smallCap;
	}
	
	public void setSmallCap(int smallCap) {
		this.smallCap = smallCap;
	}

}
