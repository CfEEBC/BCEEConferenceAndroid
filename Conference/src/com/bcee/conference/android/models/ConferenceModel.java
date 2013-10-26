package com.bcee.conference.android.models;

public class ConferenceModel implements Comparable<ConferenceModel>{

	private String name;
	private String description;
	private String location;
	private String speakers;
	private String biography;
	private String START_TIME;
	private String END_TIME;
	private String SURVEY_LINK;
	
	/**
	 * We compare the location and speakers to differentiate between each Conference
	 */
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

	@Override
	public int compareTo(ConferenceModel c) {
		// TODO Auto-generated method stub
		return this.name.compareTo(c.getName());
	}
	
}
