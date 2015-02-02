package com.example.machinsk_notes;

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
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

//http://www.tutorialspoint.com/android/android_datepicker_control.htm Feb 1 2015
public class ClaimEditingActivity extends Activity {

	private Calendar pickStart;
	private Calendar pickEnd;
	private DatePicker datePicker;
	private TextView dateView;
	private int year, month, day;
	private int claimPosition;
	boolean start, end, ifNew;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.claim_editor);
		
		//http://stackoverflow.com/questions/14876273/simple-example-for-intent-and-bundle  Jan 31 2015
		Bundle extras = getIntent().getExtras();
		ifNew = false;
		if(extras != null){
			ifNew = extras.getBoolean("ifNew");
			if (ifNew){
				pickStart = Calendar.getInstance();
				pickEnd = Calendar.getInstance();
			} else {
				ArrayList<TravelClaim> claims = ClaimController.getClaimList().getClaims();
				claimPosition = extras.getInt("position");
				
				pickStart = claims.get(claimPosition).getStartDate();
				pickEnd = claims.get(claimPosition).getEndDate();
				EditText edName = (EditText) findViewById(R.id.ClaimNameEdit);
				EditText edDesp = (EditText) findViewById(R.id.ClaimDescriptionEdit);
				edName.setText(claims.get(claimPosition).getName());
				edDesp.setText(claims.get(claimPosition).getTextDescription());
			}
			
			TextView tvStart = (TextView) findViewById(R.id.ClaimStartText);
			TextView tvEnd = (TextView) findViewById(R.id.EndDateText);
			
			tvStart.setText( android.text.format.DateFormat.format("yyyy-MM-dd",pickStart).toString());
			tvEnd.setText( android.text.format.DateFormat.format("yyyy-MM-dd",pickEnd).toString());
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


	@SuppressWarnings("deprecation")
	public void startDate(View v){
		Toast.makeText(this, "Start Date", Toast.LENGTH_SHORT).show();
		start = true;
		end = false;
		
		year = pickStart.get(Calendar.YEAR);
	    month = pickStart.get(Calendar.MONTH);
	    day = pickStart.get(Calendar.DAY_OF_MONTH);
		
		showDialog(999);
	}
	
	@SuppressWarnings("deprecation")
	public void endDate(View v){
		Toast.makeText(this, "Start Date", Toast.LENGTH_SHORT).show();
		end = true;
		start = false;

		year = pickEnd.get(Calendar.YEAR);
	    month = pickEnd.get(Calendar.MONTH);
	    day = pickEnd.get(Calendar.DAY_OF_MONTH);
		
		showDialog(999);
	}
	
	public void applyButton(View v){
		EditText claimName = (EditText) findViewById(R.id.ClaimNameEdit);
		EditText claimDesp = (EditText) findViewById(R.id.ClaimDescriptionEdit);
		String name = claimName.getText().toString();
		ArrayList<TravelClaim> claims = ClaimController.getClaimList().getClaims();
		int duplicate = -1;
		
		//check for unique name
		for(int i=0; i<claims.size(); i++){
			duplicate = name.compareTo(claims.get(i).getName());
			if(duplicate==0){
				Toast.makeText(this, "Duplicate Claim Name", Toast.LENGTH_LONG).show();
				return;
			}
		}

		Toast.makeText(this, "Applied", Toast.LENGTH_SHORT).show();
		if(ifNew){
			ClaimController st = new ClaimController();
			st.addClaim(new TravelClaim(claimName.getText().toString(), claimDesp.getText().toString(), pickStart, pickEnd));
		} else {
			claims.get(claimPosition).setStartDate(pickStart);
			claims.get(claimPosition).setEndDate(pickEnd);
			claims.get(claimPosition).setName(claimName.getText().toString());
			claims.get(claimPosition).setTextDescription(claimDesp.getText().toString());
			ClaimController.getClaimList().Update();
			
		}
		
		Intent intent = new Intent(ClaimEditingActivity.this, MainActivity.class);
		startActivity(intent);
	}
	
	public void deleteButton(View v){
		Toast.makeText(this, "Claim Deleted", Toast.LENGTH_SHORT).show();
		
		if(!ifNew){
			//Taking passed claim position and removing it from controller
			ArrayList<TravelClaim> claims = ClaimController.getClaimList().getClaims();
			TravelClaim claim = claims.get(claimPosition);
			ClaimController.getClaimList().removeClaim(claim);
		}
		
		Intent intent = new Intent(ClaimEditingActivity.this, MainActivity.class);
		startActivity(intent);
	}
	
	public void goToExpense(MenuItem menu){
		Toast.makeText(this, "Edit Claim", Toast.LENGTH_SHORT).show();
		Intent intent = new Intent(ClaimEditingActivity.this, ListExpensesActivity.class);
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
			if(start){
				pickStart.set(arg1, arg2, arg3);
				TextView tvStart = (TextView) findViewById(R.id.ClaimStartText);
				tvStart.setText( android.text.format.DateFormat.format("yyyy-MM-dd",pickStart).toString());
			}
			if(end){
				pickEnd.set(arg1, arg2, arg3);
				TextView tvEnd = (TextView) findViewById(R.id.EndDateText);
				tvEnd.setText( android.text.format.DateFormat.format("yyyy-MM-dd",pickEnd).toString());
			}
	   	}
	};
	

}
