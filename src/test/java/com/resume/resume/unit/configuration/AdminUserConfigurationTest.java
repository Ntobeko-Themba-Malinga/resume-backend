package com.resume.resume.unit.configuration;

import com.resume.resume.configuration.AdminUserConfiguration;
import com.resume.resume.model.Role;
import com.resume.resume.model.User;
import com.resume.resume.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AdminUserConfigurationTest {

    @Mock private UserRepository userRepository;
    private AdminUserConfiguration underTest;

    @BeforeEach
    void setUp() {
        this.underTest = new AdminUserConfiguration();
    }

    @Test
    void addingAdminUserIfDoesNotExist() throws Exception {
        // Given
        User adminUser = new User(
                "admin@admin.com",
                "admin",
                Role.ADMIN
        );

        // Act
        ArgumentCaptor<User> userArgumentCaptor = ArgumentCaptor
                .forClass(User.class);
        underTest.commandLineRunner(userRepository).run(new String[]{});

        // Then
        verify(userRepository).save(userArgumentCaptor.capture());

        assertThat(userArgumentCaptor.getValue()).isEqualTo(adminUser);
    }

    @Test
    void addingAdminUserIfExist() throws Exception {
        // Given
        User adminUser = new User(
                "admin@admin.com",
                "admin",
                Role.ADMIN
        );

        when(userRepository.findByRole(any()))
                .thenReturn(Optional.of(adminUser));

        // Act
        underTest.commandLineRunner(userRepository).run(new String[]{});

        // Then
        verify(userRepository, never()).save(any());
    }
}