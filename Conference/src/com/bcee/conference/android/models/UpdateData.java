package com.bcee.conference.android.models;

public interface UpdateData {
	
	/**
	 * Checks if update is needed
	 * @param lastUpdated the time when data is last updated
	 */
	public boolean needUpdate(String lastUpdated);
		
	
}

