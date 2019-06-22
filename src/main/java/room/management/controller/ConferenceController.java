package room.management.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import room.management.bean.Conference;
import room.management.service.ConferenceService;

@RestController
@RequestMapping(value = { "/conference" })
public class ConferenceController {
	@Autowired
	ConferenceService conferenceService;

	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Conference> getConferenceById(@PathVariable("id") long id) {
		System.out.println("Fetching Conference with id " + id);
		Conference conference = conferenceService.findById(id);
		if (conference == null) {
			return new ResponseEntity<Conference>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Conference>(conference, HttpStatus.OK);
	}

	@PostMapping(value = "/create", headers = "Accept=application/json")
	public ResponseEntity<Void> createConference(@RequestBody Conference conference, UriComponentsBuilder ucBuilder) {
		System.out.println("Creating Conference " + conference.getName());
		conferenceService.createConference(conference);
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/conference/{id}").buildAndExpand(conference.getId()).toUri());
		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}

	@GetMapping(value = "/get", headers = "Accept=application/json")
	public List<Conference> getAllConference() {
		List<Conference> tasks = conferenceService.getConference();
		return tasks;

	}

	@PutMapping(value = "/update", headers = "Accept=application/json")
	public ResponseEntity<String> updateConference(@RequestBody Conference currentConference) {
		System.out.println("sd");
		Conference conference = conferenceService.findById(currentConference.getId());
		if (conference == null) {
			return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
		}
		conferenceService.update(currentConference, currentConference.getId());
		return new ResponseEntity<String>(HttpStatus.OK);
	}

	@DeleteMapping(value = "/{id}", headers = "Accept=application/json")
	public ResponseEntity<Conference> deleteConference(@PathVariable("id") long id) {
		Conference conference = conferenceService.findById(id);
		if (conference == null) {
			return new ResponseEntity<Conference>(HttpStatus.NOT_FOUND);
		}
		conferenceService.deleteConferenceById(id);
		return new ResponseEntity<Conference>(HttpStatus.NO_CONTENT);
	}

	@PatchMapping(value = "/{id}", headers = "Accept=application/json")
	public ResponseEntity<Conference> updateConferencePartially(@PathVariable("id") long id,
			@RequestBody Conference currentConference) {
		Conference conference = conferenceService.findById(id);
		if (conference == null) {
			return new ResponseEntity<Conference>(HttpStatus.NOT_FOUND);
		}
		conference = conferenceService.updatePartially(currentConference, id);
		return new ResponseEntity<Conference>(conference, HttpStatus.OK);
	}
}
