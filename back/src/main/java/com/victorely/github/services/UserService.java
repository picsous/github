package com.victorely.github.services;

import com.victorely.github.configuration.security.PasswordEncrypter;
import com.victorely.github.entities.User;
import com.victorely.github.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    private final int RESULTS_PER_PAGE = 20;

    public User authenticate(String login, String password) {
        String encryptedPassword = PasswordEncrypter.encryptPassword(password);
        User user = userRepository.findByUsernameAndPassword(login, encryptedPassword);

        if (user == null) {
            return null;
        }

        UUID uuid = UUID.randomUUID();
        String token = uuid.toString();
        user.setToken(token);
        userRepository.save(user);

        return user;
    }

    public Page<User> getAll(int page) {
        return userRepository.findAll(PageRequest.of(page, RESULTS_PER_PAGE));
    }

}
