package room.management;

import java.util.HashSet;
import java.util.Set;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import room.management.bean.Role;
import room.management.bean.Room;
import room.management.bean.User;
import room.management.repository.RoomRepository;
import room.management.repository.UsersRepository;

@SpringBootApplication
public class ConferenceRoomBookingApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConferenceRoomBookingApplication.class, args);
	}

	@Bean
	public CommandLineRunner demoData(RoomRepository roomRepository, UsersRepository usersRepository) {
		// useful link to name the Rooms
		// https://www.britannica.com/topic-browse/Animals/Mammals/Feline-Family/1
		return args -> {

			if (usersRepository.count() < 1) {
				User defaultUser = new User();
				defaultUser.setId(1);
				defaultUser.setFirstName("admin");
				defaultUser.setEmail("admin@confrencebooking.com");
				defaultUser.setPassword("admin123");
				Role role = new Role();
				role.setId(1);
				role.setRoleName("ADMIN");
				Set roles = new HashSet<Role>();
				roles.add(role);
				defaultUser.setRoles(roles);
				usersRepository.save(defaultUser);
//				defaultUser.set
			}

			if (false && roomRepository.count() < 1) {
				Room room1 = new Room();
				room1.setName("");
				roomRepository.save(new Room());
			}
		};
	}

//	private static Room createRoomInstance() {
//
//	}
//
//	private static Facility getRoomFacility() {
//
//	}

}
