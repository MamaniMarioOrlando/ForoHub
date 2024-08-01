package com.ForoHub.ForoHubChallenge.service;

import com.ForoHub.ForoHubChallenge.model.Course.CourseEntity;
import com.ForoHub.ForoHubChallenge.model.Course.ICourseRepository;
import com.ForoHub.ForoHubChallenge.model.User.IUserRepository;
import com.ForoHub.ForoHubChallenge.model.User.UserEntity;
import com.ForoHub.ForoHubChallenge.model.UserCourse.UserAndCourseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserCourseService {
    @Autowired
    private IUserRepository userRepository;
    @Autowired
    private ICourseRepository courseRepository;

    public void addCourseToUser(UserAndCourseDto userAndCourseDto){
        Optional<UserEntity> userOptional = userRepository.findById(userAndCourseDto.userId());
        Optional<CourseEntity> courseOptional = courseRepository.findById(userAndCourseDto.courseId());

        if (userOptional.isPresent() && courseOptional.isPresent()){
            UserEntity user = userOptional.get();
            CourseEntity course = courseOptional.get();

            user.getCourses().add(course);
            userRepository.save(user);
        }else{
            throw new RuntimeException("Usuario o curso no es encontrado.");
        }

    }
}
