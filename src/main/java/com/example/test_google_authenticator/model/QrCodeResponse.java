package com.example.test_google_authenticator.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class QrCodeResponse {
    private String secret;
    private String qrUrl;
}
