package com.diplom.zip_way_backend.service;

import com.google.auth.oauth2.GoogleCredentials;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Collections;

@Service
public class FirebaseTokenService {

    @Value("${firebase.service-account.path}")
    private Resource serviceAccountResource;

    public String getAccessToken() throws IOException {
        GoogleCredentials googleCredentials = GoogleCredentials
                .fromStream(serviceAccountResource.getInputStream())
                .createScoped(Collections.singleton("https://www.googleapis.com/auth/firebase.messaging"));

        googleCredentials.refreshIfExpired();
        return googleCredentials.getAccessToken().getTokenValue();
    }
}