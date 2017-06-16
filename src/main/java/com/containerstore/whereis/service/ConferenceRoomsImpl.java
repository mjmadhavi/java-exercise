package com.containerstore.whereis.service;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import com.containerstore.common.util.Format;

/**
 * @author mjmad
 *
 */
public class ConferenceRoomsImpl implements ConferenceRooms {
	protected static final Map<String, Integer> roomsMap = new LinkedHashMap<String, Integer>();
    protected static final Map<Integer,String> locationMap = new HashMap<Integer,String>();
	
	static {
		locationMap.put(1, "in the vendor conference area (off of reception)");
		locationMap.put(2, "in the Information Systems area");
		locationMap.put(3, "at the north end of the Information Systems area");
		locationMap.put(4, "off the atrium, behind reception");
		locationMap.put(5, "where gumby has always been located...c'mon!");
		locationMap.put(6, "upstairs, south end, adjacent to CSD");
		locationMap.put(7, "upstairs, south end, adjacent to CSD (seating area in front of Contain Yourself)");
		locationMap.put(8, "upstairs, southwest corner");
		locationMap.put(9, "upstairs, southwest corner, adjacent to loss prevention");
		locationMap.put(10, "upstairs, northwest corner, adjacent to the executive suite");
		locationMap.put(11, "up the stairs, turn right (adjacent to merchandising)");
	}
	static {
		roomsMap.put("fill their baskets", 1);
		roomsMap.put("service selection price", 1);
		roomsMap.put("man in the desert", 1);
		roomsMap.put("air of excitement", 1);
		roomsMap.put("data central", 2);
		roomsMap.put("perfect product presentation", 2);
		roomsMap.put("main and main", 3);
		roomsMap.put("1 great = 3 good", 4);
		roomsMap.put("intuition does not come to an unprepared mind", 4);
		roomsMap.put("gumby", 5);
		roomsMap.put("contain yourself", 6);
		roomsMap.put("we love our employees", 7);
		roomsMap.put("all eyes on elfa", 8);
		roomsMap.put("service = selling", 9);
		roomsMap.put("fun and functional", 9);
		roomsMap.put("communication is leadership", 10);
		roomsMap.put("we sell the hard stuff", 11);
		roomsMap.put("blue sky", 11);
		// location = "somewhere, but I don't know where";
	}

	private String oldlocationOf(String query) {
		String location;

		switch (query.trim().toLowerCase()) {
		case "fill their baskets":
		case "service selection price":
		case "man in the desert":
		case "air of excitement":
			location = "in the vendor conference area (off of reception)";
			break;
		case "data central":
			location = "in the Information Systems area";
			break;
		case "perfect product presentation":
		case "main and main":
			location = "at the north end of the Information Systems area";
			break;
		case "1 great = 3 good":
		case "intuition does not come to an unprepared mind":
			location = "off the atrium, behind reception";
			break;
		case "gumby":
			location = "where gumby has always been located...c'mon!";
			break;
		case "contain yourself":
			location = "upstairs, south end, adjacent to CSD";
			break;
		case "we love our employees":
			location = "upstairs, south end, adjacent to CSD (seating area in front of Contain Yourself)";
			break;
		case "all eyes on elfa":
			location = "upstairs, southwest corner";
			break;
		case "service = selling":
		case "fun and functional":
			location = "upstairs, southwest corner, adjacent to loss prevention";
			break;
		case "communication is leadership":
			location = "upstairs, northwest corner, adjacent to the executive suite";
			break;
		case "we sell the hard stuff":
		case "blue sky":
			location = "up the stairs, turn right (adjacent to merchandising)";
			break;
		default:
			location = "somewhere, but I don't know where";
			break;
		}

		return String.format("%s is located %s", query, location);
	}


	/**
	 * @param query
	 * @return location of the room 
	 */
	public String locationOf(String query) {
		String location = null;
		if ((query != null) && !(query.trim()).equals("")) {
			query = (query.trim()).toLowerCase();
			Integer locationCode = (Integer) roomsMap.get(query.trim());
			location = (String) locationMap.get(locationCode);
		}
		return location;
	}

	/**
	 * @return Array of Strings with All Rooms
	 */
	public String[] getRoomsList() {
		String[] roomsList = roomsMap.keySet().toArray(new String[roomsMap.size()]);
		return roomsList;
	}

	/**
	 * @return Map of Rooms and their locations
	 */
	public Map<String, String> getRoomLocationList() {
		Map<String, String> roomsLocation = new LinkedHashMap<String, String>();
		roomsMap.forEach((room, locationCode) -> {
			String location = (String) locationMap.get(locationCode);
			roomsLocation.put(Format.titleCase(room), location);
		});
		return roomsLocation;

	}

	/**
	 * @param query
	 * @return comma separated rooms string
	 */
	public String getRooms(String query) {
		StringBuilder roomsList = new StringBuilder("");
		// String test = String.join(","+newHTMLLine,roomsMap.keySet());
		roomsMap.keySet().forEach((key) -> roomsList.append(Format.titleCase(key) + "," + newHTMLLine));
		return roomsList.toString();
	}
}
