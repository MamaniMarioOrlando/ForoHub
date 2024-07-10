package com.ForoHub.ForoHubChallenge.model.Course;

public record ResponseCourseDto(
        Long id,
        String name,
        CategoryEnum categoryEnum
) {
}
