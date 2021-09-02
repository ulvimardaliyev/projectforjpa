package az.projects.projectforjpa.service;

import az.projects.projectforjpa.dto.requestdto.CourseRequestDto;
import az.projects.projectforjpa.dto.requestdto.StudentRequestDto;
import az.projects.projectforjpa.dto.responsedto.CourseResponseDto;
import az.projects.projectforjpa.dto.responsedto.StudentResponseDto;

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

}
