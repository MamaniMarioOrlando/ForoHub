package com.ForoHub.ForoHubChallenge.model.Response;



import java.time.LocalDateTime;

public record ResponseDto(
        Long id,
        String authorName,
        String topicName,
        String content,
        LocalDateTime creationDate
) {
    public ResponseDto(ResponseEntidad responseEntidad){
        this(
                responseEntidad.getId(),
                responseEntidad.getAuthor().getUsername(),
                responseEntidad.getTopicEntity().getTitle(),
                responseEntidad.getContent(),
                responseEntidad.getCreatioDate()
        );
    }
}
