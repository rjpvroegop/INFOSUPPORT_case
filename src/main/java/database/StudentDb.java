package database;

import model.CourseInfo;
import model.Student;
import model.StudentType;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

/**
 * Created by rjpvr on 11-10-2016.
 */
public class StudentDb {
    private DatabaseConnector dc = new DatabaseConnector();
    private static Connection conn = null;

    public Student getStudent(String email) throws SQLException, ClassNotFoundException {
        Student.StudentBuilder studentBuilder = Student.builder();
        try {
            conn = dc.connect();

            PreparedStatement customerStatement = conn.prepareStatement("SELECT * FROM student WHERE email = ?");

            customerStatement.setString(1, email);

            ResultSet rs = customerStatement.executeQuery();

            while(rs.next()){
                //Retrieve by column name
                String mail = rs.getString("email");
                String name = rs.getString("name");
                String lastname = rs.getString("lastname");
                String type = rs.getString("studenttype");

                studentBuilder.email(mail).name(name).lastName(lastname).type(getType(type)).build();
            }

            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
        return studentBuilder.build();
    }

    public StudentType getType(String type){
        switch(type){
            case "PRIVATE":
                return StudentType.COMPANY;
            default:
                return StudentType.PRIVATE;
        }
    }
}
