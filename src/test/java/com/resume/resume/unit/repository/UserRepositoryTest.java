package com.resume.resume.unit.repository;

import com.resume.resume.model.Role;
import com.resume.resume.model.User;
import com.resume.resume.repository.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
class UserRepositoryTest {

    @Autowired
    private UserRepository underTest;

    @AfterEach
    void tearDown() {
        this.underTest.deleteAll();
    }

    @Test
    void findByEmail() {
        // given
        User user = new User(
                "testuser@email.com",
                "testuserpassword",
                Role.ADMIN
        );
        underTest.save(user);

        // act
        User retrievedUser = underTest.findByEmail("testuser@email.com").get();

        // then
        assertThat(retrievedUser).isEqualTo(user);
    }

    @Test
    void findByRole() {
        // given
        User user = new User(
                "testuser@email.com",
                "testuserpassword",
                Role.ADMIN
        );
        underTest.save(user);

        // act
        User retrievedUser = underTest.findByRole(Role.ADMIN).get();

        // then
        assertThat(retrievedUser).isEqualTo(user);
    }
}