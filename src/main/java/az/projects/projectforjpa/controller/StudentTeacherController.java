package az.projects.projectforjpa.controller;

import az.projects.projectforjpa.dao.entity.Student;
import az.projects.projectforjpa.dto.responsedto.TeacherResponseDto;
import az.projects.projectforjpa.service.ProjectForJpaService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("api")
public class StudentTeacherController {

    private final ProjectForJpaService service;

    @PutMapping("/students/{studentId}/teachers/{teacherId}")
    public Student studentResponseDto(
            @PathVariable long studentId,
            @PathVariable long teacherId) {
        return service.addTeacherById(teacherId, studentId);
    }

    @PutMapping("/teachers/{teacherId}/students/{studentId}")
    public TeacherResponseDto addStudentById(
            @PathVariable long teacherId,
            @PathVariable long studentId) {
        return service.addStudentById(teacherId, studentId);
    }

    //TODO create a controller for the following requests
    //TODO create a service method for the following requests
    //@DeleteMapping("/students/{studentId}/teachers/{teacherId}")
    //@DeleteMapping("/teachers/{teacherId}/students/{studentId}")
}
