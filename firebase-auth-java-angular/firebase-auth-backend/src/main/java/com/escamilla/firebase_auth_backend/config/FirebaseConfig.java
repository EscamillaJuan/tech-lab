package com.escamilla.firebase_auth_backend.config;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;

@Configuration
public class FirebaseConfig {
  String credentialsEnv = System.getenv("FIREBASE_CREDENTIALS_JSON");
  String emulatorHost = System.getenv("FIREBASE_AUTH_EMULATOR_HOST");
  InputStream serviceAccount;

  @Bean
  public FirebaseApp firebaseApp() throws IOException {
    FirebaseOptions options;

    if (emulatorHost != null && !emulatorHost.isBlank()) {
      options = FirebaseOptions.builder()
        .setProjectId("firebase-auth-java-angular")
        .setCredentials(GoogleCredentials.create(null))
        .build();
    } else {
      if (credentialsEnv != null && !credentialsEnv.isBlank()) {
        serviceAccount = new ByteArrayInputStream(credentialsEnv.getBytes(StandardCharsets.UTF_8));
      } else {
        serviceAccount = Objects.requireNonNull(
          getClass().getClassLoader().getResourceAsStream("firebase-key.json"),
          "No Firebase credentials found. Please set the FIREBASE_CREDENTIALS_JSON environment variable or place firebase-key.json in the properties."
        );
      }
      options = FirebaseOptions.builder()
        .setCredentials(GoogleCredentials.fromStream(serviceAccount))
        .build();
      }
      return FirebaseApp.initializeApp(options);
  }
}
