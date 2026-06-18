package org.example.aleasofttrainner;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.*;

@Entity
@Table(name = "hotels")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String phone;
    private String address;
    private String city;
    private String state;
    private String country;
    private String zipcode;
    private String description;


    public Student( String name, String email, String phone, String city, String country, String description, String zipcode, String state, String address) {
    this.name = name;
    this.email = email;

    this.phone = phone;
    this.city = city;
    this.country = country;
    this.description = description;
    this.zipcode = zipcode;
    this.state = state;
    this.address = address;



    }

    public Student(String name, String email, String phone) {
        this.name = name;
        this.email = email;
        this.phone = phone;
    }
}
