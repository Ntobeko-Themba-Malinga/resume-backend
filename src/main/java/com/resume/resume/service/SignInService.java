package com.resume.resume.service;

import com.resume.resume.dto.SignInRequestDTO;
import com.resume.resume.dto.SignInResponseDTO;

public interface SignInService {

    public SignInResponseDTO signIn(SignInRequestDTO signInRequestDTO);
}
