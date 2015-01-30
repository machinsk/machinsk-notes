/*
Tracks Expenses for Various Travel Claims

Copyright (C) 2015 Jonathon Machinski machinsk@ualbera.ca

This program is free software: you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation, either version 3 of the License, or
(at your option) any later version.
This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
GNU General Public License for more details.
You should have received a copy of the GNU General Public License
along with this program. If not, see <http://www.gnu.org/licenses/>.
*/

package com.example.machinsk_notes;

import java.util.ArrayList;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends Activity {

	private static final String FILENAME = "file.sav";
	private ListView claimList;
	private ArrayAdapter<String> adapter;
	private ArrayList<String> claims;
	
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		claimList = (ListView) findViewById(R.id.ClaimListView);
		
		
	}

	@Override
	protected void onStart() {
//		ArrayList<UserLike> users = new ArrayList<UserLike>();
		super.onStart();
//		tweets = loadFromFile();
//		adapter = new ArrayAdapter<String>(this,
//		R.layout.list_item, tweets);
//		oldTweetsList.setAdapter(adapter);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	public void newClaim(View v){
		Toast.makeText(this, "Hurray", Toast.LENGTH_LONG).show();
		Intent intent = new Intent(MainActivity.this, ClaimEditingActivity.class);
		startActivity(intent);
	}

}
