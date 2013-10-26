package com.bcee.conference.android;

import com.bcee.conference.android.models.DataCentre;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.widget.Button;

public class Home extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.home);
		DataCentre dc = DataCentre.createDefaultInstance();
		initvars();
	}

	private void initvars() {
		// TODO Auto-generated method stub
		Button sessions = (Button) findViewById(R.id.welcomeB1);
		sessions.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i = new Intent(v.getContext(),ExpandableList.class);
				startActivityForResult(i,0);
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.home, menu);
		return true;
	}

}
