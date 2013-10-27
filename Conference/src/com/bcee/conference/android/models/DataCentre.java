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

import android.os.AsyncTask;

/**
 * 
 * Data Centre - stores all ConferenceModels and provides methods to these conferences
 *
 */
public class DataCentre {

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
		if(defaultInstance==null)
			defaultInstance = new DataCentre();
		return defaultInstance;
	}

	/**
	 * get conferences that starts at a particular time 
	 * @param time conference's start time
	 * @return a list of names of conferences starting at a particular time 
	 */

	public static List<String> getDataByTime(String time) {
		List<String> arr = new ArrayList<String>();
		for(ConferenceModel c:models){
			if(c.getSTART_TIME().equals(time)){
				arr.add(c.getName());
			}
		}
		return arr;
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


	public static final class AsyncParse extends AsyncTask <String, Void, Void>{

		@Override
		protected Void doInBackground(String... arg0) {
			HttpClient client = new DefaultHttpClient();  
			HttpGet needUpdate = new HttpGet("http://bceeconference.appspot.com/machineMeta");			
			HttpGet getData = new HttpGet(arg0[0]);
			ResponseHandler<String> handler = new BasicResponseHandler();
			try {
				String updateString = client.execute(needUpdate,handler);
				String lastUpdate = new JSONObject(updateString).getString("last_update");
				if(arg0[1].equals("START") || arg0[1].compareTo(lastUpdate) > 0 || !(models.size() >0)){
					String jsonString = client.execute(getData,handler);
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
						models.add(c); 
					}
				}
			} catch (ClientProtocolException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (JSONException e) {
				e.printStackTrace();
			} 
			return null;
		}

	}

	/**
	 * Helper method that checks if the string url has http header
	 * @param s the url string
	 * @return the url string with if it has a proper http header; otherwise add the header 
	 */
	private static String httpCheck(String s){
		System.out.println("url : " + s);
		if(s.substring(0,8).equals("https://") || s.substring(0,7).equals("http://"))
			return s;
		else return ("http://" + s);
	}

}

