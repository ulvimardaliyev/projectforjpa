package az.projects.projectforjpa.dao.repository;

import az.projects.projectforjpa.dao.entity.Course;
import az.projects.projectforjpa.dao.entity.Student;
import az.projects.projectforjpa.dto.responsedto.CourseResponseDto;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface CourseRepository extends CrudRepository<Course, Long> {
    CourseResponseDto deleteByStudentAndId(Student student, long courseId);
}
