package room.management.service;

import java.util.List;

import room.management.bean.Conference;

public interface ConferenceService {
	
	public Conference createConference(Conference conference);

	public List<Conference> getConference();

	public Conference findById(long id);

	public Conference update(Conference conference, long l);

	public void deleteConferenceById(long id);

	public Conference updatePartially(Conference conference, long id);
}
