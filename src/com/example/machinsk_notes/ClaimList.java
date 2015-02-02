package com.example.machinsk_notes;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;

public class ClaimList implements Serializable {
	
	/**
	 * ClaimList serialization ID
	 */
	private static final long serialVersionUID = -7026409233627323832L;
	protected ArrayList<TravelClaim> claimList = null;
	protected transient ArrayList<Listener> listeners = null;
	
	public ClaimList() {
		claimList = new ArrayList<TravelClaim>();		
		listeners = new ArrayList<Listener>();
	}
	
	private ArrayList<Listener> getListeners() {
		if (listeners == null ) {
			listeners = new ArrayList<Listener>();
		}
		return listeners;
	}
	
	public ArrayList<TravelClaim> getClaims() {
		return claimList;		
	}

	public void addClaim(TravelClaim testClaim) {
		claimList.add(testClaim);
		notifyListeners();
	}

	private void notifyListeners() {
		for (Listener  listener : getListeners()) {
			listener.update();
		}
	}
	
	public void removeClaim(TravelClaim testClaim) {
		claimList.remove(testClaim);
		notifyListeners();
	}

	public TravelClaim chooseClaim() throws EmptyClaimListException {
		int size = claimList.size();
		if (size <= 0) {
			throw new EmptyClaimListException();
		}
		int index = (int) (claimList.size() * Math.random());
		return claimList.get(index);
	}

	public int size() {
		return claimList.size();
	}

	public boolean contains(TravelClaim testClaim) {
		return claimList.contains(testClaim);
	}

	public void addListener(Listener l) {
		getListeners().add(l);
	}

	public void removeListener(Listener l) {
		getListeners().remove(l);
	}
	
	public void sort(){
		DateSort ds = new DateSort();
		int move;
		for(int i=claimList.size()-1; i>0; i--){
			for(int j=0; j<i; j++){
				move = ds.compare(claimList.get(j).getStartDate(), claimList.get(j+1).getStartDate());
				if(move==1){
					Collections.rotate(claimList.subList(j, j+2), -1);
				}
			}
		}
	}
	
}
