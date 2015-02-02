package com.example.machinsk_notes;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;

import android.os.Bundle;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ExpenseEditorActivity extends Activity {

	private Calendar pickDate;
	private DatePicker datePicker;
	private TextView dateView;
	private int year, month, day;
	private int expensePosition;
	boolean start, end, ifNew;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.expense_editor);
		
		//http://stackoverflow.com/questions/14876273/simple-example-for-intent-and-bundle  Jan 31 2015
		Bundle extras = getIntent().getExtras();
		ifNew = false;
		if(extras != null){
			ifNew = extras.getBoolean("ifNew");
			if (ifNew){
				pickDate = Calendar.getInstance();
			} else {
				ArrayList<Expense> expenses = ExpenseController.getExpenseList().getExpenses();
				expensePosition = extras.getInt("position");
				
				pickDate = expenses.get(expensePosition).getDate();
				EditText edCategory = (EditText) findViewById(R.id.CategoryEdit);
				EditText edAmount = (EditText) findViewById(R.id.AmountEdit);
				EditText edCurrency = (EditText) findViewById(R.id.CurencyEdit);
				EditText edDescription = (EditText) findViewById(R.id.DescriptionEdit);
				edCategory.setText(expenses.get(expensePosition).getCategory());
				edAmount.setText(expenses.get(expensePosition).getAmountSpent().toString());
				edCurrency.setText(expenses.get(expensePosition).getCurrency());
				edDescription.setText(expenses.get(expensePosition).getDescription());
			}
			
			TextView tvDate = (TextView) findViewById(R.id.ExpenseDateText);
			
			tvDate.setText( android.text.format.DateFormat.format("yyyy-MM-dd",pickDate).toString());
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.expense_editor, menu);
		return true;
	}



	@SuppressWarnings("deprecation")
	public void pickDate(View v){
		Toast.makeText(this, "Start Date", Toast.LENGTH_SHORT).show();
		start = true;
		end = false;
		
		year = pickDate.get(Calendar.YEAR);
	    month = pickDate.get(Calendar.MONTH);
	    day = pickDate.get(Calendar.DAY_OF_MONTH);
		
		showDialog(999);
	}
	
	public void applyExpense(View v){
		EditText edCategory = (EditText) findViewById(R.id.CategoryEdit);
		EditText edAmount = (EditText) findViewById(R.id.AmountEdit);
		EditText edCurrency = (EditText) findViewById(R.id.CurencyEdit);
		EditText edDescription = (EditText) findViewById(R.id.DescriptionEdit);
		ArrayList<Expense> expenses = ExpenseController.getExpenseList().getExpenses();
		
		

		Toast.makeText(this, "Applied", Toast.LENGTH_SHORT).show();
		if(ifNew){
			ExpenseController st = new ExpenseController();
			st.addExpense(new Expense(edCategory.getEditableText().toString(),edDescription.getEditableText().toString(),edCurrency.getEditableText().toString(), pickDate, Double.parseDouble(edAmount.getEditableText().toString())));
		} else {
			expenses.get(expensePosition).setDate(pickDate);
			expenses.get(expensePosition).setAmountSpent( Double.parseDouble(edAmount.getEditableText().toString()));
			expenses.get(expensePosition).setCategory(edCategory.getEditableText().toString());
			expenses.get(expensePosition).setCurrency(edCurrency.getEditableText().toString());
			expenses.get(expensePosition).setDescription(edDescription.getEditableText().toString());
			ExpenseController.getExpenseList().Update();
			
		}
		
		Intent intent = new Intent(ExpenseEditorActivity.this, ListExpensesActivity.class);
		Toast.makeText(this, "Start Date", Toast.LENGTH_SHORT).show();
		startActivity(intent);
	}
	
	
	public void deleteExpense(View v){
		Toast.makeText(this, "Expense Deleted", Toast.LENGTH_SHORT).show();
		
		if(!ifNew){
			//Taking passed expense position and removing it from controller
			ArrayList<Expense> expenses = ExpenseController.getExpenseList().getExpenses();
			Expense expense = expenses.get(expensePosition);
			ExpenseController.getExpenseList().removeExpense(expense);
		}
		
		Intent intent = new Intent(ExpenseEditorActivity.this, ListExpensesActivity.class);
		startActivity(intent);
	}
	

	@Override
   	protected Dialog onCreateDialog(int id) {
	   // TODO Auto-generated method stub
	   	if (id == 999) {
    	  return new DatePickerDialog(this, myDateListener, year, month, day);
       	}
      	return null;
   	}
	
	
	private DatePickerDialog.OnDateSetListener myDateListener = new DatePickerDialog.OnDateSetListener() {

		@Override
	   	public void onDateSet(DatePicker arg0, int arg1, int arg2, int arg3) {
			// TODO Auto-generated method stub
		    // arg1 = year
		    // arg2 = month
		    // arg3 = day
			pickDate.set(arg1, arg2, arg3);
			TextView tvStart = (TextView) findViewById(R.id.ExpenseDateText);
			tvStart.setText( android.text.format.DateFormat.format("yyyy-MM-dd",pickDate).toString());
			
	   	}
	};
	
	
}
