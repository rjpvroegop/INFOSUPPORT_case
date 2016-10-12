package controller;

import course.CourseController;
import course.CourseDb;
import courseinfo.CourseInfoController;
import courseinfo.CourseInfoDb;
import model.Course;
import model.CourseInfo;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.cglib.core.Local;
import org.mockito.runners.MockitoJUnitRunner;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.*;

/**
 * Created by rjpvr on 12-10-2016.
 */

@RunWith(MockitoJUnitRunner.class)
public class CourseInfoControllerTest {
    @Rule
    public ExpectedException expectedEx = ExpectedException.none();

    @Mock
    private CourseInfoDb db;

    @InjectMocks
    private CourseInfoController sut;

    @Test
    public void saveCourseCallDb() throws Exception {
        doNothing().when(db).saveCourseInfo(any(CourseInfo.class));
        doReturn(new ArrayList<CourseInfo>()).when(db).getCourseInfo(any(LocalDate.class));

        sut.saveCourseInfo(new CourseInfo(1,"test","abc",LocalDate.now(),"5 dagen"));

        verify(db, times(1)).saveCourseInfo(any(CourseInfo.class));
    }

    @Test
    public void dontSaveDuplicateCourseCallDb() throws Exception {
        ArrayList<CourseInfo> al = new ArrayList<CourseInfo>();
        al.add(new CourseInfo(1,"test","abc",LocalDate.now(),"5 dagen"));

        doReturn(al).when(db).getCourseInfo(any(LocalDate.class));

        Boolean doesNotExist = sut.doesNotExistYet(new CourseInfo(1,"test","abc",LocalDate.now(),"5 dagen"));

        assertThat(doesNotExist, is(false));
    }

    @Test
    public void dontSaveDuplicateCourse2CallDb() throws Exception {
        ArrayList<CourseInfo> al = new ArrayList<CourseInfo>();
        al.add(new CourseInfo(1,"test","abc",LocalDate.now().minusMonths(1),"5 dagen"));
        al.add(new CourseInfo(1,"test","abc",LocalDate.now(),"5 dagen"));

        doReturn(al).when(db).getCourseInfo(any(LocalDate.class));

        Boolean doesNotExist = sut.doesNotExistYet(new CourseInfo(1,"test","abc",LocalDate.now(),"5 dagen"));

        assertThat(doesNotExist, is(false));
    }

    @Test
    public void saveCoursesEmptyListDontCallDb() throws Exception {
        doNothing().when(db).saveCourseInfo(any(CourseInfo.class));

        sut.saveCourseInfo(new ArrayList<CourseInfo>());

        verify(db, times(0)).saveCourseInfo(any(CourseInfo.class));
    }

    @Test
    public void saveCoursesCallDb() throws Exception {
        doNothing().when(db).saveCourseInfo(any(CourseInfo.class));
        doReturn(new ArrayList<CourseInfo>()).when(db).getCourseInfo(any(LocalDate.class));

        ArrayList<CourseInfo> al = new ArrayList<CourseInfo>();
        al.add(new CourseInfo(1,"test","abc",LocalDate.now(),"5dagen"));

        sut.saveCourseInfo(al);

        verify(db, times(1)).saveCourseInfo(any(CourseInfo.class));
    }

    @Test
    public void getCourseCallDb() throws Exception {
        doReturn(new ArrayList<CourseInfo>()).when(db).getCourseInfo();

        sut.getCourseInfo();

        verify(db, times(1)).getCourseInfo();
    }

    @Test
    public void getCourseByDateCallDb() throws Exception {
        doReturn(new ArrayList<CourseInfo>()).when(db).getCourseInfo(any(LocalDate.class));

        sut.getCourseInfo(LocalDate.now());

        verify(db, times(1)).getCourseInfo(any(LocalDate.class));
    }

    @Test
    public void getCourseByIdCallDb() throws Exception {
        doReturn(new CourseInfo()).when(db).getCourseInfo(anyInt());

        sut.getCourseInfo(5);

        verify(db, times(1)).getCourseInfo(anyInt());
    }

    @Test
    public void updateCourseInfoCallDb() throws Exception {
        doNothing().when(db).updateCourseInfo(any(CourseInfo.class));

        sut.updateCourseInfo(new CourseInfo());

        verify(db, times(1)).updateCourseInfo(any(CourseInfo.class));
    }

    @Test
    public void removeCourseInfoCallDb() throws Exception {
        doNothing().when(db).removeCourseInfo(any(CourseInfo.class));

        sut.removeCourseInfo(new CourseInfo());

        verify(db, times(1)).removeCourseInfo(any(CourseInfo.class));
    }
}
