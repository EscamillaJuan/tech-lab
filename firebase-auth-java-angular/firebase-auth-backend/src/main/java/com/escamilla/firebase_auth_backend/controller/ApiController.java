package com.escamilla.firebase_auth_backend.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.escamilla.firebase_auth_backend.payload.FirebaseUserPrincipal;
import com.escamilla.firebase_auth_backend.payload.ResponseDto;

@RestController
@RequestMapping("/api/v1")
public class ApiController {

  @GetMapping()
  public ResponseEntity<ResponseDto> protectedMethod(@AuthenticationPrincipal FirebaseUserPrincipal user) {
    ResponseDto response = new ResponseDto();
    response.setMessage("Hello, you are correctly authenticated");
    response.setUser(user.getName());
    return ResponseEntity.ok(response);
  }
}

