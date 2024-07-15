package com.ForoHub.ForoHubChallenge.model.Topic.validations;

import com.ForoHub.ForoHubChallenge.model.Topic.ITopicRepository;
import com.ForoHub.ForoHubChallenge.model.Topic.TopicDto;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ActiveTopic implements ITopicValidator{
    @Autowired
    private ITopicRepository topicRepository;
    @Override
    public void validate(TopicDto data) {
        boolean activeTopic = topicRepository.existsByTitleAndStatus(data.title(), true);
        if(activeTopic){
            throw new ValidationException("A topic with the title '"+ data.title() + "' already exists and is active.");
        }

    }
}
