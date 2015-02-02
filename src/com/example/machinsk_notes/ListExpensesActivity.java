package com.example.machinsk_notes;

import java.math.BigDecimal;
import java.util.ArrayList;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemLongClickListener;

public class ListExpensesActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.list_expenses);
		
		ListView expenseListView = (ListView) findViewById(R.id.ExpenseListView);
		ArrayList<Expense> expenses = ExpenseController.getExpenseList().getExpenses();
		final ArrayList<String> list = new ArrayList<String>();
		for(int i = 0; i<expenses.size(); i++){
			list.add(expenses.get(i).getAmountSpent().toString() + " " + expenses.get(i).getCurrency() + "\nDate: " + android.text.format.DateFormat.format("yyyy-MM-dd",expenses.get(i).getDate()));
		}
		final ArrayAdapter<String> expenseAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, list);
		expenseListView.setAdapter(expenseAdapter);
	
		expenseListView.setOnItemLongClickListener(new OnItemLongClickListener() {
			@Override
			public boolean onItemLongClick(AdapterView<?> adapterView, View view, int expensePosition, long id){
				Intent intent = new Intent(ListExpensesActivity.this, ExpenseEditorActivity.class);
				intent.putExtra("ifNew", false);
				intent.putExtra("position", expensePosition);
				startActivity(intent);
				return false;
			}
		});
		
		
		
		ExpenseController.getExpenseList().addListener(new Listener() {
			@Override
			public void update() {
				list.clear();
				ArrayList<Expense> expenses = ExpenseController.getExpenseList().getExpenses();
				for(int i = 0; i<expenses.size(); i++){
					list.add(expenses.get(i).getAmountSpent().toString() + " " + expenses.get(i).getCurrency() + "\nStarting: " + android.text.format.DateFormat.format("yyyy-MM-dd",expenses.get(i).getDate()));
				}
				expenseAdapter.notifyDataSetChanged();
			}
		});
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.list_expenses, menu);
		return true;
	}

	
	public void newExpense(View v){
		Toast.makeText(this, "New Claim", Toast.LENGTH_SHORT).show();
		Intent i = new Intent(this, ExpenseEditorActivity.class);
		i.putExtra("ifNew", true);
		startActivity(i);
	}
}
