package com.example.machinsk_notes;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.util.Base64;

public class ExpenseListManager {
	static final String prefFile = "ExpenseList";
	static final String slKey = "expenseList"; 
	
	Context context;
	
	static private ExpenseListManager expenseListManager = null;
	
	public static void initManager(Context context) {
		if (expenseListManager == null) {
			if (context==null) {
				throw new RuntimeException("missing context for ExpenseListManager");
			}
			expenseListManager = new ExpenseListManager(context);
		}		
	}
	
	public static ExpenseListManager getManager() {
		if (expenseListManager==null) {
			throw new RuntimeException("Did not initialize Manager");
		}
		return expenseListManager;
	}
	
	
	public ExpenseListManager(Context context) {
		this.context = context;
	}

	public ExpenseList loadExpenseList() throws ClassNotFoundException, IOException {
		SharedPreferences settings = context.getSharedPreferences(prefFile, Context.MODE_PRIVATE);
		String expenseListData = settings.getString(slKey, "");
		if (expenseListData.equals("")) {
			return new ExpenseList();
		} else {
			return expenseListFromString(expenseListData);
		}
	}
	
	static public ExpenseList expenseListFromString(String expenseListData) throws ClassNotFoundException, IOException {
		ByteArrayInputStream bi = new ByteArrayInputStream(Base64.decode(expenseListData, Base64.DEFAULT));
		ObjectInputStream oi = new ObjectInputStream(bi);
		return (ExpenseList)oi.readObject();	
	}
	
	static public String expenseListToString(ExpenseList sl) throws IOException {
		ByteArrayOutputStream bo = new ByteArrayOutputStream();
		ObjectOutputStream oo = new ObjectOutputStream(bo);
		oo.writeObject(sl);
		oo.close();
		byte bytes[] = bo.toByteArray();
		return Base64.encodeToString(bytes,Base64.DEFAULT);
	}
	
	public void saveExpenseList(ExpenseList sl) throws IOException {
		SharedPreferences settings = context.getSharedPreferences(prefFile, Context.MODE_PRIVATE);
		Editor editor = settings.edit();
		editor.putString(slKey, expenseListToString(sl));
		editor.commit();
	}

}
