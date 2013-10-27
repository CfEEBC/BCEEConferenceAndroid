package com.bcee.conference.android;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.bcee.conference.android.models.DataCentre;
import com.bcee.conference.android.models.UpdateData;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.text.format.Time;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

public class Home extends Activity implements UpdateData {
	private static final String JSON_URL = "http://bceeconference.appspot.com/machine";
	private String lastUpdatedTime;  // check if everything is larger than empty string
	private String currentTime;

	
 

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		Log.d("Home", "ON CREATE");
		Date now = new Date();
		currentTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(now);
		lastUpdatedTime = "";
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.home);
		DataCentre.createDefaultInstance();
		new DataCentre.AsyncParse().execute(JSON_URL,currentTime);
		initvars();
	}

	
	@Override 
	protected void onResume(){
		Log.d("Home", "ON RESUME");
		super.onResume();
		Date now = new Date();
		currentTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(now);
		if(needUpdate(lastUpdatedTime)){
			new DataCentre.AsyncParse().execute(JSON_URL,currentTime);
			Date now1 = new Date();
			lastUpdatedTime = new SimpleDateFormat("yyyy-MM-dd HH").format(now1); 
		}
		
	}
	private void initvars() {
		TextView sessions = (TextView) findViewById(R.id.welcomeB1);
		sessions.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent i = new Intent(v.getContext(),ExpandableList.class);
				startActivityForResult(i,0);
			}
		});
		TextView generalSurvey = (TextView) findViewById(R.id.conferenceGeneralSurvey);
		generalSurvey.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent i = new Intent(Intent.ACTION_VIEW,Uri.parse("http://www.google.com"));
				startActivity(i);
				
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.home, menu);
		return true;
	}

	@Override
	public boolean needUpdate(String lastUpdated) {
		// TODO Auto-generated method stub
		Date now = new Date();
		String lastCheck = new SimpleDateFormat("yyyy-MM-dd HH").format(now); 
		return (lastCheck.compareTo(lastUpdated) >  0)? true:false; 
	}




}

