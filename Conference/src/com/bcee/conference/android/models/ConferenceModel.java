package com.bcee.conference.android.models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * ConferenceModel - Structure for conference with name, description, location, speakers, biography, START_TIME, END_TIME, SURVEY_LINK
 * implementing comparable 
 * @author 
 *
 */
public class ConferenceModel implements Comparable<ConferenceModel>, Parcelable{

	/**
	 * ConferenceModel's name
	 */
	private String name;
	/**
	 * ConferenceModel's description
	 */
	private String description;
	/**
	 * ConferenceModel location
	 */
	private String location;
	/**
	 * ConferenceModel's speakers
	 */
	private String speakers;
	/**
	 * ConferenceModel's biography
	 */
	private String biography;
	/**
	 * ConferenceModel's start time
	 */
	private String START_TIME;
	/**
	 * ConferenceModel's end time
	 */
	private String END_TIME;
	/**
	 * ConferenceModel's link to survey
	 */
	private String SURVEY_LINK;
	
	
	/**
	 * ConferenceModel constructor
	 * @param name  name 
	 * @param desc  description
	 * @param loc   location
	 * @param speakers  speakers
	 * @param bio   speaker's biography
	 * @param start  start time
	 * @param end   end time
	 * @param url   link to survey
	 */
	public ConferenceModel(String name, String desc, String loc, String speakers, String bio, String start, String end, String url){
		this.name = name;
		this.description = desc;
		this.location = loc;
		this.speakers = speakers;
		this.biography = bio;
		this.START_TIME = start;
		this.END_TIME = end;
		this.SURVEY_LINK = url;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getSpeakers() {
		return speakers;
	}

	public void setSpeakers(String speakers) {
		this.speakers = speakers;
	}

	public String getBiography() {
		return biography;
	}

	public void setBiography(String biography) {
		this.biography = biography;
	}

	public String getSTART_TIME() {
		return START_TIME;
	}

	public void setSTART_TIME(String sTART_TIME) {
		START_TIME = sTART_TIME;
	}

	public String getEND_TIME() {
		return END_TIME;
	}

	public void setEND_TIME(String eND_TIME) {
		END_TIME = eND_TIME;
	}

	public String getSURVEY_LINK() {
		return SURVEY_LINK;
	}

	public void setSURVEY_LINK(String sURVEY_LINK) {
		SURVEY_LINK = sURVEY_LINK;
	}

	/**
	 * Sort ConferenceModel alphabetically 
	 */
	@Override
	public int compareTo(ConferenceModel c) {
		// TODO Auto-generated method stub
		return this.name.compareTo(c.getName());
	}
	
	/**
	 * We compare the location and speakers to differentiate between each Conference
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ConferenceModel other = (ConferenceModel) obj;
		if (location == null) {
			if (other.location != null)
				return false;
		} else if (!location.equals(other.location))
			return false;
		if (speakers == null) {
			if (other.speakers != null)
				return false;
		} else if (!speakers.equals(other.speakers))
			return false;
		return true;
	}
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((location == null) ? 0 : location.hashCode());
		result = prime * result
				+ ((speakers == null) ? 0 : speakers.hashCode());
		return result;
	}

	// # region Parceling 	
	/**
	 * Constructing ConferenceModel for parceling 
	 * @param in the parcel 
	 */
	public ConferenceModel(Parcel in){
		String[] data = new String[8];
		
		in.readStringArray(data);
		this.name = data[0];
		this.biography = data[1];
		this.description = data[2];
		this.location = data[3];
		this.speakers = data[4];
		this.START_TIME = data[5];
		this.END_TIME = data[6];
		this.SURVEY_LINK = data[7];
	}
	
	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}

	/**
	 * Writing all fields to parcel 
	 */
	@Override
	public void writeToParcel(Parcel dest, int flags) {
		// TODO Auto-generated method stub
		dest.writeStringArray(new String[] {this.name,this.biography,this.description,
				this.location,this.speakers,this.START_TIME,this.END_TIME,this.SURVEY_LINK});
	}
	
	/**
	 * creating the parcel by calling the this.constructor for the parcel
	 */
	//http://stackoverflow.com/questions/7181526/example-of-implementing-parcelable
	public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
         public ConferenceModel createFromParcel(Parcel in) {
			return new ConferenceModel(in);
         }

         public ConferenceModel[] newArray(int size) {
             return new ConferenceModel[size];
         }
    };
	
}
