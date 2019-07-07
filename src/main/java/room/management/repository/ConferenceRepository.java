package room.management.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import room.management.bean.Conference;

public interface ConferenceRepository extends JpaRepository<Conference, Long> {
	public List<Conference> findByRoomId(long roomId);
	public List<Conference> findConferenceByRoomId(long roomId, long conferenceId);
}
