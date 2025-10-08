package com.kimy.course.repositories;

import com.kimy.course.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;

// JpaRepository<User, Long> indica que a interface UserRepository é um repositório JPA para a
// entidade User, cujo ID é do tipo Long
public interface CategoryRepository extends JpaRepository<Category, Long> {
}
