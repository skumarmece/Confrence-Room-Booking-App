package room.management.service;

import java.util.Date;
import java.util.List;

import room.management.bean.Conference;
import room.management.exception.RoomBookingException;

public interface ConferenceService {
	
	public Conference createConference(Conference conference) throws RoomBookingException;

	public List<Conference> getConference();

	public Conference findById(long id) throws RoomBookingException;

	public Conference update(Conference conference, long l) throws RoomBookingException;

	public void deleteConferenceById(long id) throws RoomBookingException;

	public Conference updatePartially(Conference conference, long id) throws RoomBookingException;
	
	public List<Conference> getConferenceByRoomId(long roomId) throws RoomBookingException;
	
	public List<Conference> getConferenceByRoomId(long roomId, long conferenceId) throws RoomBookingException;
}
