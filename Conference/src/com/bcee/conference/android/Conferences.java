package com.bcee.conference.android;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.webkit.URLUtil;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.bcee.conference.android.models.ConferenceModel;

public class Conferences extends Activity {

	private ConferenceModel c;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.conferences);
		Intent i = getIntent();
		c = (ConferenceModel) i.getParcelableExtra("conference");
		initvars();
	}

	/**
	 * Corresponds the views in Conferences with the xml
	 */
	private void initvars() {
		// TODO Auto-generated method stub
		
		TextView name = (TextView) findViewById(R.id.conferencesName);
		name.setText("Name:     " + c.getName());
		TextView descrip = (TextView) findViewById(R.id.conferencesDescription);
		descrip.setText("Description:     " + c.getDescription());
		TextView bio = (TextView) findViewById(R.id.conferencesBiography);
		bio.setText("Biography:     " + c.getBiography());
		TextView speakers = (TextView) findViewById(R.id.conferencesSpeakers);
		speakers.setText("Speakers:     " + c.getSpeakers());
		TextView sTime = (TextView) findViewById(R.id.conferencesSTime);
		sTime.setText("Starting Time:     " + c.getSTART_TIME());
		TextView eTime = (TextView) findViewById(R.id.conferencesETime);
		eTime.setText("Ending Time:     " + c.getEND_TIME());
		TextView location = (TextView) findViewById(R.id.conferencesLocation);
		location.setText("Location:     " + c.getLocation());
		Button surveyButton = (Button) findViewById(R.id.conferencesB1);
		surveyButton.setOnClickListener(new View.OnClickListener() {
			
			// Found on Oct 26 from
			// http://stackoverflow.com/questions/2201917/how-can-i-open-a-url-in-androids-web-browser-from-my-application
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(!URLUtil.isValidUrl(c.getSURVEY_LINK())){
					Toast.makeText(getApplicationContext(), "Invalid URL, could not find " + c.getSURVEY_LINK(), Toast.LENGTH_LONG).show();
					return;
				}
				Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(c.getSURVEY_LINK()));
				startActivity(browserIntent);
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.conferences, menu);
		return true;
	}

}
