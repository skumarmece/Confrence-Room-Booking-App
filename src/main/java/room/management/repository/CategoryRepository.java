package room.management.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import room.management.bean.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
