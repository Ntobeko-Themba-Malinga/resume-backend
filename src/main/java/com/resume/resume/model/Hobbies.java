package com.resume.resume.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@Entity
@Table(name = "Hobbies")
public class Hobbies {

    @Id
    @SequenceGenerator(
            name = "sequence_generator",
            sequenceName = "sequence_generator",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "sequence_generator"
    )
    private long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String image;

    public Hobbies(String title, String image) {
        this.title = title;
        this.image = image;
    }
}
