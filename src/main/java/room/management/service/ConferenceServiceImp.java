package room.management.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import room.management.bean.Conference;
import room.management.bean.Room;
import room.management.exception.RoomBookingException;
import room.management.repository.ConferenceRepository;
import room.management.service.utils.ServiceUtils;

@Service
@Transactional
public class ConferenceServiceImp implements ConferenceService {
	@Autowired
	ConferenceRepository conferenceRepository;
	
	@Autowired
	ServiceUtils serviceUtils;

	public Conference createConference(Conference conference) throws RoomBookingException {
		return createOrUpdateConference(conference, 0);
	}
	
	public Conference createOrUpdateConference(Conference conference, long id) throws RoomBookingException {
		Conference resultData = null;
		if(conference != null) {
			Room room = conference.getRoom();
			if(room != null) {
				Room existingRoom = serviceUtils.getRoomById(room.getId());
				if(existingRoom != null) {
					if(!serviceUtils.hasConflict(room.getId(), conference, id)) {
						resultData = saveConferenceObj(conference);		
					} else {
						serviceUtils.throwConflict("Conflicts occurs.");
					}
				} else {
					serviceUtils.throwBadRequest("Invalid room details.");
				}
			} else {
				serviceUtils.throwNotFound("Room detail found");
			}
		} else {
			serviceUtils.throwBadRequest("Invalid input data");
		}
		return resultData;
	}
	
	@Override
	public List<Conference> getConferenceByRoomId(long roomId) throws RoomBookingException {
		return (List<Conference>) conferenceRepository.findByRoomId(roomId);
	}

	@Override
	public List<Conference> getConferenceByRoomId(long roomId, long conferenceId) throws RoomBookingException {
		return (List<Conference>) conferenceRepository.findConferenceByRoomId(roomId, conferenceId);
	}
	
	public List<Conference> getConference() {
		return (List<Conference>) conferenceRepository.findAll();
	}

	public Conference findById(long id) throws RoomBookingException {
		return conferenceRepository.findById(id).get();
	}

	public Conference update(Conference conference, long l) throws RoomBookingException {
		Conference resultData = null;
		Conference existingConference = findById(l);
		if(existingConference == null) {
			serviceUtils.throwNotFound("Conference is not found.");
		} else {
			if(conference != null) {
				serviceUtils.updateExistingConference(existingConference, conference);
				resultData = createOrUpdateConference(existingConference, existingConference.getId());
			} else {
				serviceUtils.throwBadRequest("Invalid input data");
			}
		}
		return resultData;
	}
	
	private Conference saveConferenceObj(Conference conference) {
		return conferenceRepository.save(conference);
	}
	
	public void deleteConferenceById(long id) throws RoomBookingException {
		Conference conference = findById(id);
		if(conference != null) {
			conferenceRepository.delete(conference);
		}
	}

	public Conference updatePartially(Conference conference, long id) throws RoomBookingException {
		// TODO Auto-generated method stub
		Conference usr = findById(id);
		return conferenceRepository.save(usr);
	}

}
