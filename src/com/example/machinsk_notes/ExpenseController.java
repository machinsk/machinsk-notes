package com.example.machinsk_notes;

import java.io.IOException;

//Modified from:
//https://github.com/abramhindle/student-picker/blob/master/src/ca/softwareprocess/studentpicker/StudentListController.java  Feb 1 2015

public class ExpenseController {
	
	// Lazy Singleton 
	private static ExpenseList expenseList = null;
	
	
	// Warning: throws a runTimeException
	static public ExpenseList getExpenseList() {
		if (expenseList == null) {
			try {
				expenseList = ExpenseListManager.getManager().loadExpenseList();
				expenseList.addListener(new Listener() {					
					@Override
					public void update() {
						saveExpenseList();
					}
				});
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				throw new RuntimeException("Could not deserialize ExpenseList from ExpenseListManager");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				throw new RuntimeException("Could not deserialize ExpenseList from ExpenseListManager");
			}
		}
		return expenseList;			
	}
	
	
	static public void saveExpenseList() {
		try {
			ExpenseListManager.getManager().saveExpenseList(getExpenseList());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException("Could not deserialize ExpenseList from ExpenseListManager");
		}		
	}
	
	
	public Expense chooseExpense() throws EmptyExpenseListException {
		return getExpenseList().chooseExpense();		
	}
	
	
	public void addExpense(Expense expense) { 
		getExpenseList().addExpense(expense);
	}
	
}
