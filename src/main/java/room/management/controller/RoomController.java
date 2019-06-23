package room.management.controller;

import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import room.management.bean.Room;
import room.management.repository.RoomRepository;

@RestController
@RequestMapping(value = { "/v1/rooms" })
public class RoomController {

	@Autowired
	RoomController itemService;

	@Autowired
	RoomRepository roomRepository;

	@PreAuthorize("hasAnyRole('ADMIN')")
	@RequestMapping("")
	@ResponseBody
	public ResponseEntity<List<Room>> getAllItems() {
		return new ResponseEntity<List<Room>>(roomRepository.findAll(), HttpStatus.OK);
	}

	@PreAuthorize("hasAnyRole('ADMIN')")
	@PostMapping(value = "", headers = "Accept=application/json")
	public ResponseEntity<Room> addRoom(@RequestBody Room room) {

		return new ResponseEntity<Room>(roomRepository.save(room), HttpStatus.OK);
	}

	@PreAuthorize("hasAnyRole('ADMIN')")
	@PutMapping(value = "/{id}", headers = "Accept=application/json")
	public ResponseEntity<Room> updateRoom(@RequestBody Room room, @PathParam(value = "id") long id) {
		Room dbVal = roomRepository.findById(id).get();
		if (dbVal != null) {
			return new ResponseEntity<Room>(roomRepository.save(dbVal), HttpStatus.OK);
		}
		return new ResponseEntity<Room>(HttpStatus.BAD_REQUEST);
	}

	@PreAuthorize("hasAnyRole('ADMIN')")
	@DeleteMapping(value = "/{id}", headers = "Accept=application/json")
	public ResponseEntity deleteRoom(@PathParam(value = "id") long id) {
		Room dbVal = roomRepository.findById(id).get();
		if (dbVal != null) {
			roomRepository.deleteById(id);
			return new ResponseEntity(HttpStatus.OK);
		}
		return new ResponseEntity(HttpStatus.BAD_REQUEST);
	}
}
