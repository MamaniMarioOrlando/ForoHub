package com.ForoHub.ForoHubChallenge.model.Course;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CourseDto(
        @NotBlank
        String name,
        @NotNull
        CategoryEnum categoryEnum
) {
}
