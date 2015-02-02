package com.example.machinsk_notes;

import java.util.ArrayList;
import java.util.Calendar;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ClaimEditingActivity extends Activity {

	private Calendar pickStart;
	private Calendar pickEnd;
	private int claimPosition;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.claim_editor);
		
		//http://stackoverflow.com/questions/14876273/simple-example-for-intent-and-bundle  Jan 31 2015
		Bundle extras = getIntent().getExtras();
		if(extras != null){
			boolean ifNew = extras.getBoolean("ifNew");
			boolean ifStart = extras.getBoolean("start");
			boolean ifEnd = extras.getBoolean("end");
			if (ifNew){
				pickStart = Calendar.getInstance();
				pickEnd = Calendar.getInstance();
			} else {
				ArrayList<TravelClaim> claims = ClaimController.getClaimList().getClaims();
				claimPosition = extras.getInt("position");
				if(ifStart){
					pickStart = (Calendar)extras.get("date");
					pickEnd = claims.get(claimPosition).getEndDate();
				} else if(ifEnd){
					pickStart = claims.get(claimPosition).getStartDate();
					pickEnd = (Calendar)extras.get("date");
				} else {
					pickStart = claims.get(claimPosition).getStartDate();
					pickEnd = claims.get(claimPosition).getEndDate();
				}
				
				TextView tvStart = (TextView) findViewById(R.id.ClaimStartText);
				TextView tvEnd = (TextView) findViewById(R.id.EndDateText);
				EditText edName = (EditText) findViewById(R.id.ClaimNameEdit);
				EditText edDesp = (EditText) findViewById(R.id.ClaimDescriptionEdit);
				
				tvStart.setText( android.text.format.DateFormat.format("yyyy-MM-dd",pickStart));
				tvEnd.setText( android.text.format.DateFormat.format("yyyy-MM-dd",pickEnd));
				edName.setText(claims.get(claimPosition).getName());
				edDesp.setText(claims.get(claimPosition).getTextDescription());
			}
		}
		

		
		
		
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
		intent.putExtra("start", true);
		intent.putExtra("position", claimPosition);
		startActivity(intent);
	}
	
	public void endDate(View v){
		Toast.makeText(this, "End Date", Toast.LENGTH_SHORT).show();
		Intent intent = new Intent(ClaimEditingActivity.this, DatePickerActivity.class);
		intent.putExtra("end", true);
		intent.putExtra("position", claimPosition);
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
		
		//Taking passed claim position and removing it from controller
		ArrayList<TravelClaim> claims = ClaimController.getClaimList().getClaims();
		TravelClaim claim = claims.get(claimPosition);
		ClaimController.getClaimList().removeClaim(claim);
		
		Intent intent = new Intent(ClaimEditingActivity.this, MainActivity.class);
		startActivity(intent);
	}
	
	public void goToExpense(MenuItem menu){
		Toast.makeText(this, "Edit Claim", Toast.LENGTH_SHORT).show();
		Intent intent = new Intent(ClaimEditingActivity.this, ListExpensesActivity.class);
		startActivity(intent);

	}
	
	
	

}
