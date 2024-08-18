package com.project.uber.uber.services.Impl;

import com.project.uber.uber.dto.DriverDto;
import com.project.uber.uber.dto.SignUpDto;
import com.project.uber.uber.dto.UserDto;
import com.project.uber.uber.entities.User;
import com.project.uber.uber.entities.enums.Role;
import com.project.uber.uber.exceptions.RuntimeConflictException;
import com.project.uber.uber.repositories.UserRepository;
import com.project.uber.uber.services.AuthService;
import com.project.uber.uber.services.RiderService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final ModelMapper modelMapper;
    private final UserRepository userRepository;
    private final RiderService riderService;

    @Override
    public String login(String email, String password) {
        return "";
    }

    @Override
    @Transactional
    public UserDto signUp(SignUpDto signUpDto) {
        User user = userRepository.findByEmail(signUpDto.getEmail()).orElse(null);
        if(user != null) {
             throw new RuntimeConflictException("User is already present with this email "+signUpDto.getEmail());
        }

        User mappedUser = modelMapper.map(signUpDto, User.class);
        mappedUser.setRoles(Set.of(Role.RIDER));
        User savedUser = userRepository.save(mappedUser);

        riderService.createRider(savedUser);
        return modelMapper.map(savedUser, UserDto.class);
    }

    @Override
    public DriverDto onBoardNewDriver(Long userId) {
        return null;
    }
}
