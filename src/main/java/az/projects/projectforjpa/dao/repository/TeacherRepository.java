package az.projects.projectforjpa.dao.repository;

import az.projects.projectforjpa.dao.entity.Teacher;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeacherRepository extends CrudRepository<Teacher, Long> {
}
