package course;

import courseinfo.CourseInfoController;
import database.DatabaseConnector;
import student.StudentController;
import model.Course;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;


public class CourseDb {
    private CourseInfoController cic = new CourseInfoController();
    private StudentController studentController = new StudentController();
    private DatabaseConnector dc = new DatabaseConnector();
    private static Connection conn = null;

    protected CourseDb(){

    }

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

            courses = courseRsToArrayList(rs);

            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
        return courses;
    }

    public ArrayList<Course> getCourses(LocalDate startDate) throws SQLException, ClassNotFoundException {
        ArrayList<Course>courses = new ArrayList<>();
        try {
            conn = dc.connect();

            PreparedStatement customerStatement = conn.prepareStatement(
                "SELECT * " +
                "FROM course " +
                "WHERE courseinfoid in ( select id " +
                "                        from courseinfo " +
                "                        where datum BETWEEN ? AND ?)");


            customerStatement.setDate(1, java.sql.Date.valueOf(startDate));
            customerStatement.setDate(2, java.sql.Date.valueOf(startDate.plusDays(6)));

            System.out.println(startDate);

            ResultSet rs = customerStatement.executeQuery();

            courses = courseRsToArrayList(rs);

            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
        return courses;
    }

    private ArrayList<Course> courseRsToArrayList(ResultSet rs) throws SQLException, ClassNotFoundException {
        ArrayList<Course> courses = new ArrayList<>();

        while(rs.next()){
            //Retrieve by column name
            int id  = rs.getInt("courseinfoid");
            String email = rs.getString("studentemail");

            Course course = Course.builder()
                .courseInfo(
                    cic.getCourseInfo(id)
                )
                .student(
                    studentController.getStudent(email)
                )
                .build();

            courses.add(course);
        }

        return courses;
    }
}
