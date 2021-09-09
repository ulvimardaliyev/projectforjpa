package az.projects.projectforjpa.service;

import az.projects.projectforjpa.dao.entity.Student;
import az.projects.projectforjpa.dao.entity.Teacher;
import az.projects.projectforjpa.dto.requestdto.CourseRequestDto;
import az.projects.projectforjpa.dto.requestdto.StudentRequestDto;
import az.projects.projectforjpa.dto.requestdto.TeacherRequestDto;
import az.projects.projectforjpa.dto.responsedto.CourseResponseDto;
import az.projects.projectforjpa.dto.responsedto.StudentResponseDto;
import az.projects.projectforjpa.dto.responsedto.TeacherResponseDto;

import java.util.List;


public interface ProjectForJpaService {

    List<CourseResponseDto> getAllCourses();

    List<StudentResponseDto> getAllStudents();

    StudentResponseDto getStudentById(long id);

    List<CourseResponseDto> getCourseResponseDto(long id);

    long saveStudent(StudentRequestDto studentRequestDto);

    void deleteCourse(long id);

    void deleteStudent(long id);

    long saveCourse(CourseRequestDto courseRequestDto, Long id);

    //think again for return type of this abstract method
    void deleteCourseWithId(long studentId, long courseId);

    long saveTeacher(TeacherRequestDto teacherRequestDto);

    void deleteTeacher(long id);

    List<TeacherResponseDto> getAllTeacher();

    TeacherResponseDto getTeacherById(long id);

    //add teacher to student
    Student addTeacherById(long teacherId, long studentId);
    //add student to teacher
    TeacherResponseDto addStudentById(long teacherId, long studentId);
}
