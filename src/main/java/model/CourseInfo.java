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
public class CourseInfo {
    private int id;
    private String titel;
    private String cursuscode;
    private LocalDate startdatum;
    private String duur;
}
