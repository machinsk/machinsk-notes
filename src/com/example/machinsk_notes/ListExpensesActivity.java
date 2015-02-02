package com.example.machinsk_notes;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class ListExpensesActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.list_expenses);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.list_expenses, menu);
		return true;
	}

}
