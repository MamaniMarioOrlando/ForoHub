package com.ForoHub.ForoHubChallenge.model.Course;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ICourseRepository extends JpaRepository<CourseEntity, Long> {
    CourseEntity findByName(String name);
}
