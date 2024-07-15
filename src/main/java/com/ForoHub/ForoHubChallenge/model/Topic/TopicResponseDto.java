package com.ForoHub.ForoHubChallenge.model.Topic;

import com.ForoHub.ForoHubChallenge.model.Response.ResponseDto;

import java.time.LocalDateTime;
import java.util.List;

public record TopicResponseDto (
        Long topic_id,
        String content,
        String authorName,
        String courseName,
        LocalDateTime creationDate,
        List<ResponseDto> listResponses
){
    public TopicResponseDto(TopicEntity topicEntity, List<ResponseDto> responseDtos){
        this(
                topicEntity.getId(),
                topicEntity.getContent(),
                topicEntity.getAuthor().getUsername(),
                topicEntity.getCourse().getName(),
                topicEntity.getCreationDate(),
                responseDtos
                );
    }
}
