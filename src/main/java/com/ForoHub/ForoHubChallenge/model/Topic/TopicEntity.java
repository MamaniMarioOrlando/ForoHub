package com.ForoHub.ForoHubChallenge.model.Topic;

import com.ForoHub.ForoHubChallenge.model.Course.CourseEntity;
import com.ForoHub.ForoHubChallenge.model.Response.ResponseEntidad;
import com.ForoHub.ForoHubChallenge.model.User.UserEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Entity(name = "TopicEntity")
@Table(name = "topics")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TopicEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String content;
    private LocalDateTime creationDate;
    private Boolean status;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity author;

    @ManyToMany
    @JoinColumn(name = "curse_id")
    private CourseEntity course;
    @OneToMany(mappedBy = "topicEntity")
    private List<ResponseEntidad> responses;

    public TopicEntity(TopicDto topicDto, UserEntity user, CourseEntity course){
        this.status = true;
        this.title = topicDto.title();
        this.course = course;
        this.content = topicDto.content();
        this.creationDate = LocalDateTime.now();
        this.author = author;
    }

    public void updateTopic(UpdateTopicDto updateTopicDto){
        if(updateTopicDto.title() != null){
            this.title = updateTopicDto.title();
        }
        if(updateTopicDto.couseName() != null){
            this.course = course;
        }
        if(updateTopicDto.content() != null){
            this.content = updateTopicDto.content();
        }
        this.setCreationDate(LocalDateTime.now());
    }
}
