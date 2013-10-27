package com.bcee.conference.android.models;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

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

/**
 * 
 * Data Centre that stores all ConferenceModels with the information parsed from server and provides methods to 
 * access the data 
 * 
 * @author 
 *
 */
public class DataCentre {

	private static final String JSON_URL = "http://bceeconference.appspot.com/machine";
	
	/**
	 * the DataCentre
	 */
	private static DataCentre defaultInstance;
	/**
	 * Collection of ConferenceModels
	 */
	private static Collection <ConferenceModel> models = new ArrayList<ConferenceModel>();

	/**
	 * instance of DataCentre 
	 * @return  the designated DataCentre for this app
	 */
	public static DataCentre createDefaultInstance() {
		// TODO Auto-generated method stub
		if(defaultInstance==null){
			defaultInstance = new DataCentre();
			DataCentre.parseData();
		}
		return defaultInstance;
	}
	
	/**
	 * get conferences that starts at a particular time 
	 * @param time conference's start time
	 * @return a list of names of conferences starting at a particular time 
	 */
	
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
	/**
	 * Starts parsing our data that we get from the server to populate fields in our ConferenceModel
	 */
	public static void parseData(){
		HttpClient client = new DefaultHttpClient();  
		String url = JSON_URL;   
		HttpGet httpget = new HttpGet(url);
		ResponseHandler<String> handler = new BasicResponseHandler();
		try{
			String jsonString = client.execute(httpget,handler);
			JSONArray arr = new JSONArray(jsonString);
			//  gets the fields 
			for(int i=0;i<arr.length();i++){
				JSONObject obj = (JSONObject) arr.get(i);
				String name = (String) obj.get("session_name");
				String location = (String) obj.get("location");
				// truncate strings into "MM-DD hh:mm"
				String start = obj.getString("stime").substring(5,obj.getString("stime").length()-3);
				String end = obj.getString("etime").substring(5,obj.getString("etime").length()-3);
				String descrip = obj.getString("description");
				String speakers = obj.getString("speakers");
				String bio = obj.getString("biography");
				String survey = httpCheck(obj.getString("survey_link"));
				ConferenceModel c = new ConferenceModel(name,descrip,location,speakers,bio,start,end,survey);
				System.out.println(models==null);
				models.add(c);  // adds the new ConferenceModel to our collection
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
	
	/**
	 * Checks if the string url has http header
	 * @param s the url string
	 * @return the url string with if it has a proper http header; otherwise add the header 
	 */
	private static String httpCheck(String s){
		if(s.substring(0,6).equals("http://"))
			return s;
		else return ("http://" + s);
	}
	
	/**
	 * Find the ConferenceModel by startTime and name 
	 * @param start Conference startTime
	 * @param name  Conference name 
	 * @return the ConferenceModel with the corresponding startTime and name when found; otherwise return null
	 */
	public ConferenceModel findConference(String start, String name){
		for(ConferenceModel c:models){
			if(c.getSTART_TIME().equals(start) && c.getName().equals(name)){
				return c;
			}
		}
		return null;
	}
	
	/**
	 * Get all the startTime as a collection of strings
	 * @return all startTime as collection of strings
	 */
	public Set<String> getStartTimes(){
		Set<String> s = new TreeSet<String>();
		for(ConferenceModel c:models){
			s.add(c.getSTART_TIME());
		}
		return s;
	}
}
