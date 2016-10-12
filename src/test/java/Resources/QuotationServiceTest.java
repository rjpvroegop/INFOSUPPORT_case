package Resources;

import course.CourseController;
import course.CourseDb;
import model.Course;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import course.QuotationService;

import javax.ws.rs.core.Response;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.times;

/**
 * Created by rjpvr on 12-10-2016.
 */
@RunWith(MockitoJUnitRunner.class)
public class QuotationServiceTest {
    @Rule
    public ExpectedException expectedEx = ExpectedException.none();

    @Mock
    private CourseController db;

    @InjectMocks
    private QuotationService sut;

    @Test
    public void getCoursesByWeek() throws Exception {
        Course c = new Course();
        ArrayList<Course> cList = new ArrayList<>();
        cList.add(c);

        Mockito.when(db.getCourses(any(LocalDate.class))).thenReturn(cList);

        Response r = sut.getCourses(43);

        Mockito.verify(db, times(1)).getCourses(any(LocalDate.class));
        assertThat(r.getStatus(), is(200));
    }

    @Test
    public void getCoursesByWeekToHandleException() throws Exception {
        Course c = new Course();
        ArrayList<Course> cList = new ArrayList<>();
        cList.add(c);

        Mockito.when(db.getCourses(any(LocalDate.class))).thenThrow(new IllegalArgumentException());

        Response r = sut.getCourses(43);

        assertThat(r.getStatus(), is(412));
    }

    @Test
    public void getCoursesByWeekAndYear() throws Exception {
        Course c = new Course();
        ArrayList<Course> cList = new ArrayList<>();
        cList.add(c);

        Mockito.when(db.getCourses(any(LocalDate.class))).thenReturn(cList);

        Response r = sut.getCourses(43, 2013);

        Mockito.verify(db, times(1)).getCourses(any(LocalDate.class));
        assertThat(r.getStatus(), is(200));
    }

    @Test
    public void getCoursesByWeekAndYearToHandleException() throws Exception {
        Course c = new Course();
        ArrayList<Course> cList = new ArrayList<>();
        cList.add(c);

        Mockito.when(db.getCourses(any(LocalDate.class))).thenThrow(new IllegalArgumentException());

        Response r = sut.getCourses(43, 2013);

        assertThat(r.getStatus(), is(412));
    }

    @Test
    public void optionHeader() throws Exception {
        Response r = sut.optionHeader();

        assertThat(r.getStatus(), is(200));

        assertThat(r.getHeaders().toString().contains("[GET, POST, PUT, DELETE]"), is(true));
    }
}
