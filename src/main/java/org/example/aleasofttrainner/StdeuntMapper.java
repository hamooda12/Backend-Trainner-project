package org.example.aleasofttrainner;

public class StdeuntMapper {

    public static StudentDto fromEntity(Student s) {
        return new StudentDto(s.getName(), s.getEmail(), s.getPhone());
    }

    public static Student toEntity(StudentRequest dto) {
        return new Student(
                dto.getName(),
                dto.getEmail(),
                dto.getPhone(),
                dto.getCity(),
                dto.getCountry(),
                dto.getDescription(),
                dto.getZipcode(),
                dto.getState(),
                dto.getAddress()
        );
    }
}