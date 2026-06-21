package org.example.aleasofttrainner;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
@ActiveProfiles("test")
class StudentE2ETest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void createStudent_shouldReturnCreatedStudent() throws Exception {
        String requestBody = """
                {
                  "name": "Omar",
                  "email": "omar@test.com",
                  "phone": "0599999999",
                  "address": "Ramallah",
                  "city": "Ramallah",
                  "state": "West Bank",
                  "country": "Palestine",
                  "zipcode": "00970",
                  "description": "Test student"
                }
                """;

        mockMvc.perform(post("/students")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name", is("Sami")))
                .andExpect(jsonPath("$.email", is("omar@test.com")));
    }
}