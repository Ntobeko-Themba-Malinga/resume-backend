package com.resume.resume.controller;

import com.resume.resume.dto.SignInRequestDTO;
import com.resume.resume.dto.SignInResponseDTO;
import com.resume.resume.service.SignInService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping(path = "api/v1/auth")
@AllArgsConstructor
public class SignInController {

    private final SignInService signInService;

    @PostMapping
    public ResponseEntity<SignInResponseDTO> logginIn(@RequestBody SignInRequestDTO signInRequestDTO) {
        return ResponseEntity.ok(signInService.signIn(signInRequestDTO));
    }
}
