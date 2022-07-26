package com.robertogoz.api.controllers;

import com.robertogoz.api.dtos.UserDto;
import com.robertogoz.api.dtos.UserEditDto;
import com.robertogoz.api.entities.User;
import com.robertogoz.api.services.UserServices;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

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

    @GetMapping(value = ("/user/{id}"))
    public ResponseEntity<Object> getOneUser(@PathVariable(value="id") UUID id) {
        Optional<User> UserOptional = _userServices.findById(id);
        if(!UserOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }
        return ResponseEntity.status(HttpStatus.OK).body(UserOptional.get());
    }

    @PostMapping(value = ("/user"))
    public ResponseEntity<Object> saveUser(@RequestBody @Valid UserDto userDto) {
        var user = new User();
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        BeanUtils.copyProperties(userDto,user);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return ResponseEntity.status(HttpStatus.CREATED).body(_userServices.save(user));
    }

    @DeleteMapping("/user/{id}")
    public ResponseEntity<Object> deleteUser(@PathVariable(value = "id") UUID id) {
        Optional<User> UserOptional = _userServices.findById(id);

        if(!UserOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }
        _userServices.delete(UserOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body("User deleted successfully.");
    }

    @PutMapping("/user/{id}")
    public ResponseEntity<Object> updateUser(@PathVariable(value = "id") UUID id,
                                             @RequestBody @Valid UserEditDto userDto){
        Optional<User> UserOptional = _userServices.findById(id);
        if (!UserOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found.");
        }
        var user = new User();
        BeanUtils.copyProperties(userDto, user);
        user.setId(UserOptional.get().getId());
        user.setEmail(UserOptional.get().getEmail());
        return ResponseEntity.status(HttpStatus.OK).body(_userServices.save(user));
    }
}
