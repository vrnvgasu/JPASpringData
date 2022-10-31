package ru.edu.dao.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.edu.dao.entity.StudentEntity;

public interface StudentRepository extends JpaRepository<StudentEntity, String> {

}
