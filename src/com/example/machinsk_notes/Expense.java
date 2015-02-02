package com.example.machinsk_notes;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Comparator;

public class Expense implements Serializable {
	/**
	 * Expense Serializable ID
	 */
	private static final long serialVersionUID = 1799961715277531286L;
	

	protected Calendar date;
	protected String category;
	protected String currency;
	protected String description;
	protected Double amountSpent;
	
	
	public Calendar getDate() {
		return date;
	}


	public void setDate(Calendar date) {
		this.date = date;
	}


	public String getCategory() {
		return category;
	}


	public void setCategory(String category) {
		this.category = category;
	}


	public String getCurrency() {
		return currency;
	}


	public void setCurrency(String currency) {
		this.currency = currency;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public Double getAmountSpent() {
		return amountSpent;
	}


	public void setAmountSpent(Double amountSpent) {
		this.amountSpent = amountSpent;
	}

	
	public Expense(String category, String description, String currency, Calendar date, Double amountSpent) {
		this.category = category;
		this.date = date;
		this.currency = currency;
		this.description = description;
		this.amountSpent = amountSpent;
	}
	
	
	public boolean equals(Object compareExpense) {
		if (compareExpense != null && 
				compareExpense.getClass()==this.getClass()) {
			return this.equals((Expense)compareExpense);
		} else {
			return false;
		}
	}
	
	public boolean equals(Expense compareExpense) {
		if(compareExpense==null) {			
			return false;
		}
		return getDescription().equals(compareExpense.getDescription());
	}	
	
	public int hashCode() {
		return ("Expense:"+getDescription()).hashCode();
	}
	
	static class SortByDate implements Comparator {

		@Override
		public int compare(Object date1, Object date2) {
			if(!(date1 instanceof Expense)||!(date2 instanceof Expense)){
				throw new ClassCastException();
			}
			Expense cDate1 = (Expense) date1;
			Expense cDate2 = (Expense) date2;
			return (cDate1.getDate()).compareTo(cDate2.getDate());
		}
		
	}


}
