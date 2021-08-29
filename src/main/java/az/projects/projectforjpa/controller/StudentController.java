package az.projects.projectforjpa.controller;

import az.projects.projectforjpa.dto.requestdto.StudentRequestDto;
import az.projects.projectforjpa.dto.responsedto.StudentResponseDto;
import az.projects.projectforjpa.service.ProjectForJpaService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class StudentController {


    private final ProjectForJpaService projectForJpaService;

    @GetMapping("/students")
    public List<StudentResponseDto> studentResponseDtoList() {
        return projectForJpaService.getAllStudents();
    }

    @PostMapping("/students")
    public long saveStudent(@RequestBody StudentRequestDto requestDto) {
        return projectForJpaService.saveStudent(requestDto);
    }

    @GetMapping("/students/{id}")
    public StudentResponseDto studentResponseDto(@PathVariable long id) {
        return projectForJpaService.getStudentById(id);

    }

    //delete students
    //butun telebeleri silir amma course-lari silmemelidi mence
    @DeleteMapping("/students/{id}")
    public void deleteStudent(@PathVariable long id) {
        projectForJpaService.deleteStudent(id);
    }
}
