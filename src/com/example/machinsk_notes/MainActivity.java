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
import android.widget.AdapterView.OnItemLongClickListener;
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
		ExpenseListManager.initManager(this.getApplicationContext());
		
		ListView claimListView = (ListView) findViewById(R.id.ClaimListView);
		ArrayList<TravelClaim> claims = ClaimController.getClaimList().getClaims();
		final ArrayList<String> list = new ArrayList<String>();
		for(int i = 0; i<claims.size(); i++){
			list.add(claims.get(i).getName().toString() + "\nStarting: " + android.text.format.DateFormat.format("yyyy-MM-dd",claims.get(i).getStartDate())/* + "\n" + claims.get(i).getTextDescription().toString()*/);
		}
		final ArrayAdapter<String> claimAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, list);
		claimListView.setAdapter(claimAdapter);
	
		claimListView.setOnItemLongClickListener(new OnItemLongClickListener() {
			@Override
			public boolean onItemLongClick(AdapterView<?> adapterView, View view, int claimPosition, long id){
				Intent intent = new Intent(MainActivity.this, ClaimEditingActivity.class);
				intent.putExtra("ifNew", false);
				intent.putExtra("position", claimPosition);
				startActivity(intent);
				return false;
			}
		});
		
		
		
		ClaimController.getClaimList().addListener(new Listener() {
			@Override
			public void update() {
				list.clear();
				ArrayList<TravelClaim> claims = ClaimController.getClaimList().getClaims();
				for(int i = 0; i<claims.size(); i++){
					list.add(claims.get(i).getName().toString() + "\nStarting: " + android.text.format.DateFormat.format("yyyy-MM-dd",claims.get(i).getStartDate()));
				}
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
		i.putExtra("ifNew", true);
		startActivity(i);
	}
	
	
	
	

}
