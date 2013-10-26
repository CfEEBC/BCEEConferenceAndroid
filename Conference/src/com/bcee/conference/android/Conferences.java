package com.bcee.conference.android;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class Conferences extends Activity {

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.conferences);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.conferences, menu);
		return true;
	}

}
