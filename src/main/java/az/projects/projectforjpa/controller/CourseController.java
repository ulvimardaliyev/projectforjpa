package az.projects.projectforjpa.controller;

import az.projects.projectforjpa.dto.requestdto.CourseRequestDto;
import az.projects.projectforjpa.dto.responsedto.CourseResponseDto;
import az.projects.projectforjpa.service.ProjectForJpaService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("api")
public class CourseController {

    private final ProjectForJpaService service;

    @GetMapping("/students/courses")
    public List<CourseResponseDto> getAllCourses() {
        return service.getAllCourses();
    }


    @PostMapping("/students/{id}/courses")
    public long saveCourse(@RequestBody CourseRequestDto courseRequestDto,
                           @PathVariable Long id) {
        return service.saveCourse(courseRequestDto, id);
    }

    @GetMapping("/students/{id}/courses")
    public CourseResponseDto courseResponseDto(@PathVariable long id) {
        return service.getCourseById(id);
    }

    //delete course
    @DeleteMapping("/students/courses/{id}")
    public void deleteCourse(@PathVariable long id) {
        service.deleteCourse(id);
    }
    //delete specified course of specified student /students/{id}/courses/{id}
}
