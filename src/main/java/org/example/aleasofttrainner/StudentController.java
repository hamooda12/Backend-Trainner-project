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
    @Operation(summary = "get hotels")
    @ApiResponses({


            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "403", description = "Forbidden")
    })
    public ResponseEntity<List<StudentDto>> getAllStudents(){
        return  ResponseEntity.status(HttpStatus.ACCEPTED).body(studentCreate.getStudents());
 }
    @PostMapping
    @Operation(summary = "Create a new hotel")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Hotel created"),
            @ApiResponse(responseCode = "400", description = "Validation error"),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "403", description = "Forbidden")
    })
    public ResponseEntity<StudentDto> createHotel(@Valid @RequestBody StudentRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(studentCreate.createStudent(request));
    }
    @Operation(summary = "get hotels")
    @ApiResponses({


            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "403", description = "Forbidden"),
            @ApiResponse(responseCode = "404", description = "NotFound")
    })
    public ResponseEntity<StudentDto> getStudent(@PathVariable String  name) {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(studentCreate.getStudentDto(name));
    }
    public   ResponseEntity<Void> deleteStudent(@PathVariable String  name) {
        studentCreate.DeleteStudent(name);
        return  ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }




}
