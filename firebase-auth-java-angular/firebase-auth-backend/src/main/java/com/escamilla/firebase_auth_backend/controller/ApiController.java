package com.escamilla.firebase_auth_backend.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.escamilla.firebase_auth_backend.payload.FirebaseUserPrincipal;

@RestController
@RequestMapping("/api/v1")
public class ApiController {

  @GetMapping()
  public ResponseEntity<String> protectedMethod(@AuthenticationPrincipal FirebaseUserPrincipal user) {
    String name = user.getName();
    return ResponseEntity.ok().body("Hello: " + name + ", you are correctly authenticated");
  }
}
