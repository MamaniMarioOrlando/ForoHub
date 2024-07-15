package com.ForoHub.ForoHubChallenge.model.Topic;

import com.ForoHub.ForoHubChallenge.model.Response.ResponseDto;

import java.time.LocalDateTime;
import java.util.List;

public record ListOfTopicDto(
        Long id,
        String title,
        String content,
        LocalDateTime creationDate,
        String authorName,
        String courseName,
        List<ResponseDto> listResponses
) {
    public ListOfTopicDto(TopicEntity topicEntity, List<ResponseDto> responseDtos){
        this(
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
