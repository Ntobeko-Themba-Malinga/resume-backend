package com.resume.resume.unit.service.impl;

import com.resume.resume.model.Role;
import com.resume.resume.model.User;
import com.resume.resume.repository.UserRepository;
import com.resume.resume.service.impl.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;
    private UserServiceImpl underTest;

    @BeforeEach
    void setUp() {
        this.underTest = new UserServiceImpl(userRepository);
    }

    @Test
    void userDetailsUserFound() {
        // given
        User user = new User(
                "testuser",
                "testuser@email.com",
                Role.ADMIN
        );

        given(userRepository.findByEmail(any()))
                .willReturn(Optional.of(user));

        // act
        UserDetailsService userDetailsService = underTest.userDetails();
        UserDetails retrievedUser = userDetailsService.loadUserByUsername("testuser@email.com");

        // then
        assertThat(retrievedUser).isEqualTo(user);
    }

    @Test
    void userDetailsUserNotFound() {
        // given
        String username = "testuser@email.com";

        // act
        // then
        assertThatThrownBy(() -> underTest.userDetails().loadUserByUsername(username))
                .isInstanceOf(UsernameNotFoundException.class)
                .hasMessage("User not found!");
    }
}