package com.ForoHub.ForoHubChallenge.model.Response;


import com.ForoHub.ForoHubChallenge.model.Topic.TopicEntity;
import com.ForoHub.ForoHubChallenge.model.User.UserEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity(name = "Response")
@Table(name = "responses")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseEntidad {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String content;
    
    @ManyToOne
    @JoinColumn(name = "topic_id")
    private TopicEntity topicEntity;
    private LocalDateTime creationDate;
    @ManyToOne
    @JoinColumn(name = "author_id")
    private UserEntity author;
    private Boolean solution;

    public ResponseEntidad(CreateResponseDto createResponseDto, UserEntity author, TopicEntity topicEntity){
        this.content = createResponseDto.content();
        this.topicEntity = topicEntity;
        this.creationDate = LocalDateTime.now();
        this.author = author;
        this.solution = true;
    }
    public void solutionResponse(){
        this.solution = false;
    }
    public void updateResponse(DataUpdateResponseDto data){
        if(data.idTopic() != null){
            this.topicEntity = topicEntity;
        }
        if(data.content() != null){
            this.content = data.content();
        }
        this.setCreationDate(LocalDateTime.now());
    }
}
