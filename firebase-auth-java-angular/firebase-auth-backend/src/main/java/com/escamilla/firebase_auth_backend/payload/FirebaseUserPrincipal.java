package com.escamilla.firebase_auth_backend.payload;

import com.google.auto.value.AutoValue.Builder;

import lombok.Data;

@Data
@Builder
public class FirebaseUserPrincipal {
  private final String uuid;
  private final String name;
}
