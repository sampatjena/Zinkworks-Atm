package com.zinkworks.enterprise.solutions.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "atmcurrencyinfo")
public class AtmCurrencyInfo {
	
	@Id
	@Column(name = "atm_id")
	private int atmId;
	
	@Column(name = "fifty_euro_count")
	private double fiftyEuroCurrencyCount;
	
	@Column(name = "twenty_euro_count")
	private double twentyEuroCurrencyCount;
	
	@Column(name = "ten_euro_count")
	private double tenEuroCurrencyCount;
	
	@Column(name = "five_euro_count")
	private double fiveEuroCurrencyCount;

	public AtmCurrencyInfo() {
			}

	public int getAtmId() {
		return atmId;
	}

	public void setAtmId(int atmId) {
		this.atmId = atmId;
	}

	public double getFiftyEuroCurrencyCount() {
		return fiftyEuroCurrencyCount;
	}

	public void setFiftyEuroCurrencyCount(double fiftyEuroCurrencyCount) {
		this.fiftyEuroCurrencyCount = fiftyEuroCurrencyCount;
	}

	public double getTwentyEuroCurrencyCount() {
		return twentyEuroCurrencyCount;
	}

	public void setTwentyEuroCurrencyCount(double twentyEuroCurrencyCount) {
		this.twentyEuroCurrencyCount = twentyEuroCurrencyCount;
	}

	public double getTenEuroCurrencyCount() {
		return tenEuroCurrencyCount;
	}

	public void setTenEuroCurrencyCount(double tenEuroCurrencyCount) {
		this.tenEuroCurrencyCount = tenEuroCurrencyCount;
	}

	public double getFiveEuroCurrencyCount() {
		return fiveEuroCurrencyCount;
	}

	public void setFiveEuroCurrencyCount(double fiveEuroCurrencyCount) {
		this.fiveEuroCurrencyCount = fiveEuroCurrencyCount;
	}
	
	
	
	
	

}
