package com.example.machinsk_notes;

import java.util.ArrayList;

public class ClaimList {
	
	protected ArrayList<TravelClaim> claimList = null;
	
	
	public ClaimList() {
		claimList = new ArrayList<TravelClaim>();
		
	}
	
	public void addClaim(TravelClaim newClaim){
		claimList.add(newClaim);
		
	}
}
