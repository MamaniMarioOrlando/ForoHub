package com.ForoHub.ForoHubChallenge.Controller;

import com.ForoHub.ForoHubChallenge.infra.errors.ResourcedNotFoundException;
import com.ForoHub.ForoHubChallenge.model.Course.*;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.data.domain.Pageable;

import java.net.URI;

@RestController
@RequestMapping("/courses")
@SecurityRequirement(name = "bearer-key")
public class CourseController {
    @Autowired
    private ICourseRepository courseRepository;
    @PostMapping
    public ResponseEntity<ResponseCourseDto> registerCourse(@RequestBody CourseDto courseDto,
                                                            UriComponentsBuilder uriComponentsBuilder){
        CourseEntity courseEntity = courseRepository.save(new CourseEntity(courseDto));
        ResponseCourseDto responseCourseDto = new ResponseCourseDto(
                courseEntity.getId(),
                courseEntity.getName(),
                courseEntity.getCategory());
        URI url = uriComponentsBuilder.path("/courses/{id}").buildAndExpand(courseEntity.getId()).toUri();
        return ResponseEntity.created(url).body(responseCourseDto);
    }
    @GetMapping
    public ResponseEntity<Page<ListOfCoursesDto>> listOfCouse(Pageable pageable){
        return ResponseEntity.ok(courseRepository.findAll(pageable).map(ListOfCoursesDto::new));
    }
    @GetMapping("/{idCourse}")
    public ResponseEntity<ResponseCourseDto> courseForId(@PathVariable Long idCourse){
        CourseEntity courseEntity = courseRepository.getReferenceById(idCourse);
        ResponseCourseDto responseCourseDto = new ResponseCourseDto(
                courseEntity.getId(),
                courseEntity.getName(),
                courseEntity.getCategory()
        );
        return ResponseEntity.ok(responseCourseDto);
    }

    @PutMapping
    @Transactional
    public ResponseEntity updateCouse(@PathVariable Long id, @RequestBody @Valid CourseDto courseDto){
        CourseEntity courseEntity = courseRepository.findById(id)
                .orElseThrow(() -> new ResourcedNotFoundException("Course", "id", id));
        courseEntity.upDateCourse(courseDto);
        courseRepository.save(courseEntity);
        return ResponseEntity.ok(new ResponseCourseDto(
                courseEntity.getId(),
                courseEntity.getName(),
                courseEntity.getCategory()));
    }
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity deleteCourse(@PathVariable Long idCourse){
        CourseEntity courseEntity = courseRepository.getReferenceById(idCourse);
        courseRepository.deleteById(courseEntity.getId());
        return ResponseEntity.noContent().build();
    }
}
