package com.bcee.conference.android.models;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

public class DataCentre {

	private static DataCentre defaultInstance = createDefaultInstance();
	private static Collection <ConferenceModel> models = new ArrayList<ConferenceModel>();

	public static DataCentre createDefaultInstance() {
		// TODO Auto-generated method stub
		if(defaultInstance==null){
			defaultInstance = new DataCentre();
			parseData();
		}
		return defaultInstance;
	}
	
	public static List<String> getDataByTime(String time) {
		// TODO Auto-generated method stub
		List<String> arr = new ArrayList<String>();
		for(ConferenceModel c:models){
			if(c.getSTART_TIME().equals(time)){
				arr.add(c.getName());
			}
		}
		return arr;
	}

	//Parse info methods
	public static void parseData(){
		HttpClient client = new DefaultHttpClient();
		String url = "http://napon.googlecode.com/svn/sampleJSON.json/";
		HttpGet httpget = new HttpGet(url);
		ResponseHandler<String> handler = new BasicResponseHandler();
		try{
			String jsonString = client.execute(httpget,handler);
			JSONArray arr = new JSONArray(jsonString);
			for(int i=0;i<arr.length();i++){
				JSONObject obj = (JSONObject) arr.get(i);
				System.out.println(obj);
				String name = (String) obj.get("sessionName");
				String location = (String) obj.get("location");
				String start = obj.getString("start_time");
				String end = obj.getString("end_time");
				String descrip = obj.getString("description");
				String speakers = obj.getString("speakers");
				String bio = obj.getString("biography");
				ConferenceModel c = new ConferenceModel(name,descrip,location,speakers,bio,start,end,"survey url");
				models.add(c);
				Log.d("tag",start);
				
			}
		} catch(JSONException e){
			Log.d("LOG_TAG",e.getMessage());
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public ConferenceModel findConference(String start, String name){
		for(ConferenceModel c:models){
			if(c.getSTART_TIME().equals(start) && c.getName().equals(name)){
				return c;
			}
		}
		return null;
	}
	
	public Set<String> getStartTimes(){
		Set<String> s = new HashSet<String>();
		for(ConferenceModel c:models){
			s.add(c.getSTART_TIME());
		}
		return s;
	}
}
