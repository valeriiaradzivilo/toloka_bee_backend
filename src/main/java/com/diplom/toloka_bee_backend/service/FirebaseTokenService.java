package com.diplom.toloka_bee_backend.service;

import com.google.auth.oauth2.AccessToken;
import com.google.auth.oauth2.GoogleCredentials;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Service
public class FirebaseTokenService {

    @Value("${firebase.service-account.path}")
    private String serviceAccountPath;

    public String getAccessToken() throws IOException {
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream(serviceAccountPath);
        if (inputStream == null) {
            throw new FileNotFoundException("Resource not found: firebase-service-account.json");
        }

        GoogleCredentials googleCredentials = GoogleCredentials
                .fromStream(inputStream)
                .createScoped(List.of("https://www.googleapis.com/auth/firebase.messaging"));

        googleCredentials.refresh();
        AccessToken accessToken = googleCredentials.getAccessToken();
        if (accessToken == null) {
            throw new IOException("Failed to obtain access token");
        }

        return accessToken.getTokenValue();
    }
}