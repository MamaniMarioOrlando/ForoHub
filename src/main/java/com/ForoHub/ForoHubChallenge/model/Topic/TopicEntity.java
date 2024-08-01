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

    @ManyToOne
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
        this.author = user;
    }

    /*public void updateTopic(UpdateTopicDto updateTopicDto){
        if(updateTopicDto.title() != null){
            this.title = updateTopicDto.title();
        }
        if(updateTopicDto.courseName() != null){
            this.course = course;
        }
        if(updateTopicDto.content() != null){
            this.content = updateTopicDto.content();
        }
        this.setCreationDate(LocalDateTime.now());
    }*/
    public void updateTopic(UpdateTopicDto updateTopicDto, CourseEntity newCourse) {
        if(updateTopicDto.title() != null){
            this.title = updateTopicDto.title();
        }
        if(updateTopicDto.courseName() != null && newCourse != null) {
            this.course = newCourse; // Asignar el curso encontrado o proporcionado
        }
        if(updateTopicDto.content() != null){
            this.content = updateTopicDto.content();
        }
        // Actualizar la fecha de última modificación en lugar de la fecha de creación
        this.creationDate = LocalDateTime.now(); // Considera agregar un campo 'lastUpdated'
    }
    public void desactivateTopic(){
        this.status = false;
    }
}
