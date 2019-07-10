package room.management.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import room.management.bean.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
}
