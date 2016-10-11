package model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Created by rjpvr on 10-10-2016.
 */

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class StudentData {
    private Student student;
    private String address;
    private String city;
    private int housenumber;
    private String housenumberAddition;
    private String bankAccount;
    private String postalCode;
}
