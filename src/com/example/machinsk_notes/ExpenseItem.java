package com.example.machinsk_notes;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Currency;


public class ExpenseItem {
	protected Date date;
	protected String currencyUnit;
	protected String category;
	protected String textDescprition;
	protected BigDecimal amountSpent;
	
	
	
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	
	
	
	public String getCurrencyUnit() {
		return currencyUnit;
	}
	
	public void setCurrencyUnit(String currencyUnit) {
		this.currencyUnit = currencyUnit;
	}
	
	
	
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	
	
	
	public String getTextDescprition() {
		return textDescprition;
	}
	
	public void setTextDescprition(String textDescprition) {
		this.textDescprition = textDescprition;
	}
	
	
	
	public BigDecimal getAmountSpent() {
		return amountSpent;
	}
	
	public void setAmountSpent(BigDecimal amountSpent) {
		this.amountSpent = amountSpent;
	}
	
	
}
