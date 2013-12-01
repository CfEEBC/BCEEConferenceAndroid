package com.bcee.conference.android;

import java.text.DateFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.URLUtil;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.bcee.conference.android.models.ConferenceModel;

/**
 * Displays the information of a ConferenceModel
 *
 */
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
		TextView name = (TextView) findViewById(R.id.conferencesName);
		name.setText(c.getName());
		TextView descrip = (TextView) findViewById(R.id.conferencesDescription);
		descrip.setText(c.getDescription());
		TextView bio = (TextView) findViewById(R.id.conferencesBiography);
		bio.setText(c.getBiography());
		TextView speakers = (TextView) findViewById(R.id.conferencesSpeakers);
		speakers.setText(c.getSpeakers());
		TextView sTime = (TextView) findViewById(R.id.conferencesTime);
		sTime.setText(extractStartingTime());
		TextView location = (TextView) findViewById(R.id.conferencesLocation);
		location.setText(c.getLocation());
		Button surveyButton = (Button) findViewById(R.id.conferencesB1);
		surveyButton.setOnClickListener(new View.OnClickListener() {
			
			/**
			 * @author http://stackoverflow.com/questions/2201917/how-can-i-open-a-url-in-androids-web-browser-from-my-application
			 */
			@Override
			public void onClick(View v) {
				if(!URLUtil.isValidUrl(c.getSURVEY_LINK())){
					Toast.makeText(getApplicationContext(), "Invalid URL, could not find " + c.getSURVEY_LINK(), Toast.LENGTH_LONG).show();
					return;
				}
				Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(c.getSURVEY_LINK()));
				startActivity(browserIntent);
			} 
		});
	}

	private String extractStartingTime() {
		// TODO Auto-generated method stub
		/*Date d = new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(s);
		String date = new SimpleDateFormat("EEEE HH:mm").format(d);*/
		try {
			Log.d("month",c.getSTART_TIME());
			String start = new SimpleDateFormat("MMMM dd HH:mm").format(new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(c.getSTART_TIME()));
			String end = new SimpleDateFormat("HH:mm").format(new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(c.getEND_TIME()));
			return start+"-"+end;
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return c.getSTART_TIME();
		}
	}

	/**
	 * Extracts the parsed time into a readable format
	 * 
	 * @param s time in MM-DD format
	 * @return time in Month, hh:mm
	 */
	private String extractStartingTime(String s){
		String time = s.substring(6);
		String month = new DateFormatSymbols().getMonths()[Integer.parseInt(s.substring(0,2))-1];
		int date = Integer.parseInt(s.substring(3,5));
		return month + " " + date + ",  " + time;
	}
	
}
