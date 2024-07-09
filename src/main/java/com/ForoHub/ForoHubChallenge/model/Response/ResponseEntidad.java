package com.ForoHub.ForoHubChallenge.model.Response;


import com.ForoHub.ForoHubChallenge.model.Topic.TopicEntity;
import com.ForoHub.ForoHubChallenge.model.User.UserEntity;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity(name = "Response")
@Table(name = "responses")
@Data
@NoArgsConstructor

public class ResponseEntidad {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String content;
    
    @ManyToOne
    @JoinColumn(name = "topic_id")
    private TopicEntity topicEntity;
    private LocalDate creatioDate;
    @ManyToOne
    @JoinColumn(name = "author_id")
    private UserEntity author;
    private Boolean solution;
}
