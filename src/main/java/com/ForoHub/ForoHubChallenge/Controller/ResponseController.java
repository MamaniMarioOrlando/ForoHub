package com.ForoHub.ForoHubChallenge.Controller;

import com.ForoHub.ForoHubChallenge.infra.errors.ResourcedNotFoundException;
import com.ForoHub.ForoHubChallenge.model.Response.*;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/responses")
@SecurityRequirement(name = "bearer-key")
public class ResponseController {
    @Autowired
    private IResponseRepository responseRepository;
    @Autowired
    private ResponseService responseService;

    @PostMapping
    @Transactional
    public ResponseEntity<ResponseDto> createResponse(@RequestBody @Valid CreateResponseDto createResponseDto){
        ResponseDto responseDto = responseService.create(createResponseDto);
        return ResponseEntity.ok(responseDto);
    }

    @PutMapping("/{idResponse}")
    @Transactional
    public ResponseEntity<ResponseDto> updateResponse(@RequestBody @Valid Long idResponse, @RequestBody @Valid DataUpdateResponseDto data){
        ResponseEntidad responseEntidad = responseRepository.findById(idResponse)
                .orElseThrow(() -> new ResourcedNotFoundException("Response","id",idResponse));
        responseEntidad.updateResponse(data);
        responseRepository.save(responseEntidad);
        return ResponseEntity.ok(new ResponseDto(
                responseEntidad.getId(),
                responseEntidad.getAuthor().getUsername(),
                responseEntidad.getTopicEntity().getTitle(),
                responseEntidad.getContent(),
                responseEntidad.getCreationDate()
                ));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity deleteResponse(@PathVariable Long id){
        responseRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
