package com.example.machinsk_notes;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Comparator;

//Heavily modified from:
//https://github.com/abramhindle/student-picker/blob/master/src/ca/softwareprocess/studentpicker/Student.java  Jan 30 2015

public class TravelClaim implements Serializable {
	/**
	 * Claim Serializable ID
	 */
	private static final long serialVersionUID = -2158008197578272068L;

	protected Calendar startDate;
	protected Calendar endDate;
	protected String name;
	protected String textDescription;
	
	public TravelClaim(String name, String textDescription, Calendar startDate, Calendar endDate) {
		this.startDate = startDate;
		this.endDate = endDate;
		this.name = name;
		this.textDescription = textDescription;
	}
	
	
	public Calendar getStartDate() {
		return startDate;
	}
	public void setStartDate(Calendar startDate) {
		this.startDate = startDate;
	}
	
	public Calendar getEndDate() {
		return endDate;
	}
	public void setEndDate(Calendar endDate) {
		this.endDate = endDate;
	}
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
	public String getTextDescription() {
		return textDescription;
	}
	public void setTextDescription(String textDescription) {
		this.textDescription = textDescription;
	}
	
	
	public boolean equals(Object compareClaim) {
		if (compareClaim != null && 
				compareClaim.getClass()==this.getClass()) {
			return this.equals((TravelClaim)compareClaim);
		} else {
			return false;
		}
	}
	
	public boolean equals(TravelClaim compareClaim) {
		if(compareClaim==null) {			
			return false;
		}
		return getName().equals(compareClaim.getName());
	}	
	
	public int hashCode() {
		return ("Claim:"+getName()).hashCode();
	}
	
	static class SortByDate implements Comparator {

		@Override
		public int compare(Object date1, Object date2) {
			if(!(date1 instanceof TravelClaim)||!(date2 instanceof TravelClaim)){
				throw new ClassCastException();
			}
			TravelClaim cDate1 = (TravelClaim) date1;
			TravelClaim cDate2 = (TravelClaim) date2;
			return (cDate1.getStartDate()).compareTo(cDate2.getStartDate());
		}
		
	}
	
	public void sumExpenses(){
		
	}

	
}
