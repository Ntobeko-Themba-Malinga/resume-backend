package com.resume.resume.service.impl;

import com.resume.resume.exception.HobbiesNotFoundException;
import com.resume.resume.model.Hobbies;
import com.resume.resume.repository.HobbiesRepository;
import com.resume.resume.service.HobbiesService;
import jakarta.transaction.Transactional;
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
        return hobbiesRepository.save(hobbies);
    }

    @Transactional
    @Override
    public Hobbies updateHobbies(long id, Hobbies updatedHobbies) {
        Hobbies hobbies = hobbiesRepository.findById(id)
                .orElseThrow(() -> new HobbiesNotFoundException(
                        "Hobbies with id " + id + " not found!"
                ));

        if (!updatedHobbies.getTitle().trim().isEmpty())
            hobbies.setTitle(updatedHobbies.getTitle());

        if (!updatedHobbies.getImage().trim().isEmpty())
            hobbies.setImage(updatedHobbies.getImage());

        return hobbies;
    }

    @Override
    public void deleteHobbies(long id) {
        Hobbies hobbies = hobbiesRepository.findById(id)
                .orElseThrow(() -> new HobbiesNotFoundException(
                        "Hobbies with id " + id + " not found!"
                ));
        hobbiesRepository.delete(hobbies);
    }
}
