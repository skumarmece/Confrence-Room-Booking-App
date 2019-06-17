package room.management.room.repository;

import org.springframework.data.repository.CrudRepository;

import room.management.room.bean.Conference;

public interface ConferenceRepository extends CrudRepository<Conference, Long> {
}
