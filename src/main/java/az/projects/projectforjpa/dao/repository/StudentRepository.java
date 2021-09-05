package az.projects.projectforjpa.dao.repository;

import az.projects.projectforjpa.dao.entity.Student;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends CrudRepository<Student, Long> {
}
