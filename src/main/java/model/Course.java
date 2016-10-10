package model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

/**
 * Created by rjpvr on 10-10-2016.
 */

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Course {
    private int id;
    private LocalDate coursePlanning;
}
