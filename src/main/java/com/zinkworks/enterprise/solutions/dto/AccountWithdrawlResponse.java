package com.zinkworks.enterprise.solutions.dto;

public class AccountWithdrawlResponse {
	
	private int fiftyCurrencyCount;
	private int twentyCurrencyCount;
	private int tenCurrencyCount;
	private int fiveCurrencyCount;
	public AccountWithdrawlResponse() {
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
	
	public String getfittyEuroNotesAsString() {
		return getFiftyCurrencyCount()+" "+"Currency notes";
	}
	public String gettwentyEuroNotesAsString() {
		return getTwentyCurrencyCount()+" "+"Currency notes";
	}
	
	public String getTenEuroNotesAsString() {
		return getTenEuroNotesAsString()+" "+"Currency notes";
	}
	public String getFiveEuroNotesAsString() {
		return getFiveEuroNotesAsString()+" "+"Currency notes";
	}
	
	

}
