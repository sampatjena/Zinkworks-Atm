package com.zinkworks.enterprise.solutions.dto;

public class AtmCurrency {
	
	private int fiftyCurrencyCount;
	private int twentyCurrencyCount;
	private int tenCurrencyCount;
	private int fiveCurrencyCount;
	private int amountNotDisburshed;
	
	
	public AtmCurrency() {
		
	}

	public int getFiftyCurrencyCount() {
		return fiftyCurrencyCount;
	}

	public void setFiftyCurrencyCount(int fiftyCurrencyCount) {
		this.fiftyCurrencyCount = fiftyCurrencyCount;
	}

	public int getTwentyCurrencyCount() {
		return twentyCurrencyCount;
	}

	public void setTwentyCurrencyCount(int twentyCurrencyCount) {
		this.twentyCurrencyCount = twentyCurrencyCount;
	}

	public int getTenCurrencyCount() {
		return tenCurrencyCount;
	}

	public void setTenCurrencyCount(int tenCurrencyCount) {
		this.tenCurrencyCount = tenCurrencyCount;
	}

	public int getFiveCurrencyCount() {
		return fiveCurrencyCount;
	}

	public void setFiveCurrencyCount(int fiveCurrencyCount) {
		this.fiveCurrencyCount = fiveCurrencyCount;
	}

	public String getDisdursedCurrencyDetailsAsString() {
		StringBuilder disbursedCurrencyDetails = new StringBuilder();
		disbursedCurrencyDetails.append("Currency disbursed :");
		if (getFiftyCurrencyCount() > 0)
			disbursedCurrencyDetails.append(getFiftyCurrencyCount()).append(" ").append("Fifty Euro.");
		if (getTwentyCurrencyCount() > 0)
			disbursedCurrencyDetails.append(getTwentyCurrencyCount()).append(" ").append("Twenty Euro.");
		if (getTenCurrencyCount() > 0)
			disbursedCurrencyDetails.append(getTenCurrencyCount()).append(" ").append("Ten Euro.");
		if (getFiveCurrencyCount()> 0)
			disbursedCurrencyDetails.append(getFiveCurrencyCount()).append(" ").append("Five Euro.");
		
		return disbursedCurrencyDetails.toString();
		
	}

	public int getAmountNotDisburshed() {
		return amountNotDisburshed;
	}

	public void setAmountNotDisburshed(int amountNotDisburshed) {
		this.amountNotDisburshed = amountNotDisburshed;
	}
	
	

}
