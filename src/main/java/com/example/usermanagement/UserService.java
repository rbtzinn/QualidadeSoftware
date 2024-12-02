package com.example.usermanagement;

import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void registerUser(User user) {
        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            throw new EmailAlreadyExistsException("Email já registrado.");
        }
        if (!isValidPassword(user.getPassword())) {
            throw new InvalidPasswordException("Senha não atende aos critérios de segurança.");
        }
        userRepository.save(user);
    }

    private boolean isValidPassword(String password) {
        return password.length() >= 8 && password.matches(".*\\d.*");
    }
}
