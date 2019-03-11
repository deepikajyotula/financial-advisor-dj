package com.dj.financial.advisor.personalizedportfolio;

import java.io.Serializable;

import javax.validation.constraints.PositiveOrZero;

/**
 * Utility class to serialize and deserialize post body for personalized portfolio API calls
 * 
 * Includes minimum validations
 * 
 * @author deepikajyotula
 *
 */
public class ClientInvestment implements Serializable {
		
	private static final long serialVersionUID = -2793854264111674547L;
	
	@PositiveOrZero(message="bonds value is in dollars and should be positive or 0")
	private double bonds;
	
	@PositiveOrZero(message="largeCap value is in dollars and should be positive or 0")
	private double largeCap;
	
	@PositiveOrZero(message="midCap value is in dollars and should be positive or 0")
	private double midCap;
	
	@PositiveOrZero(message="foreign value is in dollars and should be positive or 0")
	private double foreign;
	
	@PositiveOrZero(message="smallCap value is in dollars and should be positive or 0")
	private double smallCap;
	
	public ClientInvestment(double bonds, double largeCap, double midCap, double foreign, double smallCap) {
		super();
		this.bonds = bonds;
		this.largeCap = largeCap;
		this.midCap = midCap;
		this.foreign = foreign;
		this.smallCap = smallCap;
	}
	
	public double getBonds() {
		return bonds;
	}
	
	public void setBonds(double bonds) {
		this.bonds = bonds;
	}
	
	public double getLargeCap() {
		return largeCap;
	}
	
	public void setLargeCap(double largeCap) {
		this.largeCap = largeCap;
	}
	
	public double getMidCap() {
		return midCap;
	}
	
	public void setMidCap(double midCap) {
		this.midCap = midCap;
	}
	
	public double getForeign() {
		return foreign;
	}
	
	public void setForeign(double foreign) {
		this.foreign = foreign;
	}
	
	public double getSmallCap() {
		return smallCap;
	}
	
	public void setSmallCap(double smallCap) {
		this.smallCap = smallCap;
	}
	
	public double getTotal() {
		return this.bonds + this.largeCap + this.midCap + this.foreign + this.smallCap;
	}

}
