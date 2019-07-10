package room.management.service.utils;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import room.management.bean.Conference;
import room.management.bean.Room;
import room.management.exception.RoomBookingException;
import room.management.service.ConferenceService;
import room.management.service.RoomService;

@Component
public class ServiceUtils {

	Logger logger = LoggerFactory.getLogger(ServiceUtils.class);
	
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

	public void throwErrorMessage(HttpStatus status, String message) throws RoomBookingException {
		throw new RoomBookingException(status, message);
	}

	public void throwBadRequest(String message) throws RoomBookingException {
		throw new RoomBookingException(HttpStatus.BAD_REQUEST, message);
	}

	public void throwNotFound(String message) throws RoomBookingException {
		throw new RoomBookingException(HttpStatus.NOT_FOUND, message);
	}

	public void throwNoContent(String message) throws RoomBookingException {
		throw new RoomBookingException(HttpStatus.NO_CONTENT, message);
	}

	public void throwConflict(String message) throws RoomBookingException {
		throw new RoomBookingException(HttpStatus.CONFLICT, message);
	}

	public boolean hasConflict(long roomId, Conference conference, long conferenceId) {
		boolean bookingConflict = false;

		try {
			// TODO: fetch the conference records based on the time start and endtime
			List<Conference> bookedConferenceList = conferenceService.getConferenceByRoomId(roomId, conferenceId);
			long newStartingTime = conference.getStartTime().toInstant().toEpochMilli();
			long newEndingTime = conference.getEndTime().toInstant().toEpochMilli();

			if (bookedConferenceList != null && !bookedConferenceList.isEmpty()) {
				for (Conference bookedConference : bookedConferenceList) {
					long scheduledStartTime = bookedConference.getStartTime().toInstant().toEpochMilli();
					long scheduledEndTime = bookedConference.getEndTime().toInstant().toEpochMilli();
					// Starting at same time or ending at same time
					if ((scheduledStartTime == newStartingTime || scheduledEndTime == newEndingTime)
							// new start-time is between scheduled times
							|| (scheduledStartTime < newStartingTime && newStartingTime < scheduledEndTime)
							// new end-time is between schedule time
							|| (scheduledStartTime < newEndingTime && newEndingTime < scheduledEndTime)
							// already scheduled slot is in-between new one
							|| (newStartingTime < scheduledStartTime && scheduledEndTime < newEndingTime)) {
						logger.debug(bookedConference.toString());
						logger.debug(conference.toString());
						bookingConflict = true;
						break;
					}
				}
			}
		} catch (RoomBookingException e) {
			System.out.println("Exception happened " + e);
		}

		return bookingConflict;
	}

	public void updateExistingConference(Conference existingConference, Conference conference) {
		if (existingConference != null && conference != null) {
			existingConference.setDescription(conference.getDescription());
			//existingConference.setUser(conference.getUser());
			existingConference.setRoom(conference.getRoom());
			existingConference.setName(conference.getName());
			existingConference.setStartTime(conference.getStartTime());
			existingConference.setEndTime(conference.getEndTime());
		}
	}

}
