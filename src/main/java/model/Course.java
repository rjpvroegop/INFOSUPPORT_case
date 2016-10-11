package model;

import lombok.*;

import java.time.LocalDate;

/**
 * Created by rjpvr on 10-10-2016.
 */

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Course {
    private int id;
    private Student student;
    private CourseInfo courseInfo;
}
