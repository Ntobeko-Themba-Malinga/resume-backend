package com.resume.resume.service.impl;

import com.resume.resume.model.Hobbies;
import com.resume.resume.repository.HobbiesRepository;
import com.resume.resume.service.HobbiesService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class HobbiesServiceImpl implements HobbiesService {

    private HobbiesRepository hobbiesRepository;

    @Override
    public List<Hobbies> retrieveAllHobbies() {
        return hobbiesRepository.findAll();
    }

    @Override
    public Hobbies addHobbies(Hobbies hobbies) {
        return null;
    }

    @Override
    public Hobbies updateHobbies(long id, Hobbies updatedHobbies) {
        return null;
    }

    @Override
    public void deleteHobbies(long id) {

    }
}
