package com.ForoHub.ForoHubChallenge.model.Response;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreateResponseDto(
        @NotNull
        Long idUser,
        @NotNull
        Long idTpic,
        @NotBlank
        String content
) {
    public CreateResponseDto(ResponseEntidad responseEntidad){
        this(   responseEntidad.getAuthor().getId(),
                responseEntidad.getTopicEntity().getId(),
                responseEntidad.getContent()
        );
    }
}
