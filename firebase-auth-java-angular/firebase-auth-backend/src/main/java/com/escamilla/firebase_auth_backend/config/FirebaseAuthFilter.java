package com.escamilla.firebase_auth_backend.config;

import java.io.IOException;
import java.util.Collections;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.escamilla.firebase_auth_backend.payload.FirebaseUserPrincipal;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseToken;

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
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
          response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
          response.getWriter().write("Unauthorized: Missing or invalid Authorization header");
          return;
        }

        authToken = authHeader.substring(7);
        try {
          FirebaseToken decodedToken = FirebaseAuth.getInstance().verifyIdToken(authToken);
          FirebaseUserPrincipal principal = new FirebaseUserPrincipal(
            decodedToken.getUid(),
            decodedToken.getName()
          );
          UsernamePasswordAuthenticationToken authentication =
            new UsernamePasswordAuthenticationToken(principal, null, Collections.emptyList());
          SecurityContextHolder.getContext().setAuthentication(authentication);
        } catch (Exception e) {
          SecurityContextHolder.clearContext();
        }
        filterChain.doFilter(request, response);
      }

}
