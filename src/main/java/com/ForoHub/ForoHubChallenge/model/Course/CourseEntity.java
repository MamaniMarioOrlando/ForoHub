package com.ForoHub.ForoHubChallenge.model.Course;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "courses")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CourseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Enumerated(EnumType.STRING)
    private CategoryEnum category;

    public CourseEntity(CourseDto courseDto){
        this.name = courseDto.name();
        this.category = courseDto.categoryEnum();
    }
    public CourseEntity upDateCourse(CourseDto courseDto){
        this.name = courseDto.name();
        this.category = courseDto.categoryEnum();
        return this;
    }
}
