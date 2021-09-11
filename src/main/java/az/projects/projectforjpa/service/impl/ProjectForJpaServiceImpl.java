package az.projects.projectforjpa.service.impl;

import az.projects.projectforjpa.dao.entity.Course;
import az.projects.projectforjpa.dao.entity.Student;
import az.projects.projectforjpa.dao.entity.Teacher;
import az.projects.projectforjpa.dao.repository.CourseRepository;
import az.projects.projectforjpa.dao.repository.StudentRepository;
import az.projects.projectforjpa.dao.repository.TeacherRepository;
import az.projects.projectforjpa.dto.requestdto.CourseRequestDto;
import az.projects.projectforjpa.dto.requestdto.StudentRequestDto;
import az.projects.projectforjpa.dto.requestdto.TeacherRequestDto;
import az.projects.projectforjpa.dto.responsedto.CourseResponseDto;
import az.projects.projectforjpa.dto.responsedto.StudentResponseDto;
import az.projects.projectforjpa.dto.responsedto.TeacherResponseDto;
import az.projects.projectforjpa.service.ProjectForJpaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Transactional
@Service
@RequiredArgsConstructor
public class ProjectForJpaServiceImpl implements ProjectForJpaService {

    private final CourseRepository courseRepository;
    private final StudentRepository studentRepository;
    private final TeacherRepository teacherRepository;

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
                    .teacher(student.getTeachers())
                    .build());
        }
        return studentResponseDto;
    }

    //Updated, works correctly
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
                        .teacher(studentById.get().getTeachers())
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

    @Transactional
    @Override
    public long saveStudent(StudentRequestDto studentRequestDto) {
        Student student = Student.builder()
                .studentName(studentRequestDto.getStudentName())
                .age(studentRequestDto.getAge())
                .course(studentRequestDto.getCourse())
                .build();

        long id = studentRepository.save(student).getId();
        student.setStudentName("Khalid");
        return id;
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

    //action needs
    @Override
    public void deleteCourseWithId(long studentId, long courseId) {
        var studentEntity = studentRepository.findById(studentId).get();
        courseRepository.deleteByStudentAndId(studentEntity, courseId);
    }

    @Override
    public long saveTeacher(TeacherRequestDto teacherRequestDto) {
        Teacher newTeacher = Teacher
                .builder()
                .age(teacherRequestDto.getAge())
                .name(teacherRequestDto.getName())
                .surname(teacherRequestDto.getSurname())
                .students(teacherRequestDto.getStudents())
                .build();
        var id = teacherRepository.save(newTeacher).getId();
        return id;
    }

    //deletes teachers from Student firstly, then Teacher itself
    @Override
    public void deleteTeacher(long id) {
        var teacherById = teacherRepository.findById(id);
        List<Student> studentsOfTeacher = teacherById.get().getStudents();

        for (Student students : studentsOfTeacher) {
            students.getTeachers().remove((int) id - 1);
            studentRepository.save(students);
        }
        teacherRepository.deleteById(id);
    }

    @Override
    public List<TeacherResponseDto> getAllTeacher() {
        var allTeachers = teacherRepository.findAll();
        List<TeacherResponseDto> list = new ArrayList<>();

        for (Teacher teachers : allTeachers) {
            list.add(TeacherResponseDto
                    .builder()
                    .id(teachers.getId())
                    .age(teachers.getAge())
                    .name(teachers.getName())
                    .surname(teachers.getSurname())
                    .students(teachers.getStudents())
                    .build()
            );
        }
        return list;
    }

    @Override
    public TeacherResponseDto getTeacherById(long id) {
        var teacherById = teacherRepository.findById(id).get();
        TeacherResponseDto teacherResponseDto =
                TeacherResponseDto
                        .builder()
                        .id(teacherById.getId())
                        .name(teacherById.getName())
                        .surname(teacherById.getSurname())
                        .age(teacherById.getAge())
                        .students(teacherById.getStudents())
                        .build();
        return teacherResponseDto;
    }

    @Override
    public Student addTeacherById(long teacherId, long studentId) {
        var student = studentRepository.findById(studentId).get();
        var teacher = teacherRepository.findById(teacherId).get();

        student.getTeachers().add(teacher);
        //studentRepository.save(student);
        return studentRepository.save(student);
    }

    @Override
    public TeacherResponseDto addStudentById(long teacherId, long studentId) {
        return null;
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
