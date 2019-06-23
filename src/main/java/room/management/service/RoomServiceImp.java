package room.management.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import room.management.bean.Room;
import room.management.repository.RoomRepository;

@Service
@Transactional
public class RoomServiceImp implements RoomService {
	@Autowired
	RoomRepository roomRepository;

	public Room createRoom(Room room) {
		// TODO Auto-generated method stub
		return roomRepository.save(room);
	}

	public List<Room> getRoom() {
		// TODO Auto-generated method stub
		return (List<Room>) roomRepository.findAll();
	}

	public Room findById(long id) {
		// TODO Auto-generated method stub
		return roomRepository.findById(id).get();
	}

	public Room update(Room room, long l) {
		// TODO Auto-generated method stub
		return roomRepository.save(room);
	}

	public void deleteRoomById(long id) {
		// TODO Auto-generated method stub
		roomRepository.deleteById(id);
	}

	public Room updatePartially(Room room, long id) {
		// TODO Auto-generated method stub
		Room usr = findById(id);
		return roomRepository.save(usr);
	}

}
