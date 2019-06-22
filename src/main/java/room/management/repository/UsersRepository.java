package room.management.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import room.management.bean.User;

import java.util.Optional;

public interface UsersRepository extends JpaRepository<User, Integer> {
    Optional<User> findByFirstName(String firstname);
}
