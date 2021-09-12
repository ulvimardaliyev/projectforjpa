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

    @GetMapping("/courses")
    public List<CourseResponseDto> getAllCourses() {
        return service.getAllCourses();
    }


    @PostMapping("/courses")
    public long saveCourse(@RequestBody CourseRequestDto courseRequestDto) {
        return service.saveCourse(courseRequestDto);
    }

    @GetMapping("/courses/{courseId}")
    public CourseResponseDto courseResponseDto(@PathVariable long courseId) {
        return service.getCourseResponseDto(courseId);
    }

    //delete course
    @DeleteMapping("/courses/{courseId}")
    public void deleteCourse(@PathVariable long courseId) {
        service.deleteCourse(courseId);
    }

    @PutMapping("/students/{studentId}/courses/{courseId}")
    public CourseResponseDto addCourseToStudent(@PathVariable long studentId,
                                                @PathVariable long courseId) {
        return service.addCourseToStudent(studentId, courseId);
    }

    /**
     * TODO correct this controller, because I do not know how to save after deleting chosen (by id) course
     * TODO action : delete specified course of specified student /students/{id}/courses/{id}
     */
    @DeleteMapping("/students/{studentId}/courses/{courseId}")
    public void deleteCourseWithId(@PathVariable long studentId,
                                   @PathVariable long courseId) {
        service.deleteCourseWithId(studentId, courseId);
    }
}
