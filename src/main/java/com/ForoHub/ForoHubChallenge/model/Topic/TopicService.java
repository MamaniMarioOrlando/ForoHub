package com.ForoHub.ForoHubChallenge.model.Topic;

import com.ForoHub.ForoHubChallenge.model.Course.CourseEntity;
import com.ForoHub.ForoHubChallenge.model.Course.ICourseRepository;
import com.ForoHub.ForoHubChallenge.model.Topic.validations.ITopicValidator;
import com.ForoHub.ForoHubChallenge.model.User.IUserRepository;
import com.ForoHub.ForoHubChallenge.model.User.UserEntity;
import com.ForoHub.ForoHubChallenge.utils.TopicUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TopicService {
    @Autowired
    private ITopicRepository topicRepository;
    @Autowired
    private ICourseRepository courseRepository;
    @Autowired
    private IUserRepository userRepository;
    @Autowired
    private TopicUtils topicUtils;
    @Autowired
    private List<ITopicValidator> validatorList;

    public TopicEntity create(TopicDto topicDto){
        Optional<UserEntity> userOptional = userRepository.findById(topicDto.id_user());
        if(userOptional.isEmpty()){
            throw new RuntimeException("User not found");
        }
        UserEntity user = userOptional.get();

        CourseEntity courseEntity = courseRepository.findByName(topicDto.curseName());
        if(courseEntity == null){
            throw new RuntimeException("Course not found!");
        }
        validatorList.forEach(v -> v.validate(topicDto));
        TopicEntity topicEntity = new TopicEntity(topicDto, user, courseEntity);
        return topicRepository.save(topicEntity);
    }

}
