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

    public StudentDto getStudentDto(String name) {
        return studentRepositry.getStudentByName(name).orElseThrow(()->new StudentNotfound("there is no student with this name"));
                    }
  public StudentDto createStudent(StudentRequest studentRequest){
        return  StdeuntMapper.fromEntity(studentRepositry.save(StdeuntMapper.toEntity(studentRequest)));
  }
  public StudentDto  updateStudent(StudentRequest studentRequest){
      return  StdeuntMapper.fromEntity(studentRepositry.save(StdeuntMapper.toEntity(studentRequest)));
  }
  public void DeleteStudent(String name){
      studentRepositry.deleteStudentByName(name).orElseThrow(()->new StudentNotfound("there is no student with this name"));
  }
  public List<StudentDto> getStudents(){
        return studentRepositry.findAll().stream().map(StdeuntMapper::fromEntity).toList();
  }

}
