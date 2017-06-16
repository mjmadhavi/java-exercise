package com.containerstore.whereis.service;

import java.util.Map;

public interface ConferenceRooms {
	/**
	 * @param query
	 * @return location of the room 
	 */
	public  String locationOf(String query);
	
	/**
	 * @return Array of Strings with All Rooms
	 */
	public  String[] getRoomsList();
	
	/**
	 * @param query
	 * @return Map of Rooms and their locations
	 */
	public Map<String, String> getRoomLocationList();
	
    static final String roomsListReq = "list conference rooms";
    static final String newHTMLLine = "<br/>";
    
    
}
