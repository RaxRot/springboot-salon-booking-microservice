package com.raxrot.userservice.controller;

import com.raxrot.userservice.dto.UserRequest;
import com.raxrot.userservice.dto.UserResponse;
import com.raxrot.userservice.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<UserResponse> createUser(@Valid @RequestBody UserRequest userRequest) {
        return new ResponseEntity<>(userService.createUser(userRequest), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<UserResponse>>getAllUsers(){
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserResponse>getUserById(@PathVariable Long userId){
        return ResponseEntity.ok(userService.getUserById(userId));
    }

    @PutMapping("/{userId}")
    public ResponseEntity<UserResponse>updateUser(@PathVariable Long userId,@Valid @RequestBody UserRequest userRequest){
        return ResponseEntity.ok(userService.updateUser(userId, userRequest));
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Void>deleteUser(@PathVariable Long userId){
        userService.deleteUser(userId);
        return ResponseEntity.ok().build();
    }
}
