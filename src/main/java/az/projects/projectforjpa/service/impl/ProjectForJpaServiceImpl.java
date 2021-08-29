package az.projects.projectforjpa.service.impl;

import az.projects.projectforjpa.dao.entity.Course;
import az.projects.projectforjpa.dao.entity.Student;
import az.projects.projectforjpa.dao.repository.CourseRepository;
import az.projects.projectforjpa.dao.repository.StudentRepository;
import az.projects.projectforjpa.dto.requestdto.CourseRequestDto;
import az.projects.projectforjpa.dto.requestdto.StudentRequestDto;
import az.projects.projectforjpa.dto.responsedto.CourseResponseDto;
import az.projects.projectforjpa.dto.responsedto.StudentResponseDto;
import az.projects.projectforjpa.service.ProjectForJpaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProjectForJpaServiceImpl implements ProjectForJpaService {

    private final CourseRepository courseRepository;
    private final StudentRepository studentRepository;

    @Override
    public List<CourseResponseDto> getAllCourses() {
        var allCourses = courseRepository.findAll();
        List<CourseResponseDto> courseResponseDto = new ArrayList<>();
        for (Course course : allCourses) {
            courseResponseDto.add(CourseResponseDto.builder()
                    .student(course.getStudent())
                    .id(course.getId())
                    .courseName(course.getCourseName())
                    .build());
        }
        return courseResponseDto;
    }

    @Override
    public List<StudentResponseDto> getAllStudents() {
        var allStudents = studentRepository.findAll();
        List<StudentResponseDto> studentResponseDto = new ArrayList<>();
        for (Student student : allStudents
        ) {
            studentResponseDto.add(StudentResponseDto.builder()
                    .age(student.getAge())
                    .id(student.getId())
                    .studentName(student.getStudentName())
                    .course(student.getCourse())
                    .build());
        }
        return studentResponseDto;
    }

    //Sehv isleyir duzelt
    @Override
    public StudentResponseDto getStudentById(long id) {
        var studentById = studentRepository.findById(id);
        StudentResponseDto studentResponseDto =
                StudentResponseDto
                        .builder()
                        .id(studentById.get().getId())
                        .age(studentById.get().getAge())
                        .studentName(studentById.get().getStudentName())
                        .course(studentById.get().getCourse())
                        .build();
        return studentResponseDto;
    }

    @Override
    public List<CourseResponseDto> getCourseResponseDto(long id) {
        var studentById = studentRepository.findById(id).get();
        var listOfStudentCoursesById = studentById.getCourse();
        var listOfStudentCourses = new ArrayList<CourseResponseDto>();
        for (Course course : listOfStudentCoursesById) {
            listOfStudentCourses.add(
                    CourseResponseDto
                            .builder()
                            .courseName(course.getCourseName())
                            .student(course.getStudent())
                            .id(course.getId())
                            .build());
        }
        return listOfStudentCourses;
    }

    @Override
    public long saveStudent(StudentRequestDto studentRequestDto) {
        Student student = Student.builder()
                .studentName(studentRequestDto.getStudentName())
                .age(studentRequestDto.getAge())
                .course(studentRequestDto.getCourse())
                .build();
        return studentRepository.save(student).getId();
    }

    @Override
    public long saveCourse(CourseRequestDto courseRequestDto, Long id) {
        var student = studentRepository.findById(id);

        Course course = Course.builder()
                .courseName(courseRequestDto.getCourseName())
                .build();
        course.setStudent(student.get());
        student.get().getCourse().add(course);
        var num = courseRepository.save(course).getId();
        return num;
    }

    @Override
    public void deleteStudent(long id) {
        var entity = studentRepository.findById(id);
        var listOfCoursesForThisStudent = entity.get().getCourse();

        for (Course course : listOfCoursesForThisStudent) {
            deleteCourse(course.getId());
        }
        studentRepository.delete(entity.get());

    }

    @Override
    public void deleteCourse(long id) {
        courseRepository.delete(courseRepository.findById(id).get());
    }
}
