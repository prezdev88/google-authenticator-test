package com.example.test_google_authenticator.service;

import com.example.test_google_authenticator.model.QrCodeResponse;
import com.example.test_google_authenticator.model.VerifyRequest;

public interface AuthService {
    QrCodeResponse generate(String emailId);

    boolean verify(VerifyRequest verifyRequest);
}
