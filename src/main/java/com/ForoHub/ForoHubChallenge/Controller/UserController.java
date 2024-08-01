package com.ForoHub.ForoHubChallenge.Controller;

import com.ForoHub.ForoHubChallenge.model.User.DataNewUser;
import com.ForoHub.ForoHubChallenge.model.User.UserEntity;
import com.ForoHub.ForoHubChallenge.model.UserCourse.UserAndCourseDto;
import com.ForoHub.ForoHubChallenge.service.UserCourseService;
import com.ForoHub.ForoHubChallenge.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Usuarios", description = "Endpoints para la gestión de usuarios, incluyendo registro y consulta.")
@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private UserCourseService userCourseService;

    /**
     * Registra un nuevo usuario.
     *
     * @param dataNewUser Datos del nuevo usuario a registrar.
     * @return ResponseEntity<UserEntity> con los detalles del usuario registrado.
     */
    @PostMapping("/register")
    @Operation(summary = "Registrar un nuevo usuario",
            description = "Registra un nuevo usuario en el sistema.")
    public ResponseEntity<UserEntity> registerUser(
            @Parameter(description = "Datos del nuevo usuario a registrar", required = true)
            @Valid @RequestBody DataNewUser dataNewUser){
        UserEntity userEntity = userService.registerNewUser(dataNewUser);
        return ResponseEntity.status(HttpStatus.CREATED).body(userEntity);
    }


    /**
     * Registra un nuevo usuario.
     *
     * @param userAndCourseDto Datos del curso a agregar.
     * @return ResponseEntity<Void> con con un estado ok.
     */
    @PostMapping("/addCourse")
    @Operation(summary = "Registra a los usuarios y sus cursos",
            description = "Registra a un usuario y su curso.")
    public ResponseEntity<Void> addCourseToUser(
            @Parameter(description = "el idUser y idCourse a registrar", required = true)
            @Valid @RequestBody UserAndCourseDto userAndCourseDto){
        userCourseService.addCourseToUser(userAndCourseDto);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
