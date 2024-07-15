package com.ForoHub.ForoHubChallenge.Controller;

import com.ForoHub.ForoHubChallenge.infra.security.JWTTokenDto;
import com.ForoHub.ForoHubChallenge.infra.security.TokenService;
import com.ForoHub.ForoHubChallenge.model.User.UserEntity;
import com.ForoHub.ForoHubChallenge.model.User.UserLoginDto;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")

public class AuthConroller {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity authenticationUser(@RequestBody @Valid UserLoginDto userLoginDto){
        Authentication authToken = new UsernamePasswordAuthenticationToken(
                userLoginDto.username(),
                userLoginDto.password()
        );
        var authenticatedUser = authenticationManager.authenticate(authToken);
        var JWTtoken = tokenService.generateToken((UserEntity) authenticatedUser.getPrincipal());
        return ResponseEntity.ok(new JWTTokenDto(JWTtoken));
    }
}
