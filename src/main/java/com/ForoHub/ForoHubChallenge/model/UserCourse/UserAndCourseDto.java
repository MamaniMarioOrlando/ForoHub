package com.ForoHub.ForoHubChallenge.model.UserCourse;

import jakarta.validation.constraints.NotNull;

public record UserAndCourseDto(
        @NotNull(message = "El ID de usuario es obligatorio.")
        Long userId,
        @NotNull(message = "El Id del curso es obligatorio")
        Long courseId
        ) {
}
