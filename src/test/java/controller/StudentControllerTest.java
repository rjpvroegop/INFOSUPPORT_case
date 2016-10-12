package controller;

import courseinfo.CourseInfoController;
import courseinfo.CourseInfoService;
import model.CompanyData;
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

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.times;

/**
 * Created by rjpvr on 12-10-2016.
 */

@RunWith(MockitoJUnitRunner.class)
public class StudentControllerTest {
    @Rule
    public ExpectedException expectedEx = ExpectedException.none();

    @Mock
    private StudentDb db;

    @InjectMocks
    private StudentController sut;

    @Test
    public void getStudentCallDb() throws Exception {
        Mockito.when(db.getStudent(anyString())).thenReturn(new Student());

        sut.getStudent("test@test.nl");

        Mockito.verify(db, times(1)).getStudent(anyString());
    }

    @Test
    public void getPrivateStudentDataCallDb() throws Exception {
        Mockito.when(db.getPrivateStudentData(anyString())).thenReturn(new StudentData());

        sut.getPrivateStudentData("test@test.nl");

        Mockito.verify(db, times(1)).getPrivateStudentData(anyString());
    }

    @Test
    public void getCompanyStudentDataCallDb() throws Exception {
        Mockito.when(db.getCompanyStudentData(anyString())).thenReturn(new CompanyData());

        sut.getCompanyStudentData("test@test.nl");

        Mockito.verify(db, times(1)).getCompanyStudentData(anyString());
    }
}
