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
import java.util.Collection;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends Activity {
	
	private static final String FILENAME = "file.sav";
	private ArrayAdapter<String> adapter;
	protected ClaimList claims = new ClaimList();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		ClaimListManager.initManager(this.getApplicationContext());
		
		final ListView claimListView = (ListView) findViewById(R.id.ClaimListView);
		Collection<TravelClaim> claims = ClaimController.getClaimList().getClaims();
		final ArrayList<TravelClaim> list = new ArrayList<TravelClaim>(claims);
		final ArrayAdapter<TravelClaim> claimAdapter = new ArrayAdapter<TravelClaim>(this, android.R.layout.simple_list_item_1, list);
		claimListView.setAdapter(claimAdapter);
//		claimListView.setOnItemClickListener(new OnItemClickListener() {
//
//			@Override
//			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
//				int selectedPos = (Integer) claimListView.getItemAtPosition(arg2);
//				
//			}
//			
//		});
		
		
		
		ClaimController.getClaimList().addListener(new Listener() {
			@Override
			public void update() {
				list.clear();
				Collection<TravelClaim> claims = ClaimController.getClaimList().getClaims();
				list.addAll(claims);
				claimAdapter.notifyDataSetChanged();
			}
		});
		
		
		
	}

//	@Override
//	protected void onStart() {
//		ArrayList<UserLike> users = new ArrayList<UserLike>();
//		super.onStart();
//		tweets = loadFromFile();
//		adapter = new ArrayAdapter<String>(this,
//		R.layout.list_item, tweets);
//		oldTweetsList.setAdapter(adapter);
//		
//		Bundle claimData = getIntent().getExtras();
//		if (claimData==null){
//			return;
//		}
//		TravelClaim claimToAdd = (TravelClaim) claimData.getBoolean(key)
//		
//	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	
	
	public void newClaim(View v){
		Toast.makeText(this, "New Claim", Toast.LENGTH_SHORT).show();
		Intent i = new Intent(this, ClaimEditingActivity.class);
		startActivity(i);
	}
	
	public void editClaim(View v){
		Toast.makeText(this, "Edit Claim", Toast.LENGTH_SHORT).show();

	}
	
	public void goToExpense(MenuItem menu){
		Toast.makeText(this, "Edit Claim", Toast.LENGTH_SHORT).show();

	}
	
	
	
	

}
