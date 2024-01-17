package com.resume.resume.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;

@Entity
@Table(name = "Skill")
@Data
public class Skill {

    @Id
    @SequenceGenerator(
            name = "skill_sequence",
            sequenceName = "skill_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "skill_sequence"
    )
    private long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private int level;

    public Skill(String title, int level) {
        this.title = title;
        this.level = level;
    }
}
