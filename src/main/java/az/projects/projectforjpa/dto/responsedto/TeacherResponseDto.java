package az.projects.projectforjpa.dto.responsedto;

import az.projects.projectforjpa.dao.entity.Student;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TeacherResponseDto {

    private String name;
    private String surname;
    private int age;
    private List<Student> students;
}
