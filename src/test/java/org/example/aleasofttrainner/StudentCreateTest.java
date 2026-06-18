package org.example.aleasofttrainner;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class StudentCreateTest {

    @Mock
    StudentRepositry studentRepositry;

    @InjectMocks
    StudentCreate studentCreate;

    @Test
    void getStudentDto_shouldReturnStudentDto_whenStudentExists() {
        Student student = new Student();
        student.setId(1L);
        student.setName("Ahmad");
        student.setEmail("ahmad@test.com");

        when(studentRepositry.findById(1L)).thenReturn(Optional.of(student));

        StudentDto result = studentCreate.getStudentDto(1L);

        assertThat(result.getName()).isEqualTo("Ahmad");
        assertThat(result.getEmail()).isEqualTo("ahmad@test.com");

        verify(studentRepositry).findById(1L);
    }
}