package com.example.machinsk_notes;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class ClaimEditingActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.claim_editor);
		
		//Date Picker for start and End Date buttons
		Button bStart = (Button) findViewById(R.id.StartDateButton);
		Button bEnd = (Button) findViewById(R.id.EndDateButton);
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.claim_editing, menu);
		return true;
	}

	
	public void startDate(View v){
		Toast.makeText(this, "Start Date", Toast.LENGTH_SHORT).show();
		Intent intent = new Intent(ClaimEditingActivity.this, DatePickerActivity.class);
		startActivity(intent);
	}
	
	
	

}
