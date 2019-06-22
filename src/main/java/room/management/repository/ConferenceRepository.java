package room.management.repository;

import org.springframework.data.repository.CrudRepository;

import room.management.bean.Conference;

public interface ConferenceRepository extends CrudRepository<Conference, Long> {
}
