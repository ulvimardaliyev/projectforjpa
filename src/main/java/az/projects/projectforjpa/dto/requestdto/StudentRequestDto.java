package az.projects.projectforjpa.dto.requestdto;

import az.projects.projectforjpa.dao.entity.Course;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StudentRequestDto {
    private String studentName;
    private int age;
    private List<Course> course;
}
