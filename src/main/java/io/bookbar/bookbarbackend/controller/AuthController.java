package io.bookbar.bookbarbackend.controller;


import io.bookbar.bookbarbackend.config.auth.TokenProvider;
import io.bookbar.bookbarbackend.dto.JwtDto;
import io.bookbar.bookbarbackend.dto.SignInDto;
import io.bookbar.bookbarbackend.dto.SignUpDto;
import io.bookbar.bookbarbackend.entities.User;
import io.bookbar.bookbarbackend.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private AuthService service;
    @Autowired
    private TokenProvider tokenService;

    @PostMapping("/signup")
    public ResponseEntity<?> signUp(@RequestBody SignUpDto data) {
        service.signUp(data);
        return ResponseEntity.status(HttpStatus.CREATED).build();

    }

    @PostMapping("/login")
    public ResponseEntity<JwtDto> signIn(@RequestBody SignInDto data) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.username(), data.password());

        var authUser = authenticationManager.authenticate(usernamePassword);

        var accessToken = tokenService.generateAccessToken((User) authUser.getPrincipal());

        return ResponseEntity.ok(new JwtDto(accessToken));
    }

}
