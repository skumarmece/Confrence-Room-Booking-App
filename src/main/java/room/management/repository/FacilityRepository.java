package room.management.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import room.management.bean.Facility;

public interface FacilityRepository extends JpaRepository<Facility, Long> {
}
