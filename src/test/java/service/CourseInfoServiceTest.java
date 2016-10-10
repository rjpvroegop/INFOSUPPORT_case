package service;

import database.CourseInfoDb;
import model.CourseInfo;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import javax.ws.rs.core.Response;
import java.util.ArrayList;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;

/**
 * Created by rjpvr on 10-10-2016.
 */

@RunWith(MockitoJUnitRunner.class)
public class CourseInfoServiceTest {

    @Rule
    public ExpectedException expectedEx = ExpectedException.none();

    @Mock
    private CourseInfoDb db;

    @InjectMocks
    private CourseInfoService sut;

    @Test
    public void getCourseInfoList() throws Exception {
        CourseInfo ci = new CourseInfo(1, "test", "desc");
        ArrayList<CourseInfo> ciList = new ArrayList<>();
        ciList.add(ci);

        Mockito.when(db.getCourseInfo()).thenReturn(ciList);

        Response r = sut.getCourseInfo();

        Mockito.verify(db, times(1)).getCourseInfo();
        assertThat(r.getStatus(), is(200));
    }

    @Test
    public void getCourseInfoListToHandleException() throws Exception {
        CourseInfo ci = new CourseInfo(1, "test", "desc");
        ArrayList<CourseInfo> ciList = new ArrayList<>();
        ciList.add(ci);

        Mockito.when(db.getCourseInfo()).thenThrow(new IllegalArgumentException());

        Response r = sut.getCourseInfo();

        assertThat(r.getStatus(), is(412));
    }

    @Test
    public void getCourseInfoPerCourse() throws Exception {
        CourseInfo ci = new CourseInfo(1, "test", "desc");

        Mockito.when(db.getCourseInfo(0)).thenReturn(ci);

        Response r = sut.getCourseInfo(0);

        Mockito.verify(db, times(1)).getCourseInfo(0);

        assertThat(r.getStatus(), is(200));
    }

    @Test
    public void getCourseInfoPerCourseToHandleException() throws Exception {
        CourseInfo ci = new CourseInfo(1, "test", "desc");

        Mockito.when(db.getCourseInfo(0)).thenThrow(new IllegalArgumentException());

        Response r = sut.getCourseInfo(0);

        Mockito.verify(db, times(1)).getCourseInfo(0);

        assertThat(r.getStatus(), is(412));
    }

    @Test
    public void addCourseInfo() throws Exception {
        CourseInfo ci = new CourseInfo(1, "test", "desc");

        doNothing().when(db).saveCourseInfo(ci);

        Response r = sut.addCourseInfo(ci);

        Mockito.verify(db, times(1)).saveCourseInfo(ci);

        assertThat(r.getStatus(), is(200));
    }

    @Test
    public void addCourseInfoToHandleException() throws Exception {
        CourseInfo ci = new CourseInfo(1, "test", "desc");

        doThrow(new IllegalArgumentException()).when(db).saveCourseInfo(ci);

        Response r = sut.addCourseInfo(ci);

        Mockito.verify(db, times(1)).saveCourseInfo(ci);

        assertThat(r.getStatus(), is(412));
    }

    @Test
    public void updateCourseInfo() throws Exception {
        CourseInfo ci = new CourseInfo(1, "test", "desc");

        doNothing().when(db).updateCourseInfo(ci);

        Response r = sut.updateCourseInfo(ci);

        Mockito.verify(db, times(1)).updateCourseInfo(ci);

        assertThat(r.getStatus(), is(200));
    }

    @Test
    public void updateCourseInfoToHandleException() throws Exception {
        CourseInfo ci = new CourseInfo(1, "test", "desc");

        doThrow(new IllegalArgumentException()).when(db).updateCourseInfo(ci);

        Response r = sut.updateCourseInfo(ci);

        Mockito.verify(db, times(1)).updateCourseInfo(ci);

        assertThat(r.getStatus(), is(412));
    }

    @Test
    public void removeCourseInfo() throws Exception {
        CourseInfo ci = new CourseInfo(1, "test", "desc");

        doNothing().when(db).removeCourseInfo(ci);

        Response r = sut.removeCourseInfo(ci);

        Mockito.verify(db, times(1)).removeCourseInfo(ci);

        assertThat(r.getStatus(), is(200));
    }

    @Test
    public void removeCourseInfoToHandleException() throws Exception {
        CourseInfo ci = new CourseInfo(1, "test", "desc");

        doThrow(new IllegalArgumentException()).when(db).removeCourseInfo(ci);

        Response r = sut.removeCourseInfo(ci);

        Mockito.verify(db, times(1)).removeCourseInfo(ci);

        assertThat(r.getStatus(), is(412));
    }

    @Test
    public void optionHeader() throws Exception {
        Response r = sut.optionHeader();

        assertThat(r.getStatus(), is(200));

        assertThat(r.getHeaders().toString().contains("[GET, POST, PUT, DELETE]"), is(true));
    }

}
