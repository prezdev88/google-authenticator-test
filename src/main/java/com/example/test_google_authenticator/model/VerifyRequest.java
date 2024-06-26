package com.example.test_google_authenticator.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VerifyRequest {
    private int code;
    private String secret;
}
