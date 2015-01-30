package com.example.machinsk_notes;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.DatePicker;
import android.widget.Toast;

public class DatePickerActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_date_picker);
		
		final DatePicker datepicker = (DatePicker) findViewById(R.id.date_picker);

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

}
