package room.management.room.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import room.management.room.bean.User;

import java.util.Optional;

public interface UsersRepository extends JpaRepository<User, Integer> {
    Optional<User> findByFirstName(String firstname);
}
