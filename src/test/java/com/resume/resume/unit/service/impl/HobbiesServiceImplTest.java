package com.resume.resume.unit.service.impl;

import com.resume.resume.model.Hobbies;
import com.resume.resume.repository.HobbiesRepository;
import com.resume.resume.service.impl.HobbiesServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class HobbiesServiceImplTest {

    @Mock private HobbiesRepository hobbiesRepository;
    private HobbiesServiceImpl underTest;

    @BeforeEach
    void setUp() {
        this.underTest = new HobbiesServiceImpl(hobbiesRepository);
    }

    @Test
    void retrieveAllHobbies() {
        // given
        Hobbies hobbies = new Hobbies();
        hobbies.setTitle("Test");
        hobbies.setImage("test");

        // act
        underTest.retrieveAllHobbies();

        // then
        verify(hobbiesRepository).findAll();
    }
}