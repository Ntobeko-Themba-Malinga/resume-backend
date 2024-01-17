package com.resume.resume.service.impl;

import com.resume.resume.dto.SignInRequestDTO;
import com.resume.resume.dto.SignInResponseDTO;
import com.resume.resume.model.User;
import com.resume.resume.repository.UserRepository;
import com.resume.resume.service.JWTService;
import com.resume.resume.service.SignInService;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class SignInServiceImpl implements SignInService {

    private final JWTService jwtService;
    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;

    @Override
    public SignInResponseDTO signIn(SignInRequestDTO signInRequestDTO) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        signInRequestDTO.getEmail(),
                        signInRequestDTO.getPassword()
                )
        );

        User user = userRepository.findByEmail(signInRequestDTO.getEmail())
                .orElseThrow(() -> new UsernameNotFoundException("User not found!"));
        String token = jwtService.generateToken(user);
        return new SignInResponseDTO(token);
    }
}
