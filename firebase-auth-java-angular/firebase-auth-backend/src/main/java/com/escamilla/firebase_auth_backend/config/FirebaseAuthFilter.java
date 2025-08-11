package com.escamilla.firebase_auth_backend.config;

import java.io.IOException;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.google.firebase.auth.FirebaseAuth;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class FirebaseAuthFilter extends OncePerRequestFilter {

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
      throws ServletException, IOException {
        String authHeader = request.getHeader("Authorization");
        String authToken = null;
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
          filterChain.doFilter(request, response);
          return;
        }
        if (authHeader != null) {
          authToken = authHeader.substring(7);
        }
        try {
          FirebaseAuth.getInstance().verifyIdToken(authToken);
        } catch (Exception e) {
          response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
          response.getWriter().write("Unauthorized: " + e.getMessage());
          return;
        }
      }

}
