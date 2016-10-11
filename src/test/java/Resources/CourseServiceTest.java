package Resources;

import database.CourseDb;
import model.Course;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import service.CourseService;

import javax.ws.rs.core.Response;

import java.util.ArrayList;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;

/**
 * Created by rjpvr on 11-10-2016.
 */
@RunWith(MockitoJUnitRunner.class)
public class CourseServiceTest {


    @Rule
    public ExpectedException expectedEx = ExpectedException.none();

    @Mock
    private CourseDb db;

    @InjectMocks
    private CourseService sut;

    @Test
    public void getCourseInfoList() throws Exception {
        Course c = new Course();
        ArrayList<Course> cList = new ArrayList<>();
        cList.add(c);

        Mockito.when(db.getCourses()).thenReturn(cList);

        Response r = sut.getCourses();

        Mockito.verify(db, times(1)).getCourses();
        assertThat(r.getStatus(), is(200));
    }

    @Test
    public void getCourseInfoListToHandleException() throws Exception {
        Course c = new Course();
        ArrayList<Course> cList = new ArrayList<>();
        cList.add(c);

        Mockito.when(db.getCourses()).thenThrow(new IllegalArgumentException());

        Response r = sut.getCourses();

        assertThat(r.getStatus(), is(412));
    }



    @Test
    public void addCourseInfo() throws Exception {
        Course c = new Course();

        doNothing().when(db).saveCourse(c);

        Response r = sut.addCourse(c);

        Mockito.verify(db, times(1)).saveCourse(c);

        assertThat(r.getStatus(), is(200));
    }

    @Test
    public void addCourseInfoToHandleException() throws Exception {
        Course c = new Course();

        doThrow(new IllegalArgumentException()).when(db).saveCourse(c);

        Response r = sut.addCourse(c);

        Mockito.verify(db, times(1)).saveCourse(c);

        assertThat(r.getStatus(), is(412));
    }

    @Test
    public void optionHeader() throws Exception {
        Response r = sut.optionHeader();

        assertThat(r.getStatus(), is(200));

        assertThat(r.getHeaders().toString().contains("[GET, POST, PUT, DELETE]"), is(true));
    }
}
