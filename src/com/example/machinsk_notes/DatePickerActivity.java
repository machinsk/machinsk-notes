package com.example.machinsk_notes;

import java.util.Calendar;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

public class DatePickerActivity extends Activity {

	private Calendar cDate;
	private boolean start;
	private boolean end;
	private boolean expense;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_date_picker);
		
		//get date
		Bundle extras = getIntent().getExtras();
		if(extras != null){
			start = extras.getBoolean("start");
			end = extras.getBoolean("end");
			expense = extras.getBoolean("expense");
		}
		
		final DatePicker datepicker = (DatePicker) findViewById(R.id.date_picker);
		final TextView dateType = (TextView) findViewById(R.id.DateTypeText);
		
		if(start){
			dateType.setText("Start Date:");
		}
		if(end){
			dateType.setText("End Date:");
		}
		if(expense){
			dateType.setText("Date of Expense:");
		}
		

		datepicker.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Toast.makeText(DatePickerActivity.this, datepicker.getMonth() + " " + datepicker.getDayOfMonth() + " " + datepicker.getYear(), Toast.LENGTH_SHORT).show();
			}
		});
		
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.date_picker, menu);
		return true;
	}

	
	public void applyDate(View v){
		final DatePicker datepicker = (DatePicker) findViewById(R.id.date_picker);
		cDate.set(datepicker.getYear(), datepicker.getMonth(), datepicker.getDayOfMonth());
		
		if(start){
			Intent intent = new Intent(this, ClaimEditingActivity.class);
			intent.putExtra("start", true);
			intent.putExtra("date", cDate);
			startActivity(intent);
		}
		if(end){
			Intent intent = new Intent(this, ClaimEditingActivity.class);
			intent.putExtra("end", true);
			intent.putExtra("date", cDate);
			startActivity(intent);
		}
		if(expense){
//			Intent intent = new Intent(this, ClaimEditingActivity.class);
//			intent.putExtra("expense", true);
//			intent.putExtra("date", cDate);
//			startActivity(intent);
		}
		
	}
}
