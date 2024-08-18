package com.project.uber.uber.services;

import com.project.uber.uber.dto.DriverDto;
import com.project.uber.uber.dto.SignUpDto;
import com.project.uber.uber.dto.UserDto;

public interface AuthService {
    String login(String email, String password);
    UserDto signUp(SignUpDto SignUpDto);
    DriverDto onBoardNewDriver(Long userId);
}
