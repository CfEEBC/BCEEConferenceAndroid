package com.bcee.conference.android;

import com.bcee.conference.android.models.DataCentre;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.TextView;
import android.widget.Toast;

public class Home extends Activity{
	private static final String JSON_URL = "http://bceeconference.appspot.com/machine";


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.home);
		// instantiate DataCentre 
		DataCentre.createDefaultInstance();
		// check for network connection needed to parse data from server
		if(!isNetworkConnected())
			Toast.makeText(getApplicationContext(), "Internet connection needed to show latest schedule" , Toast.LENGTH_SHORT).show();;
		// parse data from server
		new DataCentre.AsyncParse().execute(JSON_URL);
		initvars();
	}
	
	/**
	 * @author http://stackoverflow.com/questions/9570237/android-check-internet-connection
	 * 
	 * @return whether device is connected to network
	 */
	private boolean isNetworkConnected() {
		ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
		return( cm.getActiveNetworkInfo() != null);
	}
	
	private void initvars() {
		//  Sessions
		TextView sessions = (TextView) findViewById(R.id.welcomeB1);
		sessions.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent i = new Intent(v.getContext(),ExpandableList.class);
				startActivityForResult(i,0);
			}
		});
		// Conference survey
		TextView generalSurvey = (TextView) findViewById(R.id.conferenceGeneralSurvey);
		generalSurvey.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent i = new Intent(Intent.ACTION_VIEW,Uri.parse("http://www.google.com"));
				startActivity(i);
				
			}
		});
	}
	
}

