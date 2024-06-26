package com.example.test_google_authenticator.controller;

import com.example.test_google_authenticator.config.GoogleAuthConfig;
import com.example.test_google_authenticator.model.QrCodeResponse;
import com.example.test_google_authenticator.model.VerifyRequest;
import com.example.test_google_authenticator.model.VerifyResponse;
import com.example.test_google_authenticator.service.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@AllArgsConstructor
public class AuthController {

    private final AuthService googleAuthServiceImpl;

    // curl -X GET 'http://localhost:8080/auth/generate?emailId=user@awto.cl'
    @GetMapping("/generate")
    public ResponseEntity<QrCodeResponse> generateSecret(
            @RequestParam(defaultValue = GoogleAuthConfig.DEFAULT_EMAIL_ID) String emailId) {
        return ResponseEntity.ok(googleAuthServiceImpl.generate(emailId));
    }

    // curl -X POST http://localhost:8080/auth/verify -H "Content-Type: application/json" -d '{"secret": "E6ZL5YSB37ZQM63F", "code": 183532}'
    @PostMapping("/verify")
    public ResponseEntity<VerifyResponse> verify(@RequestBody VerifyRequest verifyRequest) {
        boolean valid = googleAuthServiceImpl.verify(verifyRequest);
        return ResponseEntity.ok(new VerifyResponse(valid));
    }
}
