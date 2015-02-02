package com.example.machinsk_notes;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;

public class ExpenseList implements Serializable {
	
	/**
	 * ExpenseList serialization ID
	 */
	private static final long serialVersionUID = -6576632047805335174L;
	
	protected ArrayList<Expense> expenseList = null;
	protected transient ArrayList<Listener> listeners = null;
	
	public ExpenseList() {
		expenseList = new ArrayList<Expense>();		
		listeners = new ArrayList<Listener>();
	}
	
	private ArrayList<Listener> getListeners() {
		if (listeners == null ) {
			listeners = new ArrayList<Listener>();
		}
		return listeners;
	}
	
	public ArrayList<Expense> getExpenses() {
		return expenseList;		
	}

	public void addExpense(Expense testExpense) {
		expenseList.add(testExpense);
		notifyListeners();
	}

	private void notifyListeners() {
		this.sort();
		for (Listener  listener : getListeners()) {
			listener.update();
		}
	}
	
	public void removeExpense(Expense testExpense) {
		expenseList.remove(testExpense);
		notifyListeners();
	}

	public Expense chooseExpense() throws EmptyExpenseListException {
		int size = expenseList.size();
		if (size <= 0) {
			throw new EmptyExpenseListException();
		}
		int index = (int) (expenseList.size() * Math.random());
		return expenseList.get(index);
	}

	public int size() {
		return expenseList.size();
	}

	public boolean contains(Expense testExpense) {
		return expenseList.contains(testExpense);
	}

	public void addListener(Listener l) {
		getListeners().add(l);
	}

	public void removeListener(Listener l) {
		getListeners().remove(l);
	}
	
	public void Update(){
		notifyListeners();
	}
	
	
	//https://community.oracle.com/thread/1155768
	public void sort(){
		Collections.sort(expenseList, new Expense.SortByDate());
	}
	
}
