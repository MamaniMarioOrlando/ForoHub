package com.ForoHub.ForoHubChallenge.model.Response;

import com.ForoHub.ForoHubChallenge.model.Topic.ITopicRepository;
import com.ForoHub.ForoHubChallenge.model.Topic.TopicEntity;
import com.ForoHub.ForoHubChallenge.model.Topic.validations.ITopicValidator;
import com.ForoHub.ForoHubChallenge.model.User.IUserRepository;
import com.ForoHub.ForoHubChallenge.model.User.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ResponseService {
    @Autowired
    private IResponseRepository responseRepository;
    @Autowired
    private ITopicRepository topicRepository;
    @Autowired
    private IUserRepository userRepository;
    @Autowired
    private List<ITopicValidator> validators;

    public ResponseDto create(CreateResponseDto createResponseDto){
        Optional<UserEntity> userOptional = userRepository.findById(createResponseDto.idUser());
        if(userOptional.isEmpty()){
            throw new RuntimeException(("user not foun"));
        }
        UserEntity user = userOptional.get();

        Optional<TopicEntity> optionalTopic = topicRepository.findById(createResponseDto.idTopic());
        if(optionalTopic.isEmpty()){
            throw new RuntimeException("Topic not found!");
        }
        TopicEntity topic = optionalTopic.get();
        ResponseEntidad response =new ResponseEntidad(createResponseDto,user,topic);
        responseRepository.save(response);
        return new ResponseDto(
                response.getId(),
                response.getAuthor().getUsername(),
                response.getTopicEntity().getTitle(),
                response.getContent(),
                response.getCreatioDate()
                );
    }
}
