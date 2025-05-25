package com.diplom.toloka_bee_backend.config;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.util.logging.Logger;

@Configuration
public class FirebaseConfig {

    private static final Logger LOGGER = Logger.getLogger(FirebaseConfig.class.getName());

    @Value("${firebase.service-account.path}")
    private String serviceAccountPath;

    @PostConstruct
    public void initialize() {
        try {
            if (FirebaseApp.getApps().isEmpty()) {
                LOGGER.info("Initializing Firebase with service account path: " + serviceAccountPath);

                try (var serviceAccount = getClass().getClassLoader().getResourceAsStream(serviceAccountPath)) {
                    if (serviceAccount == null) {
                        throw new IllegalStateException("Service account file not found in classpath: " + serviceAccountPath);
                    }

                    FirebaseOptions options = FirebaseOptions.builder()
                            .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                            .build();

                    FirebaseApp.initializeApp(options);
                    LOGGER.info("Firebase initialized successfully.");
                }
            }
        } catch (IOException e) {
            throw new IllegalStateException("Could not initialize Firebase", e);
        }
    }
}