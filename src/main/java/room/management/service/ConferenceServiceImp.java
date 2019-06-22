package room.management.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import room.management.bean.Conference;
import room.management.repository.ConferenceRepository;

@Service
@Transactional
public class ConferenceServiceImp implements ConferenceService {
	@Autowired
	ConferenceRepository conferenceRepository;

	public Conference createConference(Conference conference) {
		// TODO Auto-generated method stub
		return conferenceRepository.save(conference);
	}

	public List<Conference> getConference() {
		// TODO Auto-generated method stub
		return (List<Conference>) conferenceRepository.findAll();
	}

	public Conference findById(long id) {
		// TODO Auto-generated method stub
		return conferenceRepository.findById(id).get();
	}

	public Conference update(Conference conference, long l) {
		// TODO Auto-generated method stub
		return conferenceRepository.save(conference);
	}

	public void deleteConferenceById(long id) {
		// TODO Auto-generated method stub
		conferenceRepository.deleteById(id);
	}

	public Conference updatePartially(Conference conference, long id) {
		// TODO Auto-generated method stub
		Conference usr = findById(id);
		return conferenceRepository.save(usr);
	}

}
