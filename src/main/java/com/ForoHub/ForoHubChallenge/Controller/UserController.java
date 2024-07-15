package com.ForoHub.ForoHubChallenge.Controller;

import com.ForoHub.ForoHubChallenge.model.User.DataNewUser;
import com.ForoHub.ForoHubChallenge.model.User.UserEntity;
import com.ForoHub.ForoHubChallenge.model.User.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Users", description = "Endpoints para la gestion de usuario")
@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/register")
    @Operation(summary = "Registrar un nuevo usuario", description = "Registrar un nuevo usuario en el sistema")
    public ResponseEntity<UserEntity> registerUser(
            @Parameter(description = "Datos del nuevo usuario a registrar", required = true)
            @Valid @RequestBody DataNewUser dataNewUser){
        UserEntity userEntity = userService.registerNewUser(dataNewUser);
        return ResponseEntity.status(HttpStatus.CREATED).body(userEntity);
    }
}
