package student;

import database.DatabaseConnector;
import model.*;

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
    private StudentController studentController;

    protected StudentDb(StudentController studentController){
        this.studentController = studentController;
    }

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
                StudentType type = getType(rs.getString("studenttype"));
                StudentData sd = null;
                CompanyData cd = null;

                if(type == StudentType.COMPANY){
                    cd = studentController.getCompanyStudentData(mail);
                } else {
                    sd = studentController.getPrivateStudentData(mail);
                }

                studentBuilder.email(mail).name(name).lastName(lastname).type(type).sd(sd).cd(cd).build();
            }

            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
        return studentBuilder.build();
    }

    public StudentData getPrivateStudentData(String studentemail) throws SQLException, ClassNotFoundException {
        StudentData sd = new StudentData();
        try {
            conn = dc.connect();

            PreparedStatement customerStatement = conn.prepareStatement("SELECT * FROM studentdata WHERE studentemail = ?");

            customerStatement.setString(1, studentemail);

            ResultSet rs = customerStatement.executeQuery();

            while(rs.next()){
                //Retrieve by column name
                String address = rs.getString("address");
                String bankaccount = rs.getString("bankaccount");
                String postalcode = rs.getString("postalcode");
                int housenumber = rs.getInt("housenumber");
                String housenumberaddition = rs.getString("housenumberaddition");
                String city = rs.getString("city");

                sd.setAddress(address);
                sd.setBankAccount(bankaccount);
                sd.setPostalCode(postalcode);
                sd.setHousenumber(housenumber);
                sd.setHousenumberAddition(housenumberaddition);
                sd.setCity(city);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }

        System.out.println(sd.getCity());

        return sd;
    }

    public CompanyData getCompanyStudentData(String studentemail) throws SQLException, ClassNotFoundException {
        CompanyData cd = new CompanyData();
        try {
            conn = dc.connect();

            PreparedStatement customerStatement = conn.prepareStatement("SELECT * FROM companydata WHERE studentemail = ?");

            customerStatement.setString(1, studentemail);

            ResultSet rs = customerStatement.executeQuery();

            while(rs.next()){
                //Retrieve by column name
                String quotation = rs.getString("quotation");
                cd.setQuotation(quotation);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }

        return cd;
    }

    private StudentType getType(String type){
        switch(type){
            case "PRIVATE":
                return StudentType.PRIVATE;
            default:
                return StudentType.COMPANY;
        }
    }
}
