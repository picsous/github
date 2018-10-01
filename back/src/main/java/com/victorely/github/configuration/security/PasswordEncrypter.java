package com.victorely.github.configuration.security;

import com.victorely.github.entities.User;
import org.bouncycastle.jcajce.provider.digest.SHA3;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;

@Service
public class PasswordEncrypter {

    public static void encryptPassword(User... user) {
        for (User u : user) {
            encryptPassword(u);
        }
    }

    public static void encryptPassword(User user) {
        String password = user.getPassword();
        String encryptedPassword = encryptPassword(password);
        user.setPassword(encryptedPassword);
    }

    public static String encryptPassword(String password) {
        SHA3.DigestSHA3 sha3Password = new SHA3.DigestSHA3(512);
        sha3Password.update(password.getBytes(StandardCharsets.UTF_8));

        byte[] digestPassword = sha3Password.digest();
        String encryptedPassword = "";
        for (byte d : digestPassword) {
            encryptedPassword += d;
        }

        return encryptedPassword;
    }
}