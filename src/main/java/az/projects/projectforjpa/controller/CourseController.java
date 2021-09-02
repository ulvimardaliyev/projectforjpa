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
    public List<CourseResponseDto> courseResponseDto(@PathVariable long id) {
        return service.getCourseResponseDto(id);
    }

    //delete course
    @DeleteMapping("/students/courses/{id}")
    public void deleteCourse(@PathVariable long id) {
        service.deleteCourse(id);
    }


    // correct this controller, because I do not know how to save after deleting chosen (by id) course
    // action : delete specified course of specified student /students/{id}/courses/{id}
    @DeleteMapping("/students/{studentId}/courses/{courseId}")
    public void deleteCourseWithId(@PathVariable long studentId,
                                                      @PathVariable long courseId){
         service.deleteCourseWithId(studentId, courseId);
    }
}
