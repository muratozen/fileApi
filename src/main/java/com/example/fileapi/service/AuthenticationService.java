package com.example.fileapi.service;

import com.example.fileapi.dto.UserDto;
import com.example.fileapi.dto.UserRequest;
import com.example.fileapi.dto.UserResponse;
import com.example.fileapi.entity.User;
import com.example.fileapi.enums.Role;
import com.example.fileapi.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository userRepository;

    private final JwtService jwtService;

    private final AuthenticationManager authenticationManager;

    private final PasswordEncoder passwordEncoder;

    public UserResponse save(UserDto userDto) {
        User user = User.builder().username(userDto.getUsername())
                .password(passwordEncoder.encode(userDto.getPassword())).nameSurname(userDto.getNameSurname())
                .role(Role.USER).build();

       userRepository.save(user);
       var token = jwtService.generateToken(user);
       return UserResponse.builder().token(token).build();
    }

    public UserResponse auth(UserRequest userRequest) {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userRequest.getUsername(),userRequest.getPassword()));
            User user= userRepository.findByUsername(userRequest.getUsername()).orElseThrow();
            String token = jwtService.generateToken(user);
            return UserResponse.builder().token(token).build();
    }
}
