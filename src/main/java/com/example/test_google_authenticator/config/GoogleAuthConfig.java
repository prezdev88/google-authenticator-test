package com.example.test_google_authenticator.config;

import java.util.concurrent.TimeUnit;

public class GoogleAuthConfig {

    private GoogleAuthConfig() {
        throw new IllegalStateException("Utility class");
    }

    public static final int TIME_WINDOW_SIZE = 1;
    public static final long TIME_STEP_SIZE = TimeUnit.SECONDS.toMillis(30);
    public static final String OTP_AUTH = "otpauth://totp/%s:%s?secret=%s&issuer=%s";
    public static final String ISSUER = "Awto";
    public static final String DEFAULT_EMAIL_ID = "@awto";
}
