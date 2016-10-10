package model;

import lombok.*;

/**
 * Created by rjpvr on 10-10-2016.
 */

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class CourseInfo {
    private int id;
    private String name;
    private String description;
}
