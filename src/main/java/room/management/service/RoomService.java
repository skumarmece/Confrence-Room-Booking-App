package room.management.service;

import java.util.List;

import room.management.bean.Room;

public interface RoomService {
	
	public Room createRoom(Room room);

	public List<Room> getRoom();

	public Room findById(long id);

	public Room update(Room room, long l);

	public void deleteRoomById(long id);

	public Room updatePartially(Room room, long id);
}
