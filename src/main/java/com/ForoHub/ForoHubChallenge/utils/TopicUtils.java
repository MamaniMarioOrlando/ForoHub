package com.ForoHub.ForoHubChallenge.utils;

import com.ForoHub.ForoHubChallenge.model.Response.ResponseDto;
import com.ForoHub.ForoHubChallenge.model.Topic.ListOfTopicDto;
import com.ForoHub.ForoHubChallenge.model.Topic.TopicDto;
import com.ForoHub.ForoHubChallenge.model.Topic.TopicEntity;
import com.ForoHub.ForoHubChallenge.model.Topic.TopicResponseDto;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class TopicUtils {
    public TopicResponseDto topicWithResponses(TopicEntity topicEntity){
        List<ResponseDto> responseDtoList = (topicEntity.getResponses() != null) ?
                topicEntity.getResponses().stream()
                        .map(response -> new ResponseDto(
                           response.getId(),
                           response.getAuthor().getUsername(),
                           topicEntity.getTitle(),
                           response.getContent(),
                           response.getCreationDate()
                        ))
                        .collect(Collectors.toList()) : Collections.emptyList();
        return new TopicResponseDto(
                topicEntity.getId(),
                topicEntity.getContent(),
                topicEntity.getAuthor().getUsername(),
                topicEntity.getCourse().getName(),
                topicEntity.getCreationDate(),
                responseDtoList
        );
    }
    public ListOfTopicDto mapToListOfTopics(TopicEntity topicEntity){
        List<ResponseDto> responseDtos = (topicEntity.getResponses() != null) ?
                topicEntity.getResponses().stream()
                        .map(response -> new ResponseDto(
                                response.getId(),
                                response.getAuthor().getUsername(),
                                topicEntity.getTitle(),
                                response.getContent(),
                                response.getCreationDate()
                        ))
                        .collect(Collectors.toList()) : Collections.emptyList();
        return new ListOfTopicDto(
                topicEntity.getId(),
                topicEntity.getTitle(),
                topicEntity.getContent(),
                topicEntity.getCreationDate(),
                topicEntity.getAuthor().getUsername(),
                topicEntity.getCourse().getName(),
                responseDtos
        );
    }
}
