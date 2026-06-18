package org.example.aleasofttrainner;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StudentRepositry extends JpaRepository<Student, Long>{

    Optional<Student> getStudentByName(String name);

    Optional<StudentDto> deleteStudentByName(String name);
}
