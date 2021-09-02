package az.projects.projectforjpa.dao.repository;

import az.projects.projectforjpa.dao.entity.Course;
import az.projects.projectforjpa.dao.entity.Student;
import az.projects.projectforjpa.dto.responsedto.CourseResponseDto;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CourseRepository extends CrudRepository<Course, Long> {
    CourseResponseDto deleteByStudentAndId(Student student, long courseId);
}
