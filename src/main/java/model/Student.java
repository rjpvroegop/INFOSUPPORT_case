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
public class Student {
    private String email;
    private String name;
    private String lastName;
    private StudentType type;
    private StudentData sd;
    private CompanyData cd;
}
