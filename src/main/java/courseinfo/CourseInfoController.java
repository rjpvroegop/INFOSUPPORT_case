package courseinfo;

import controller.DateController;
import model.CourseInfo;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.temporal.WeekFields;
import java.util.ArrayList;
import java.util.Locale;

/**
 * Created by rjpvr on 12-10-2016.
 */
public class CourseInfoController {
    DateController dateController = new DateController();
    private CourseInfoDb courseInfoDb = new CourseInfoDb();

    public void saveCourseInfo(ArrayList<CourseInfo> coursesInfo) throws SQLException, ClassNotFoundException {
        String exceptionMessage = "";

        for(CourseInfo ci : coursesInfo){
            saveCourseInfo(ci);
        }
    }

    public void saveCourseInfo(CourseInfo info) throws SQLException, ClassNotFoundException {
        if(doesNotExistYet(info)) {
            courseInfoDb.saveCourseInfo(info);
        }
    }

    public ArrayList<CourseInfo> getCourseInfo() throws SQLException, ClassNotFoundException {
        return courseInfoDb.getCourseInfo();
    }

    public CourseInfo getCourseInfo(int id) throws SQLException, ClassNotFoundException {
        return courseInfoDb.getCourseInfo(id);
    }

    public ArrayList<CourseInfo> getCourseInfo(LocalDate startDate) throws SQLException, ClassNotFoundException {
        return courseInfoDb.getCourseInfo(startDate);
    }

    public void updateCourseInfo(CourseInfo info) throws SQLException, ClassNotFoundException {
        courseInfoDb.updateCourseInfo(info);
    }

    public void removeCourseInfo(CourseInfo info) throws SQLException, ClassNotFoundException {
        courseInfoDb.removeCourseInfo(info);
    }

    public boolean doesNotExistYet(CourseInfo info) throws SQLException, ClassNotFoundException {
        WeekFields weekFields = WeekFields.of(Locale.GERMANY);
        int weekNr = info.getStartdatum().get(weekFields.weekOfWeekBasedYear());
        LocalDate firstDayOfWeek = dateController.getWeekOfYearDate(weekNr, info.getStartdatum().getYear());

        ArrayList<CourseInfo> ci = getCourseInfo(firstDayOfWeek);

        for(CourseInfo c : ci){
            if(c.getCursuscode().equals(info.getCursuscode())) {
                return false;
            }
        }

        return true;
    }
}
