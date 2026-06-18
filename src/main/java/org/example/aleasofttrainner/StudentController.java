package org.example.aleasofttrainner;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {

    final StudentCreate studentCreate;

    public StudentController(StudentCreate studentCreate) {
        this.studentCreate = studentCreate;
    }

    @GetMapping
    @Operation(summary = "Get all students")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Students returned"),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "403", description = "Forbidden")
    })
    public ResponseEntity<List<StudentDto>> getAllStudents() {
        return ResponseEntity.ok(studentCreate.getStudents());
    }

    @PostMapping
    @Operation(summary = "Create a new student")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Student created"),
            @ApiResponse(responseCode = "400", description = "Validation error"),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "403", description = "Forbidden")
    })
    public ResponseEntity<StudentDto> createStudent(@Valid @RequestBody StudentRequest request) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(studentCreate.createStudent(request));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update student by id")
    @ApiResponses({
            @ApiResponse(responseCode = "202", description = "Student updated"),
            @ApiResponse(responseCode = "400", description = "Validation error"),
            @ApiResponse(responseCode = "404", description = "Student not found")
    })
    public ResponseEntity<StudentDto> updateStudent(@PathVariable Long id,
                                                    @Valid @RequestBody StudentRequest request) {
        return ResponseEntity
                .status(HttpStatus.ACCEPTED)
                .body(studentCreate.updateStudent(id, request));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get student by id")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Student returned"),
            @ApiResponse(responseCode = "404", description = "Student not found"),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "403", description = "Forbidden")
    })
    public ResponseEntity<StudentDto> getStudent(@PathVariable Long id) {
        return ResponseEntity.ok(studentCreate.getStudentDto(id));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete student by id")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Student deleted"),
            @ApiResponse(responseCode = "404", description = "Student not found")
    })
    public ResponseEntity<Void> deleteStudent(@PathVariable Long id) {
        studentCreate.deleteStudent(id);
        return ResponseEntity.noContent().build();
    }
}