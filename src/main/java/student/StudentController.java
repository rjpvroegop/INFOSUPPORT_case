package student;

import model.CompanyData;
import model.Student;
import model.StudentData;

import java.sql.SQLException;

/**
 * Created by rjpvr on 12-10-2016.
 */
public class StudentController {
    private StudentDb studentDb = new StudentDb(this);

    public Student getStudent(String email) throws SQLException, ClassNotFoundException {
        return studentDb.getStudent(email);
    }

    public StudentData getPrivateStudentData(String studentemail) throws SQLException, ClassNotFoundException {
        return studentDb.getPrivateStudentData(studentemail);
    }

    public CompanyData getCompanyStudentData(String studentemail) throws SQLException, ClassNotFoundException {
        return studentDb.getCompanyStudentData(studentemail);
    }
}
