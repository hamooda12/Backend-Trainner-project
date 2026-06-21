package org.example.aleasofttrainner;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
@ActiveProfiles("test")
@DataJpaTest
class StudentRepositoryIT {

    @Autowired
    StudentRepositry studentRepositry;

    @Test
    void saveStudent_shouldSaveStudentInDatabase() {
        Student student = new Student();

        student.setEmail("ali@test.com");

        Student saved = studentRepositry.save(student);

        Optional<Student> found = studentRepositry.findById(saved.getId());

        assertThat(found).isPresent();
        assertThat(found.get().getName()).isEqualTo("Ali");
        assertThat(found.get().getEmail()).isEqualTo("ali@test.com");
    }
}