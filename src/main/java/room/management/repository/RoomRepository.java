package room.management.repository;

import org.springframework.data.repository.CrudRepository;

import room.management.bean.Room;

public interface RoomRepository extends CrudRepository<Room, Long> {
}
