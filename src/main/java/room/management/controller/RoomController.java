package room.management.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.websocket.server.PathParam;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import room.management.bean.Facility;
import room.management.bean.Room;
import room.management.repository.CategoryRepository;
import room.management.repository.FacilityRepository;
import room.management.repository.RoomRepository;

@RestController
@RequestMapping(value = { "/api/v1/rooms" })
public class RoomController {

	Logger logger = LoggerFactory.getLogger(RoomController.class);

	@Autowired
	RoomRepository roomRepository;

	@Autowired
	CategoryRepository categoryRepository;

	@Autowired
	FacilityRepository facilityRepository;

	@PreAuthorize("hasAnyRole('ADMIN')")
	@ResponseBody
	@GetMapping(value = "")
	public ResponseEntity<List<Room>> getAllItems() {
		return new ResponseEntity<List<Room>>(roomRepository.findAll(), HttpStatus.OK);
	}

	@PreAuthorize("hasAnyRole('ADMIN')")
	@PostMapping(value = "", headers = "Accept=application/json")
	public ResponseEntity<Room> addRoom(@RequestBody Room room) {
		logger.error(room.toString());
		room.setCategory(categoryRepository.findById(room.getCategory().getId()).get());
		room = roomRepository.saveAndFlush(room);
		Set<Facility> facilities = new HashSet();
//		for (Facility facility : room.getFacilities()) {
//			facilities.add(facilityRepository.findById(facility.getId()).get());
//		}
//		room.setFacilities(facilities);
		logger.error(room.toString());
		return new ResponseEntity<Room>(room, HttpStatus.OK);
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
