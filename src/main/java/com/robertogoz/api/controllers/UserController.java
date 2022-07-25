package com.robertogoz.api.controllers;

import com.robertogoz.api.entities.User;
import com.robertogoz.api.services.UserServices;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {
    final UserServices _userServices;

    public UserController(UserServices userServices) {
        this._userServices = userServices;
    }

    @GetMapping(value = ("/users"))
    public List<User> getAll() {
        return _userServices.findAll();
    }
}
