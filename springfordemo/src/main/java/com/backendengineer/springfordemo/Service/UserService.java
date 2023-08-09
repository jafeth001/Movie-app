package com.backendengineer.springfordemo.Service;

import com.backendengineer.springfordemo.Config.JwtService;
import com.backendengineer.springfordemo.Entity.User;
import com.backendengineer.springfordemo.Entity.UserRole;
import com.backendengineer.springfordemo.Models.UserAuthenticateResponse;
import com.backendengineer.springfordemo.Models.UserAuthenticaterequest;
import com.backendengineer.springfordemo.Models.UserRegisterrequest;
import com.backendengineer.springfordemo.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    public UserAuthenticateResponse Register(UserRegisterrequest request) {
        var user = User.builder()
                .firstname(request.getFirstname())
                .lastname(request.getLastname())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(UserRole.USER)
                .created(Instant.now())
                .build();
        userRepository.save(user);
        var token = jwtService.generatetoken(user);
        return UserAuthenticateResponse.builder()
                .token(token)
                .message("Account Created Successfully, " + request.getEmail())
                .build();
    }

    public UserAuthenticateResponse Authenticate(UserAuthenticaterequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        var user = userRepository.findByEmail(request.getEmail());
        var token = jwtService.generatetoken(user);
        return UserAuthenticateResponse.builder()
                .token(token)
                .message("Account Login Successfully, " + request.getEmail())
                .build();
    }

    public User getuserprofile(String email) {
        return userRepository.findByEmail(email);
    }
}
