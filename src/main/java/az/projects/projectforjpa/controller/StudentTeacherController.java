package az.projects.projectforjpa.controller;

import az.projects.projectforjpa.dao.entity.Student;
import az.projects.projectforjpa.dto.responsedto.StudentResponseDto;
import az.projects.projectforjpa.dto.responsedto.TeacherResponseDto;
import az.projects.projectforjpa.service.ProjectForJpaService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

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

    //works correctly
    @DeleteMapping("/students/{studentId}/teachers/{teacherId}")
    public StudentResponseDto deleteTeacherFromStudent(
            @PathVariable long studentId,
            @PathVariable long teacherId) {
        return service.deleteTeacherFromStudent(studentId, teacherId);
    }

    //works correctly
    @DeleteMapping("/teachers/{teacherId}/students/{studentId}")
    public TeacherResponseDto deleteStudentFromTeacher(
            @PathVariable long teacherId,
            @PathVariable long studentId) {
        return service.deleteStudentFromTeacher(teacherId, studentId);
    }
}
