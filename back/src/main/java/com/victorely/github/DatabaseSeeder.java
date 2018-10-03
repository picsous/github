package com.victorely.github;

import com.victorely.github.configuration.security.PasswordEncrypter;
import com.victorely.github.entities.Role;
import com.victorely.github.entities.User;
import com.victorely.github.repositories.RoleRepository;
import com.victorely.github.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static com.victorely.github.constants.RolesNames.ROLE_ADMIN;
import static com.victorely.github.constants.RolesNames.ROLE_USER;

@Service
public class DatabaseSeeder {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    public void seedDatabase() {
        if (isDatabaseSeeded()) {
            System.out.println("Database is already seeded. Exiting seeder.");
            return;
        }

        runSeedDatabase();

        System.out.println("Database successfully initialized ! Exiting seeder \uD83D\uDC4D");
    }

    private boolean isDatabaseSeeded() {
        User user = userRepository.findOne(1L);
        // if it's found, the database is already seeded
        return user != null;
    }

    private void runSeedDatabase() {

        Role roleAdmin = new Role(ROLE_ADMIN);
        Role roleUser = new Role(ROLE_USER);

        List<Role> lRoleAdmin = new ArrayList<>(Collections.singleton(roleAdmin));
        List<Role> lRoleUser = new ArrayList<>(Collections.singleton(roleUser));

        User admin = new User("admin", "Victor", "ELY", "test", "victor.ely@orange.fr", lRoleAdmin);
        User user = new User("user", "Amalric", "DE BUFFIERE", "test", "victor.ely@orange.fr", lRoleUser);

        PasswordEncrypter.encryptPassword(admin, user);

        List<User> users = new ArrayList<>(Arrays.asList(admin, user));
        List<Role> roles = new ArrayList<>(Arrays.asList(roleAdmin, roleUser));

        roleRepository.save(roles);
        userRepository.save(users);

    }

}
