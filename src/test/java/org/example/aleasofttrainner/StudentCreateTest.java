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
    void createStudent() {
    Student student =new Student();
    student.setId(1L);
    student.setName("John");
    student.setCountry("Pakistan");
    student.setPhone("9720595080386");

    when(studentRepositry.findById(1L)).thenReturn(Optional.of(student));
    StudentDto result = studentCreate.getStudentDto(1L);

    assertThat(result.getName()).isEqualTo("John");
    assertThat(result.getPhone()).isEqualTo("9720595080386");
    verify(studentRepositry).findById(1L);
}
    }
