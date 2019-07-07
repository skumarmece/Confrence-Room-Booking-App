package room.management.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import room.management.bean.Category;
import room.management.bean.Facility;
import room.management.repository.CategoryRepository;
import room.management.repository.FacilityRepository;

@RestController
@RequestMapping(value = { "/api/v1" })
public class CommonRESTController {

	@Autowired
	CommonRESTController itemService;

	@Autowired
	CategoryRepository categoryRepository;
	
	@Autowired
	FacilityRepository facilityRepository;

	@PreAuthorize("hasAnyRole('ADMIN')")
	@RequestMapping("/categories")
	@ResponseBody
	public ResponseEntity<List<Category>> getAllCategories() {
		return new ResponseEntity<List<Category>>(categoryRepository.findAll(), HttpStatus.OK);
	}
	
	@PreAuthorize("hasAnyRole('ADMIN')")
	@RequestMapping("/facilities")
	@ResponseBody
	public ResponseEntity<List<Facility>> getAllIFacilities() {
		return new ResponseEntity<List<Facility>>(facilityRepository.findAll(), HttpStatus.OK);
	}
	
	

	@RequestMapping("/hello")
	public String sayHello() {
		return "Hello User!";
	}

}
