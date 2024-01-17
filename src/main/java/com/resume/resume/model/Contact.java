package com.resume.resume.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@Entity
@Table(name = "Contact")
public class Contact {

    @Id
    @SequenceGenerator(
            name = "contact_sequence",
            sequenceName = "contact_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "contact_sequence"
    )
    private long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String email;

    private String message;

    @Column(nullable = false)
    private LocalDate date;

    public Contact(String name, String email, String message, LocalDate date) {
        this.name = name;
        this.email = email;
        this.message = message;
        this.date = date;
    }
}
