package com.example.usermanagement;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AuthenticationServiceTest {
    private final UserRepository userRepository = mock(UserRepository.class);
    private final AuthenticationService authService = new AuthenticationService(userRepository);

    @Test
    void shouldLoginSuccessfully() {
        User user = new User("Roberto", "roberto@example.com", "Password123");
        when(userRepository.findByEmail(user.getEmail())).thenReturn(Optional.of(user));

        assertTrue(authService.login("roberto@example.com", "Password123"));
    }

    @Test
    void shouldFailLoginForInvalidCredentials() {
        when(userRepository.findByEmail("roberto@example.com")).thenReturn(Optional.empty());

        assertFalse(authService.login("roberto@example.com", "Password123"));
    }
}
