package az.projects.projectforjpa.controller;

import az.projects.projectforjpa.dto.requestdto.TeacherRequestDto;
import az.projects.projectforjpa.dto.responsedto.TeacherResponseDto;
import az.projects.projectforjpa.service.ProjectForJpaService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class TeacherController {

    final private ProjectForJpaService jpaService;

    @PostMapping("/teacher")
    public long saveTeacher(@RequestBody TeacherRequestDto teacherRequestDto) {
        return jpaService.saveTeacher(teacherRequestDto);
    }
    @GetMapping("/teachers")
    public List<TeacherResponseDto> teacherResponseDto() {
        return jpaService.getAllTeacher();
    }
    @DeleteMapping("/teachers/{teacherId}")
    public void deleteTeacher(@PathVariable long teacherId){
        jpaService.deleteTeacher(teacherId);
    }

    @GetMapping("/teachers/{teacherId}")
    public TeacherResponseDto teacherResponseDto(@PathVariable long teacherId){
        return jpaService.getTeacherById(teacherId);
    }
}
