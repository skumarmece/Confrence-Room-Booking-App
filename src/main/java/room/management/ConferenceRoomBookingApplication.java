package room.management;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import room.management.bean.Category;
import room.management.bean.Conference;
import room.management.bean.Facility;
import room.management.bean.Role;
import room.management.bean.Room;
import room.management.bean.User;
import room.management.repository.CategoryRepository;
import room.management.repository.ConferenceRepository;
import room.management.repository.FacilityRepository;
import room.management.repository.RoomRepository;
import room.management.repository.UsersRepository;

@SpringBootApplication
public class ConferenceRoomBookingApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConferenceRoomBookingApplication.class, args);
	}

	@Bean
	public CommandLineRunner demoData(RoomRepository roomRepository, UsersRepository usersRepository,
			CategoryRepository categoryRepository, FacilityRepository facilityRepository,
			ConferenceRepository conferenceRepository) {
		// useful link to name the Rooms
		// https://www.britannica.com/topic-browse/Animals/Mammals/Feline-Family/1
		return args -> {

			if (categoryRepository.count() < 1) {

				List<Category> categories = new ArrayList<Category>();
				Collections.addAll(categories,
						new Category(10, "Mini U-Shape Style", "This style iѕ ideal for a grоuр оf under 10 people."),
						new Category(4, "Small Sԛuаrе Style", "This style iѕ ideal for a grоuр оf under 4 people."),
						new Category(20, "Banquet Style",
								"Depending on the room’s purpose, these tables will either "
										+ "have standing places or up to 8 chairs placed around them."),
						new Category(20, "Cabaret Style", "This style iѕ ideal for a grоuр оf under 20 people."),
						new Category(20, "Classroom Style",
								"The audience is still sitting in rows and facing one main central speaker, but they now have tables in front of them to allow for note-taking and other tasks."),
						new Category(50, "Auditorium Stуlе",
								"This theater ѕtуlе iѕ apt fоr one-way knowledge sharing or audio-visual рrеѕеntаtiоns."),
						new Category(22, "U-Shape Style",
								"This layout style can comfortably accommodate up to 22 people."),
						new Category(15, "Hоllоw Sԛuаrе Style", "This style iѕ ideal for a grоuр оf under 15 people."),
						new Category(25, "Bоаrdrооm Style",
								"The bоаrdrооm meeting room style саn typically ассоmmоdаtе up to 25 people."));
				categoryRepository.saveAll(categories);
			}
			if (facilityRepository.count() < 1) {

				Facility facility = new Facility();
				facility.setFacilityName("Notepads and pens");

				Facility facility1 = new Facility();
				facility1.setFacilityName("Computer, projector and screen");

				Facility facility2 = new Facility();
				facility2.setFacilityName("Free Wi-Fi access");

				Facility facility3 = new Facility();
				facility3.setFacilityName("Adjustable lighting");

				Facility facility4 = new Facility();
				facility4.setFacilityName("Computer, projector and screen");

				Facility facility5 = new Facility();
				facility5.setFacilityName("Ceiling-mounted video projector");

				Facility facility6 = new Facility();
				facility6.setFacilityName("HDMI connectivity, ");

				List<Facility> facilities = new ArrayList<Facility>();
				Collections.addAll(facilities, facility, facility1, facility2, facility3, facility4, facility5,
						facility6);
				facilityRepository.saveAll(facilities);

			}

			if (usersRepository.count() < 1) {
				User defaultUser = new User();
//				defaultUser.setId(1);
				defaultUser.setFirstName("admin");
				defaultUser.setEmail("admin@confrencebooking.com");
				defaultUser.setPassword("admin123");
				Role role = new Role();
//				role.setId(1);
				role.setRoleName("ADMIN");
				Set<Role> roles = new HashSet<Role>();
				roles.add(role);
				defaultUser.setRoles(roles);
				usersRepository.save(defaultUser);
//				defaultUser.set
			}

			if (roomRepository.count() < 1) {

				Room room1 = new Room();
				room1.setName("WildCat");
				room1.setCategory(categoryRepository.findById(1L).get());
				Set<Facility> facilities = new HashSet<Facility>();
				facilities.add(facilityRepository.findById(10l).get());
				facilities.add(facilityRepository.findById(11l).get());
				// room1.setFacilities(facilities);
				roomRepository.save(room1);
			}
			
			if(conferenceRepository.count() < 1) {
				Conference conference = new Conference();
				conference.setUser(usersRepository.findByFirstName("admin").get());
				conference.setName("Scrum");
				conference.setDescription("Daily standup meeting");
				Long milliSec = System.currentTimeMillis();
				conference.setStartTime(new Date(milliSec));
				conference.setEndTime(new Date(milliSec+30000));
				conference.setRoom(roomRepository.findById(19l).get());
				conferenceRepository.save(conference);
			}
		};
	}

}
