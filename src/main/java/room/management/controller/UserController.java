package room.management.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import room.management.bean.User;
import room.management.repository.UsersRepository;

@RestController
@RequestMapping(value = { "/api/v1/users" })
public class UserController {

	@Autowired
	private UsersRepository usersRepository;

	@PreAuthorize("hasAnyRole('ADMIN')")
	@RequestMapping("")
	@ResponseBody
	public ResponseEntity<List<User>> getAllUsers() {
		return new ResponseEntity<List<User>>(usersRepository.findAll(), HttpStatus.OK);
	}

	@GetMapping(value = "/{name}", headers = "Accept=application/json")
	public ResponseEntity<User> getUserByName(@RequestParam  (value = "name") String name) {
		User dbVal = usersRepository.findByFirstName(name).get();
		return new ResponseEntity<User>(dbVal, HttpStatus.OK);
	}


}
