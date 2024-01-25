package com.resume.resume.service;

import com.resume.resume.model.Hobbies;

import java.util.List;

public interface HobbiesService {

    public List<Hobbies> retrieveAllHobbies();

    public Hobbies addHobbies(Hobbies hobbies);

    public Hobbies updateHobbies(long id, Hobbies updatedHobbies);

    public void deleteHobbies(long id);
}
