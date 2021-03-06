package Resources;

import courseinfo.CourseInfoController;
import model.CourseInfo;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import courseinfo.CourseInfoService;

import javax.ws.rs.core.Response;
import java.time.LocalDate;
import java.util.ArrayList;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;

/**
 * Created by rjpvr on 10-10-2016.
 */

@RunWith(MockitoJUnitRunner.class)
public class CourseInfoServiceTest {

    @Rule
    public ExpectedException expectedEx = ExpectedException.none();

    @Mock
    private CourseInfoController db;

    @InjectMocks
    private CourseInfoService sut;

    @Test
    public void getCourseInfoList() throws Exception {
        CourseInfo ci = new CourseInfo();
        ArrayList<CourseInfo> ciList = new ArrayList<>();
        ciList.add(ci);

        Mockito.when(db.getCourseInfo()).thenReturn(ciList);

        Response r = sut.getCourseInfo();

        Mockito.verify(db, times(1)).getCourseInfo();
        assertThat(r.getStatus(), is(200));
    }

    @Test
    public void getCourseInfoListToHandleException() throws Exception {
        CourseInfo ci = new CourseInfo();
        ArrayList<CourseInfo> ciList = new ArrayList<>();
        ciList.add(ci);

        Mockito.when(db.getCourseInfo()).thenThrow(new IllegalArgumentException());

        Response r = sut.getCourseInfo();

        assertThat(r.getStatus(), is(412));
    }

    @Test
    public void getCourseInfoListByWeek() throws Exception {
        CourseInfo ci = new CourseInfo();
        ArrayList<CourseInfo> ciList = new ArrayList<>();
        ciList.add(ci);

        Mockito.when(db.getCourseInfo(any(LocalDate.class))).thenReturn(ciList);

        Response r = sut.getCourses(43);

        Mockito.verify(db, times(1)).getCourseInfo(any(LocalDate.class));
        assertThat(r.getStatus(), is(200));
    }

    @Test
    public void getCourseInfoListByWeekToHandleException() throws Exception {
        CourseInfo ci = new CourseInfo();
        ArrayList<CourseInfo> ciList = new ArrayList<>();
        ciList.add(ci);

        Mockito.when(db.getCourseInfo(any(LocalDate.class))).thenThrow(new IllegalArgumentException());

        Response r = sut.getCourses(43);

        assertThat(r.getStatus(), is(412));
    }

    @Test
    public void getCourseInfoListByWeekAndYear() throws Exception {
        CourseInfo ci = new CourseInfo();
        ArrayList<CourseInfo> ciList = new ArrayList<>();
        ciList.add(ci);

        Mockito.when(db.getCourseInfo(any(LocalDate.class))).thenReturn(ciList);

        Response r = sut.getCourses(43, 2013);

        Mockito.verify(db, times(1)).getCourseInfo(any(LocalDate.class));
        assertThat(r.getStatus(), is(200));
    }

    @Test
    public void getCourseInfoListByWeekAndYearToHandleException() throws Exception {
        CourseInfo ci = new CourseInfo();
        ArrayList<CourseInfo> ciList = new ArrayList<>();
        ciList.add(ci);

        Mockito.when(db.getCourseInfo(any(LocalDate.class))).thenThrow(new IllegalArgumentException());

        Response r = sut.getCourses(43, 2013);

        assertThat(r.getStatus(), is(412));
    }

    @Test
    public void getCourseInfoPerCourse() throws Exception {
        CourseInfo ci = new CourseInfo();

        Mockito.when(db.getCourseInfo(0)).thenReturn(ci);

        Response r = sut.getCourseInfo(0);

        Mockito.verify(db, times(1)).getCourseInfo(0);

        assertThat(r.getStatus(), is(200));
    }

    @Test
    public void getCourseInfoPerCourseToHandleException() throws Exception {
        CourseInfo ci = new CourseInfo();

        Mockito.when(db.getCourseInfo(0)).thenThrow(new IllegalArgumentException());

        Response r = sut.getCourseInfo(0);

        Mockito.verify(db, times(1)).getCourseInfo(0);

        assertThat(r.getStatus(), is(412));
    }

    @Test
    public void addCourseInfoString() throws Exception {
        String ci = "Titel: C# Programmeren\n" +
            "Cursuscode: CNETIN\n" +
            "Duur: 5 dagen\n" +
            "Startdatum: 14/10/2013\n";

        doNothing().when(db).saveCourseInfo(any(ArrayList.class));

        Response r = sut.addCourseInfo(ci);

        Mockito.verify(db, times(1)).saveCourseInfo(any(ArrayList.class));

        assertThat(r.getStatus(), is(200));
    }

    @Test
    public void addCourseInfoStringToHandleException() throws Exception {
        String ci = "Titel: C# Programmeren\n" +
            "Cursuscode: CNETIN\n" +
            "Duur: 5 dagen\n" +
            "Startdatum: 14/10/2013\n";

        doThrow(new IllegalArgumentException()).when(db).saveCourseInfo(any(ArrayList.class));

        Response r = sut.addCourseInfo(ci);

        Mockito.verify(db, times(1)).saveCourseInfo(any(ArrayList.class));

        assertThat(r.getStatus(), is(412));
    }

    @Test
    public void optionHeader() throws Exception {
        Response r = sut.optionHeader();

        assertThat(r.getStatus(), is(200));

        assertThat(r.getHeaders().toString().contains("[GET, POST]"), is(true));
    }

}
