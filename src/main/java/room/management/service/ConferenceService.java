package room.management.service;

import java.util.Date;
import java.util.List;

import room.management.bean.Conference;
import room.management.exception.RestExceptionHandler;

public interface ConferenceService {
	
	public Conference createConference(Conference conference) throws RestExceptionHandler;

	public List<Conference> getConference();

	public Conference findById(long id) throws RestExceptionHandler;

	public Conference update(Conference conference, long l) throws RestExceptionHandler;

	public void deleteConferenceById(long id) throws RestExceptionHandler;

	public Conference updatePartially(Conference conference, long id) throws RestExceptionHandler;
	
	public List<Conference> getConferenceByRoomId(long roomId) throws RestExceptionHandler;
	
	public List<Conference> getConferenceByRoomId(long roomId, long conferenceId) throws RestExceptionHandler;
}
