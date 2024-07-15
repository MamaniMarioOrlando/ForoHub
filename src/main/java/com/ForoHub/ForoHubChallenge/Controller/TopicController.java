package com.ForoHub.ForoHubChallenge.Controller;

import com.ForoHub.ForoHubChallenge.infra.errors.ResourcedNotFoundException;
import com.ForoHub.ForoHubChallenge.model.Topic.*;
import com.ForoHub.ForoHubChallenge.utils.TopicUtils;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/topics")
@SecurityRequirement(name = "barer-key")
public class TopicController {
    @Autowired
    private ITopicRepository topicRepository;
    @Autowired
    private TopicService topicService;
    @Autowired
    private TopicUtils topicUtils;

    @GetMapping
    public ResponseEntity<Page<ListOfTopicDto>> topicLIst(@PageableDefault(size = 4) Pageable pageable){
        return ResponseEntity.ok(topicRepository.findAllByStatusTrueOrderByCreationDateAsc(pageable)
                .map(topicUtils::mapToListOfTopics));
    }
    @GetMapping("/course/{courseName}")
    public ResponseEntity<Page<ListOfTopicDto>> listTopicByCourse(@PathVariable String courseName, Pageable pageable){
        Page<TopicEntity> topicPage = topicRepository.findAllByCourseNameIgnoreCase(courseName, pageable);
        Page<ListOfTopicDto> listOfTopicDtoPage = topicPage.map(topicUtils::mapToListOfTopics);
        return ResponseEntity.ok(listOfTopicDtoPage);
    }
    @GetMapping("/{id}")
    public ResponseEntity<TopicResponseDto> topicById(@PathVariable Long id) throws ResourcedNotFoundException{
        TopicEntity topicEntity = topicRepository.findById(id)
                .orElseThrow(() -> new ResourcedNotFoundException("Topic","id", id));
        TopicResponseDto topicResponseDto = topicUtils.topicWithResponses(topicEntity);
        return ResponseEntity.ok(topicResponseDto);
    }
    @PutMapping("/update/{id}")
    @Transactional
    public ResponseEntity<TopicResponseDto> updateTopic(@PathVariable Long id, @RequestBody @Valid UpdateTopicDto updateTopicDto){
        TopicEntity topicEntity = topicRepository.findById(id)
                .orElseThrow(() -> new ResourcedNotFoundException("Topic","id",id));
        topicEntity.updateTopic(updateTopicDto);

        topicRepository.save(topicEntity);
        TopicResponseDto topicResponseDto = topicUtils.topicWithResponses(topicEntity);
        return ResponseEntity.ok(topicResponseDto);
    }
    @PostMapping
    @Transactional
    public ResponseEntity<TopicResponseDto> createTopic(@RequestBody @Valid TopicDto topicDto){
        TopicEntity topicEntity = topicService.create(topicDto);
        TopicResponseDto topicResponseDto = topicUtils.topicWithResponses(topicEntity);
        return ResponseEntity.ok(topicResponseDto);
    }
    @DeleteMapping("/delete/{id}")
    @Transactional
    public ResponseEntity deleteTopic(@PathVariable Long id){
        TopicEntity topicEntity = topicRepository.findById(id)
                .orElseThrow(() -> new ResourcedNotFoundException("Topic","id",id));
        topicEntity.desactivateTopic();
        return ResponseEntity.noContent().build();
    }
}
