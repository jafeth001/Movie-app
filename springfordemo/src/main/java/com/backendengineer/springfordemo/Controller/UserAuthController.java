package com.backendengineer.springfordemo.Controller;

import com.backendengineer.springfordemo.Entity.User;
import com.backendengineer.springfordemo.Models.*;
import com.backendengineer.springfordemo.Service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class UserAuthController {
    private final UserService userService;

    @PostMapping("/user/register")
    public UserAuthenticateResponse Register(@RequestBody UserRegisterrequest request) {
        log.info("=====registering user: {}", request);
        return userService.Register(request);
    }

    @PostMapping("/user/authenticate")
    public UserAuthenticateResponse Authenticate(@RequestBody UserAuthenticaterequest request) {
        log.info("=====authenticating user: {}", request);
        return userService.Authenticate(request);
    }

    @GetMapping("/user/profile/{email}")
    public User getuserprofile(@PathVariable("email") String email){
        log.info("=====getting user: {}", email);
        return userService.getuserprofile(email);    }
}
