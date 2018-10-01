package com.victorely.github.controllers;

import com.victorely.github.dto.LoginFormDTO;
import com.victorely.github.dto.SuccessfulLoginDTO;
import com.victorely.github.entities.User;
import com.victorely.github.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public SuccessfulLoginDTO login(@RequestBody LoginFormDTO form, HttpServletResponse res) {
        User user = userService.authenticate(form.getLogin(), form.getPassword());

        if (user == null) {
            res.setStatus(400);
            return null; // TODO throw exception and add an exception handler (see DocumentController)
        }

        return new SuccessfulLoginDTO(user);
    }

}
