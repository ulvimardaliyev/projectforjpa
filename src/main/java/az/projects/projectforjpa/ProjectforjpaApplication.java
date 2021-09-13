package az.projects.projectforjpa;

import az.projects.projectforjpa.dao.entity.Course;
import az.projects.projectforjpa.dao.entity.Student;
import az.projects.projectforjpa.dao.entity.Teacher;
import az.projects.projectforjpa.dao.repository.CourseRepository;
import az.projects.projectforjpa.dao.repository.StudentRepository;
import az.projects.projectforjpa.dao.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class ProjectforjpaApplication implements CommandLineRunner {
    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private TeacherRepository teacherRepository;

    public static void main(String[] args) {
        SpringApplication.run(ProjectforjpaApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        List<Student> students = new ArrayList<>();
        List<Teacher> teachers = new ArrayList<>();

        Student student = new Student();
        student.setStudentName("Ulvi");
        student.setAge(24);
        student.setId(1);
        students.add(student);
        Teacher teacher = new Teacher();
        teacher.setAge(48);
        teacher.setId(1);
        teacher.setName("Saadat");
        teacher.setSurname("Karimova");
        teacher.setStudents(students);
        student.setTeachers(teachers);
        Course course = new Course();
        course.setId(1);
        course.setCourseName("Java Programming");
        course.setStudent(student);

        studentRepository.save(student);
        courseRepository.save(course);
        teacherRepository.save(teacher);
    }
}
