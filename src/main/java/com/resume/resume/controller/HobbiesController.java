package com.resume.resume.controller;

import com.resume.resume.model.Hobbies;
import com.resume.resume.service.HobbiesService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/hobbies")
@AllArgsConstructor
public class HobbiesController {

    private final HobbiesService hobbiesService;

    @GetMapping
    public List<Hobbies> retrievingAllHobbies() {
        return hobbiesService.retrieveAllHobbies();
    }
}
