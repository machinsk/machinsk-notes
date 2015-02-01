package com.example.machinsk_notes;

import java.io.Serializable;
import java.util.Calendar;



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

	
}
