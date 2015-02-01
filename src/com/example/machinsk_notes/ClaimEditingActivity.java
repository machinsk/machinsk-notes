package com.example.machinsk_notes;

import java.util.Calendar;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ClaimEditingActivity extends Activity {

	private Calendar pickStart;
	private Calendar pickEnd;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.claim_editor);
		
		//http://stackoverflow.com/questions/14876273/simple-example-for-intent-and-bundle  Jan 31 2015
		Bundle extras = getIntent().getExtras();
		if(extras != null){
			boolean ifNew = extras.getBoolean("ifNew");
			if (ifNew){
				pickStart = Calendar.getInstance();
				pickEnd = Calendar.getInstance();
			} else {
				
			}
		}
		
		//Date Picker for start and End Date buttons
//		Button bStart = (Button) findViewById(R.id.StartDateButton);
//		Button bEnd = (Button) findViewById(R.id.EndDateButton);
		
		
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		
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
	
	public void endDate(View v){
		Toast.makeText(this, "End Date", Toast.LENGTH_SHORT).show();
		Intent intent = new Intent(ClaimEditingActivity.this, DatePickerActivity.class);
		startActivity(intent);
	}
	
	public void applyButton(View v){
		Toast.makeText(this, "Applied", Toast.LENGTH_SHORT).show();
		
		ClaimController st = new ClaimController();
		EditText claimName = (EditText) findViewById(R.id.ClaimNameEdit);
		EditText claimDesp = (EditText) findViewById(R.id.ClaimDescriptionEdit);
		st.addClaim(new TravelClaim(claimName.getText().toString(), claimDesp.getText().toString(), pickStart, pickEnd));
		
		
		Intent intent = new Intent(ClaimEditingActivity.this, MainActivity.class);
		startActivity(intent);
	}
	
	public void deleteButton(View v){
		Toast.makeText(this, "Claim Deleted", Toast.LENGTH_SHORT).show();
		Intent intent = new Intent(ClaimEditingActivity.this, MainActivity.class);
		startActivity(intent);
	}
	
	
	

}
