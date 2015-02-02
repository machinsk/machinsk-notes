package com.example.machinsk_notes;

import java.io.IOException;

//Modified from:
//https://github.com/abramhindle/student-picker/blob/master/src/ca/softwareprocess/studentpicker/StudentListController.java  Jan 31 2015

public class ClaimController {
	
	// Lazy Singleton 
	private static ClaimList claimList = null;
	
	
	// Warning: throws a runTimeException
	static public ClaimList getClaimList() {
		if (claimList == null) {
			try {
				claimList = ClaimListManager.getManager().loadClaimList();
				claimList.addListener(new Listener() {					
					@Override
					public void update() {
						saveClaimList();
					}
				});
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				throw new RuntimeException("Could not deserialize ClaimList from ClaimListManager");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				throw new RuntimeException("Could not deserialize ClaimList from ClaimListManager");
			}
		}
		return claimList;			
	}
		
	
	static public void saveClaimList() {
		try {
			ClaimListManager.getManager().saveClaimList(getClaimList());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException("Could not deserialize ClaimList from ClaimListManager");
		}		
	}
	
	
	public TravelClaim chooseClaim() throws EmptyClaimListException {
		return getClaimList().chooseClaim();		
	}
	
	
	public void addClaim(TravelClaim claim) { 
		getClaimList().addClaim(claim);
	}
	
}
