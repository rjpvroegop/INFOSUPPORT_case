package database;

import model.Course;
import model.Student;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class CourseDb {
    private DatabaseConnector dc = new DatabaseConnector();
    private static Connection conn = null;

    public void saveCourse(Course course) throws SQLException, ClassNotFoundException {
        try {
            conn = dc.connect();

            PreparedStatement courseStatement = conn.prepareStatement("insert into course (courseinfoid, studentemail) values(?, ?)");

            courseStatement.setInt(1, course.getCourseInfo().getId());
            courseStatement.setString(2, course.getStudent().getEmail());

            courseStatement.executeUpdate();

            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    public ArrayList<Course> getCourses() throws SQLException, ClassNotFoundException {
        ArrayList<Course>courses = new ArrayList<>();
        try {
            conn = dc.connect();

            PreparedStatement customerStatement = conn.prepareStatement("SELECT * FROM course");
            ResultSet rs = customerStatement.executeQuery();

            while(rs.next()){
                //Retrieve by column name
                int id  = rs.getInt("courseinfoid");
                String email = rs.getString("studentemail");

                Course course = Course.builder()
                    .courseInfo(
                        new CourseInfoDb().getCourseInfo(id)
                    )
                    .student(
                        new StudentDb().getStudent(email)
                    )
                    .build();

                courses.add(course);
            }

            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
        return courses;
    }

}
