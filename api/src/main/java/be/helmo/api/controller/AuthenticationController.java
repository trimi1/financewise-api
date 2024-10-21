package be.helmo.api.controller;

import be.helmo.api.dto.LoginUserDto;
import be.helmo.api.dto.RegisterUserDto;
import be.helmo.api.security.AuthenticationResponse;
import be.helmo.api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/financewise/auth")
public class AuthenticationController {

    private final UserService userService;

    @Autowired
    public AuthenticationController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterUserDto request) {
        return ResponseEntity.ok(userService.register(request));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody LoginUserDto request) {
        return ResponseEntity.ok(userService.authenticate(request));
    }
}
