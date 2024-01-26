package com.resume.resume.controller;

import com.resume.resume.model.Hobbies;
import com.resume.resume.service.HobbiesService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(path = "api/v1/hobbies")
@AllArgsConstructor
public class HobbiesController {

    private final HobbiesService hobbiesService;

    @GetMapping
    public List<Hobbies> retrievingAllHobbies() {
        return hobbiesService.retrieveAllHobbies();
    }

    @PostMapping
    public Hobbies addingHobbies(@RequestBody Hobbies hobbies) {
        return hobbiesService.addHobbies(hobbies);
    }

    @PutMapping(path = "{hobbiesId}")
    public Hobbies updatingHobbies(@PathVariable("hobbiesId") long hobbiesId,
                                   @RequestBody Hobbies updatedHobbies) {
        return hobbiesService.updateHobbies(hobbiesId, updatedHobbies);
    }

    @DeleteMapping(path = "{hobbiesId}")
    public void deletingHobbies(@PathVariable("hobbiesId") long hobbiesId) {
        hobbiesService.deleteHobbies(hobbiesId);
    }
}
