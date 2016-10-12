package controller;

import course.CourseController;
import course.CourseDb;
import model.CompanyData;
import model.Course;
import model.Student;
import model.StudentData;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import student.StudentController;
import student.StudentDb;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.anything;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;

/**
 * Created by rjpvr on 12-10-2016.
 */

@RunWith(MockitoJUnitRunner.class)
public class CourseControllerTest {
    @Rule
    public ExpectedException expectedEx = ExpectedException.none();

    @Mock
    private CourseDb db;

    @InjectMocks
    private CourseController sut;

    @Test
    public void getCoursesCallDb() throws Exception {
        Mockito.when(db.getCourses()).thenReturn(new ArrayList<Course>());

        sut.getCourses();

        Mockito.verify(db, times(1)).getCourses();
    }

    @Test
    public void getCoursesWithDateCallDb() throws Exception {
        Mockito.when(db.getCourses(any(LocalDate.class))).thenReturn(new ArrayList<Course>());

        sut.getCourses(LocalDate.now());

        Mockito.verify(db, times(1)).getCourses(any(LocalDate.class));
    }

    @Test
    public void saveCourseCallDb() throws Exception {
        doNothing().when(db).saveCourse(any(Course.class));

        sut.saveCourse(new Course());

        Mockito.verify(db, times(1)).saveCourse(any(Course.class));
    }
}
