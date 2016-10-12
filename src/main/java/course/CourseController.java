package course;

import model.Course;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Created by rjpvr on 12-10-2016.
 */
public class CourseController {

    private CourseDb db = new CourseDb();

    public ArrayList<Course> getCourses() throws SQLException, ClassNotFoundException {
        return db.getCourses();
    }

    public ArrayList<Course> getCourses(LocalDate date) throws SQLException, ClassNotFoundException {
        return db.getCourses(date);
    }

    public void saveCourse(Course c) throws SQLException, ClassNotFoundException {
        db.saveCourse(c);
    }
}
