package com.ForoHub.ForoHubChallenge.model.Topic;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record TopicDto(
        @NotNull
        Long id_user,
        @NotBlank
        String title,
        @NotBlank
        @Valid
        String curseName,
        @NotBlank
        String content
) {
    public TopicDto(TopicEntity topic){
        this(topic.getAuthor().getId(),topic.getTitle(), topic.getCourse().getName(), topic.getContent());
    }
}
