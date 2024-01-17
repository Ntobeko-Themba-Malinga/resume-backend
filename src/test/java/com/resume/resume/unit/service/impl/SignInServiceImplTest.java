package com.resume.resume.unit.service.impl;

import com.resume.resume.dto.SignInRequestDTO;
import com.resume.resume.dto.SignInResponseDTO;
import com.resume.resume.model.Role;
import com.resume.resume.model.User;
import com.resume.resume.repository.UserRepository;
import com.resume.resume.service.JWTService;
import com.resume.resume.service.impl.SignInServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Answers;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class SignInServiceImplTest {

    @Mock private JWTService jwtService;
    @Mock private AuthenticationManager authenticationManager;
    @Mock private UserRepository userRepository;
    private SignInServiceImpl underTest;

    @BeforeEach
    void setUp() {
        this.underTest = new SignInServiceImpl(
                jwtService,
                authenticationManager,
                userRepository
        );
    }

    @Test
    void signInExistingAdminUser() {
        // Given
        String email = "testuser@email.com";
        String password = "testuserpassword";
        User user = new User(
                email,
                password,
                Role.ADMIN
        );
        SignInRequestDTO signInRequest = new SignInRequestDTO(
                email,
                password
        );
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
                email,
                password
        );

        // Act
        when(jwtService.generateToken(any()))
                .thenReturn("testing");
        when(authenticationManager.authenticate(any()))
                .then(Answers.RETURNS_SELF);
        when(userRepository.findByEmail(email))
                .thenReturn(Optional.of(user));
        SignInResponseDTO signInResponseDTO = underTest.signIn(signInRequest);

        // Then
        ArgumentCaptor<UsernamePasswordAuthenticationToken> usernameTokenCaptor = ArgumentCaptor
                .forClass(UsernamePasswordAuthenticationToken.class);
        ArgumentCaptor<User> userArgumentCaptor = ArgumentCaptor
                .forClass(User.class);

        verify(authenticationManager).authenticate(usernameTokenCaptor.capture());
        verify(userRepository).findByEmail(any());
        verify(jwtService).generateToken(userArgumentCaptor.capture());

        assertThat(signInResponseDTO.getToken()).isEqualTo("testing");
        assertThat(usernameTokenCaptor.getValue()).isEqualTo(token);
        assertThat(userArgumentCaptor.getValue()).isEqualTo(user);
    }
}