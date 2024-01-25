package com.resume.resume.repository;

import com.resume.resume.model.Hobbies;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HobbiesRepository extends JpaRepository<Hobbies, Long> {
}
