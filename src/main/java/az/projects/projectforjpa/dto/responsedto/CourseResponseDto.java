package az.projects.projectforjpa.dto.responsedto;

import az.projects.projectforjpa.dao.entity.Student;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CourseResponseDto {
    private long id;
    private String courseName;
    private Student student;
}
