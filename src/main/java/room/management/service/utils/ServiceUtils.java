package room.management.service.utils;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import room.management.bean.Conference;
import room.management.bean.Room;
import room.management.exception.RestExceptionHandler;
import room.management.service.ConferenceService;
import room.management.service.RoomService;

@Component
public class ServiceUtils {
	
	@Autowired
	RoomService roomService;
	
	@Autowired
	ConferenceService conferenceService;
	
	public Room getRoomById(long id) {
		return roomService.findById(id);
	}
	
	public List<Room> getAllRooms() {
		return roomService.getRoom();
	}

	public void throwErrorMessage(HttpStatus status, String message) throws RestExceptionHandler {
		throw new RestExceptionHandler(status, message);
	}
	
	public void throwBadRequest(String message) throws RestExceptionHandler {
		throw new RestExceptionHandler(HttpStatus.BAD_REQUEST, message);
	}
	
	public void throwNotFound(String message) throws RestExceptionHandler {
		throw new RestExceptionHandler(HttpStatus.NOT_FOUND, message);
	}
	
	public void throwNoContent(String message) throws RestExceptionHandler {
		throw new RestExceptionHandler(HttpStatus.NO_CONTENT, message);
	}
	
	public void throwConflict(String message) throws RestExceptionHandler {
		throw new RestExceptionHandler(HttpStatus.CONFLICT, message);
	}
	
	public boolean hasConflict(long roomId, Conference conference, long conferenceId) {
		boolean bookingConflict = false;
		
		try {
			//todo: fetch the conference records based on the time start and endtime
			List<Conference> bookedConferenceList = conferenceService.getConferenceByRoomId(roomId, conferenceId);
			
			long startingTime = conference.getStartTime().toInstant().toEpochMilli();
			long endingTime = conference.getEndTime().toInstant().toEpochMilli();
			
			if(bookedConferenceList != null && !bookedConferenceList.isEmpty()) {
				for(Conference bookedConference : bookedConferenceList) {
					long startTime = bookedConference.getStartTime().toInstant().toEpochMilli();
					long endTime = bookedConference.getEndTime().toInstant().toEpochMilli();
					if((startTime < startingTime && endTime > startingTime) || (startTime < endingTime) ) {
						bookingConflict = true;
						break;
					} else if(startTime == startingTime && endTime == endingTime) {
						bookingConflict = true;
						break;
					}
				}
			}
		} catch (RestExceptionHandler e) {
			System.out.println("Exception happened " + e);
		}
		
		return bookingConflict;
	}
	
	public void updateExistingConference(Conference existingConference, Conference conference) {
		if(existingConference != null && conference != null) {
			existingConference.setDescription(conference.getDescription());
			existingConference.setUser(conference.getUser());
			existingConference.setRoom(conference.getRoom());
			existingConference.setName(conference.getName());
			existingConference.setStartTime(conference.getStartTime());
			existingConference.setEndTime(conference.getEndTime());
		}
	}

}
