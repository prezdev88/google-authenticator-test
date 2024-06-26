package com.example.test_google_authenticator.service.impl;

import com.example.test_google_authenticator.config.GoogleAuthConfig;
import com.example.test_google_authenticator.model.QrCodeResponse;
import com.example.test_google_authenticator.model.VerifyRequest;
import com.example.test_google_authenticator.service.AuthService;
import com.warrenstrange.googleauth.GoogleAuthenticator;
import com.warrenstrange.googleauth.GoogleAuthenticatorConfig;
import com.warrenstrange.googleauth.GoogleAuthenticatorConfig.GoogleAuthenticatorConfigBuilder;
import com.warrenstrange.googleauth.GoogleAuthenticatorKey;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

@Service
public class GoogleAuthServiceImpl implements AuthService {

    private GoogleAuthenticator googleAuthenticator;

    @PostConstruct
    public void init() {
        GoogleAuthenticatorConfig configBuilder = new GoogleAuthenticatorConfigBuilder()
                .setTimeStepSizeInMillis(GoogleAuthConfig.TIME_STEP_SIZE)
                .setWindowSize(GoogleAuthConfig.TIME_WINDOW_SIZE)
                .build();

        this.googleAuthenticator = new GoogleAuthenticator(configBuilder);
    }

    @Override
    public QrCodeResponse generate(String emailId) {
        String secret = generateSecretKey();
        String qrUrl = getQRBarcodeURL(emailId, secret);

        return new QrCodeResponse(secret, qrUrl);
    }

    @Override
    public boolean verify(VerifyRequest verifyRequest) {
        return googleAuthenticator.authorize(verifyRequest.getSecret(), verifyRequest.getCode());
    }

    private String generateSecretKey() {
        GoogleAuthenticatorKey googleAuthenticatorKey = googleAuthenticator.createCredentials();
        return googleAuthenticatorKey.getKey();
    }

    private String getQRBarcodeURL(String user, String secret) {
        return String.format(GoogleAuthConfig.OTP_AUTH,
                GoogleAuthConfig.ISSUER, user, secret, GoogleAuthConfig.ISSUER);
    }
}
