package com.raxrot.userservice.service.impl;

import com.raxrot.userservice.dto.UserRequest;
import com.raxrot.userservice.dto.UserResponse;
import com.raxrot.userservice.exception.ApiException;
import com.raxrot.userservice.mapper.UserMapper;
import com.raxrot.userservice.model.User;
import com.raxrot.userservice.repository.UserRepository;
import com.raxrot.userservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public UserResponse createUser(UserRequest userRequest) {
        if (userRepository.existsByEmail(userRequest.getEmail())) {
            throw new ApiException("Email already exists", HttpStatus.CONFLICT);
        }

        if (userRepository.existsByUsername(userRequest.getUsername())) {
            throw new ApiException("Username already exists", HttpStatus.CONFLICT);
        }

        User user= UserMapper.mapToUser(userRequest);
        User savedUser=userRepository.save(user);

        return UserMapper.mapToUserResponse(savedUser);
    }

    @Override
    public UserResponse getUserById(Long id) {
        User user=userRepository.findById(id)
                .orElseThrow(()->new ApiException("User not found", HttpStatus.NOT_FOUND));
        return UserMapper.mapToUserResponse(user);
    }

    @Override
    public List<UserResponse> getAllUsers() {
        List<User>users=userRepository.findAll();
        List<UserResponse>userResponses=users.stream()
                .map(user->UserMapper.mapToUserResponse(user))
                .toList();
        return userResponses;
    }

    @Override
    public UserResponse updateUser(Long id, UserRequest userRequest) {

        User user = userRepository.findById(id)
                .orElseThrow(() -> new ApiException("User not found", HttpStatus.NOT_FOUND));

        if (!userRequest.getEmail().equals(user.getEmail()) &&
                userRepository.existsByEmail(userRequest.getEmail())) {
            throw new ApiException("Email already exists", HttpStatus.CONFLICT);
        }

        if (!userRequest.getUsername().equals(user.getUsername()) &&
                userRepository.existsByUsername(userRequest.getUsername())) {
            throw new ApiException("Username already exists", HttpStatus.CONFLICT);
        }

        if (userRequest.getUsername() != null) user.setUsername(userRequest.getUsername());
        if (userRequest.getEmail() != null) user.setEmail(userRequest.getEmail());
        if (userRequest.getPhone() != null) user.setPhone(userRequest.getPhone());
        if (userRequest.getRole() != null) user.setRole(userRequest.getRole());
        if (userRequest.getPassword() != null) user.setPassword(userRequest.getPassword());
        if (userRequest.getFullName() != null) user.setFullName(userRequest.getFullName());

        User updatedUser = userRepository.save(user);

        return UserMapper.mapToUserResponse(updatedUser);
    }


    @Override
    public void deleteUser(Long id) {
        if (!userRepository.existsById(id)) {
            throw new ApiException("User not found", HttpStatus.NOT_FOUND);
        }
        userRepository.deleteById(id);
    }
}
