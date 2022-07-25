package com.robertogoz.api.controllers;

import com.robertogoz.api.dtos.UserDto;
import com.robertogoz.api.entities.User;
import com.robertogoz.api.services.UserServices;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class UserController {
    final UserServices _userServices;

    public UserController(UserServices userServices) {
        this._userServices = userServices;
    }

    @GetMapping(value = ("/users"))
    public List<User> getAll() {
        return _userServices.findAll();
    }

    @PostMapping(value = ("/user"))
    public ResponseEntity<Object> saveUser(@RequestBody @Valid UserDto userDto) {
        var user = new User();
        BeanUtils.copyProperties(userDto,user);
        return ResponseEntity.status(HttpStatus.CREATED).body(_userServices.save(user));
    }
}
