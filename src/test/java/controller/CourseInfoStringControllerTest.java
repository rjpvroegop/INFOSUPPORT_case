package controller;

import model.CourseInfo;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Created by rjpvr on 11-10-2016.
 */
public class CourseInfoStringControllerTest {
    public CourseInfoStringController cisc;

    String correctTestString = "Titel: C# Programmeren\n" +
        "Cursuscode: CNETIN\n" +
        "Duur: 5 dagen\n" +
        "Startdatum: 14/10/2013\n" +
        "\n" +
        "Titel: C# Programmeren\n" +
        "Cursuscode: CNETIN\n" +
        "Duur: 5 dagen\n" +
        "Startdatum: 21/10/2013\n" +
        "\n" +
        "Titel: Advanced C#\n" +
        "Cursuscode: ADCSB\n" +
        "Duur: 2 dagen\n" +
        "Startdatum: 21/10/2013\n" +
        "\n" +
        "Titel: Advanced C#\n" +
        "Cursuscode: ADCSB\n" +
        "Duur: 2 dagen\n" +
        "Startdatum: 14/10/2013\n" +
        "\n" +
        "Titel: C# Programmeren\n" +
        "Cursuscode: CNETIN\n" +
        "Duur: 5 dagen\n" +
        "Startdatum: 14/10/2013\n";

    @Before
    public void init(){
        cisc = new CourseInfoStringController();
    }

    @Test
    public void toArrayList() throws Exception {
        ArrayList<CourseInfo> ci = cisc.toArrayList(correctTestString);

        assertThat(ci.size(), is(5));
    }

    @Test
    public void createCourseFirstWithRightInfo() throws Exception {
        ArrayList<CourseInfo> cil = cisc.toArrayList(correctTestString);

        CourseInfo ci = cil.get(0);

        assertThat(ci.getTitel(), is("C# Programmeren"));
        assertThat(ci.getCursuscode(), is("CNETIN"));
        assertThat(ci.getDuur(), is("5 dagen"));
        assertThat(ci.getStartdatum().equals(LocalDate.of(2013, 10, 14)), is(true));
    }

    @Test
    public void createCourseLastWithRightInfo() throws Exception {
        ArrayList<CourseInfo> cil = cisc.toArrayList(correctTestString);

        CourseInfo ci = cil.get(4);

        assertThat(ci.getTitel(), is("C# Programmeren"));
        assertThat(ci.getCursuscode(), is("CNETIN"));
        assertThat(ci.getDuur(), is("5 dagen"));
        assertThat(ci.getStartdatum().equals(LocalDate.of(2013, 10, 14)), is(true));
    }

}
