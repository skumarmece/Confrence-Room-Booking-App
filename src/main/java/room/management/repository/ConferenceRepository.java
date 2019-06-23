package room.management.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import room.management.bean.Conference;

public interface ConferenceRepository extends JpaRepository<Conference, Long> {
}
