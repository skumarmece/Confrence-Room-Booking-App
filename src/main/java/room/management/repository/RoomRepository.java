package room.management.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import room.management.bean.Room;

public interface RoomRepository extends JpaRepository<Room, Long> {
}
