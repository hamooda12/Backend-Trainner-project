package org.example.aleasofttrainner;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class StudentCreate {

    final StudentRepositry studentRepositry;

    public StudentCreate(StudentRepositry studentRepositry) {
        this.studentRepositry = studentRepositry;
    }

    public StudentDto getStudentDto(Long id) {
        Student student = studentRepositry.findById(id)
                .orElseThrow(() -> new StudentNotfound("there is no student with this id"));

        return StdeuntMapper.fromEntity(student);
    }

    public StudentDto createStudent(StudentRequest studentRequest) {
        Student student = StdeuntMapper.toEntity(studentRequest);
        Student saved = studentRepositry.save(student);

        return StdeuntMapper.fromEntity(saved);
    }

    public StudentDto updateStudent(Long id, StudentRequest studentRequest) {

        Student student = studentRepositry.findById(id)
                .orElseThrow(() -> new StudentNotfound("there is no student with this id"));

        student.setName(studentRequest.getName());
        student.setEmail(studentRequest.getEmail());
        student.setPhone(studentRequest.getPhone());
        student.setAddress(studentRequest.getAddress());
        student.setCity(studentRequest.getCity());
        student.setState(studentRequest.getState());
        student.setCountry(studentRequest.getCountry());
        student.setZipcode(studentRequest.getZipcode());
        student.setDescription(studentRequest.getDescription());

        Student updated = studentRepositry.save(student);

        return StdeuntMapper.fromEntity(updated);
    }

    public void deleteStudent(Long id) {
        Student student = studentRepositry.findById(id)
                .orElseThrow(() -> new StudentNotfound("there is no student with this id"));

        studentRepositry.delete(student);
    }

    public List<StudentDto> getStudents() {
        return studentRepositry.findAll()
                .stream()
                .map(StdeuntMapper::fromEntity)
                .toList();
    }
}