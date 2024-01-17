package com.resume.resume.unit.service.impl;

import com.resume.resume.model.Role;
import com.resume.resume.model.User;
import com.resume.resume.service.impl.JWTServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.security.core.userdetails.UserDetails;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


class JWTServiceImplTest {

    private JWTServiceImpl underTest;
    private User user;

    private String generateToken(UserDetails user) {
        return underTest.generateToken(user);
    }

    @BeforeEach
    void setUp() {
        this.underTest = new JWTServiceImpl();
        this.user = new User(
                "testuser",
                "testuser@email.com",
                Role.ADMIN
        );
    }

    @Test
    void extractUsername() {
        // given
        String token = underTest.generateToken(user);

        // act
        String email = underTest.extractUsername(token);

        // then
        assertThat(email).isEqualTo(this.user.getUsername());
    }

    @Test
    void isTokenValidTrue() {
        // given
        String token = underTest.generateToken(user);
        System.out.println(token);

        // act
        boolean tokenIsValid = underTest.isTokenValid(token, this.user);

        // then
        assertThat(tokenIsValid).isTrue();
    }
}