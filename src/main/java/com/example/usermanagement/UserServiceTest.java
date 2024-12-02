package com.example.usermanagement;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserServiceTest {
    private final UserRepository userRepository = mock(UserRepository.class);
    private final UserService userService = new UserService(userRepository);

    @Test
    void shouldRegisterUserSuccessfully() {
        User user = new User("Roberto", "roberto@example.com", "Password123");
        when(userRepository.findByEmail(user.getEmail())).thenReturn(Optional.empty());

        userService.registerUser(user);

        verify(userRepository, times(1)).save(user);
    }

    @Test
    void shouldThrowExceptionForDuplicateEmail() {
        User user = new User("Roberto", "roberto@example.com", "Password123");
        when(userRepository.findByEmail(user.getEmail())).thenReturn(Optional.of(user));

        assertThrows(EmailAlreadyExistsException.class, () -> userService.registerUser(user));
    }

    @Test
    void shouldThrowExceptionForInvalidPassword() {
        User user = new User("Roberto", "roberto@example.com", "pass");

        assertThrows(InvalidPasswordException.class, () -> userService.registerUser(user));
    }
}
