package com.ForoHub.ForoHubChallenge.model.Course;

public record ListOfCoursesDto(
        Long idCourse,
        String name,
        CategoryEnum categoryEnum
) {
    public ListOfCoursesDto(CourseEntity courseEntity){
        this(
                courseEntity.getId(),
                courseEntity.getName(),
                courseEntity.getCategory()
        );
    }
}
