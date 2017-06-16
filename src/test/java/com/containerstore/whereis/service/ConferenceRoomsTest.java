package com.containerstore.whereis.service;

import static org.junit.Assert.*;

import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ConferenceRoomsTest {
	ConferenceRooms conferenceRooms;
	@Before
	public void setUp() throws Exception {
		conferenceRooms = new ConferenceRoomsImpl();
	}

	@After
	public void tearDown() throws Exception {
		conferenceRooms = null;
	}

	@Test
	public void testLocationOf() {
		String expected = "in the vendor conference area (off of reception)";
		String location = conferenceRooms.locationOf("Air Of Excitement");
		assertEquals(expected, location);
		
		location = conferenceRooms.locationOf("test");
		assertEquals(null, location);	
	}

	@Test
	public void testGetRoomsList() {
		String[] rooms = conferenceRooms.getRoomsList();
		assertEquals(18, rooms.length);
		
	}

	@Test
	public void testGetRoomLocationList() {
		Map<String, String> roomsMap = conferenceRooms.getRoomLocationList();
		assertEquals(18, roomsMap.size());
	}

}
