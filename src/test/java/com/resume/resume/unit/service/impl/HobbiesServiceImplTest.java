package com.resume.resume.unit.service.impl;

import com.resume.resume.exception.HobbiesNotFoundException;
import com.resume.resume.model.Hobbies;
import com.resume.resume.repository.HobbiesRepository;
import com.resume.resume.service.impl.HobbiesServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.swing.text.html.Option;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

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
        // act
        underTest.retrieveAllHobbies();

        // then
        verify(hobbiesRepository).findAll();
    }

    @Test
    void addHobbies() {
        // given
        Hobbies hobbies = new Hobbies("Test", "test");

        // act
        underTest.addHobbies(hobbies);

        // then
        ArgumentCaptor<Hobbies> hobbiesArgumentCaptor = ArgumentCaptor
                .forClass(Hobbies.class);

        verify(hobbiesRepository).save(hobbiesArgumentCaptor.capture());

        assertThat(hobbiesArgumentCaptor.getValue()).isEqualTo(hobbies);
    }

    @Test
    void updateHobbiesIfExist() {
        // given
        long id = 1;
        Hobbies hobbies = new Hobbies("Test", "test");
        Hobbies updatedHobbies = new Hobbies("updated test", "updated test");

        when(hobbiesRepository.findById(id))
                .thenReturn(Optional.of(hobbies));

        // act
        underTest.updateHobbies(id, updatedHobbies);

        // then
        assertThat(hobbies).isEqualTo(updatedHobbies);
    }

    @Test
    void updateHobbiesIfDoesNotExist() {
        // given
        long id = 1;
        Hobbies hobbies = new Hobbies("Test", "test");

        // act
        // then
        assertThatThrownBy(() -> underTest.updateHobbies(id, hobbies))
                .isInstanceOf(HobbiesNotFoundException.class)
                .hasMessage("Hobbies with id " + id + " not found!");
    }

    @Test
    void deleteHobbiesIfExist() {
        // given
        long id = 1;
        Hobbies hobbies = new Hobbies("Test", "test");

        when(hobbiesRepository.findById(id))
                .thenReturn(Optional.of(hobbies));

        // act
        underTest.deleteHobbies(id);

        // then
        ArgumentCaptor<Hobbies> hobbiesArgumentCaptor = ArgumentCaptor
                .forClass(Hobbies.class);

        verify(hobbiesRepository).delete(hobbiesArgumentCaptor.capture());

        assertThat(hobbiesArgumentCaptor.getValue()).isEqualTo(hobbies);
    }

    @Test
    void deleteHobbiesIfDoesNotExist() {
        // given
        long id = 1;

        // act
        // then
        assertThatThrownBy(() -> underTest.deleteHobbies(id))
                .isInstanceOf(HobbiesNotFoundException.class)
                .hasMessage("Hobbies with id " + id + " not found!");

        verify(hobbiesRepository, never()).delete(any());
    }
}