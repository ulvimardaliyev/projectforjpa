package az.projects.projectforjpa.dao.repository;

import az.projects.projectforjpa.dao.entity.Student;
import az.projects.projectforjpa.dto.responsedto.StudentResponseDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends CrudRepository<Student, Long> {
    /*@Modifying
    @Query("update student_teacher set teacher_id=?1")
    StudentResponseDto addTeacherToStudent(Long teacher_Id, Long student_Id);*/
}
