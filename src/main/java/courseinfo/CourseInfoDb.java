package courseinfo;

import database.DatabaseConnector;
import model.Course;
import model.CourseInfo;

import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Locale;

public class CourseInfoDb {

    protected CourseInfoDb(){

    }

    private DatabaseConnector dc = new DatabaseConnector();
    private static Connection conn = null;

    public void saveCourseInfo(CourseInfo info) throws SQLException, ClassNotFoundException {
        try {
            conn = dc.connect();

            PreparedStatement courseStatement = conn.prepareStatement("insert into courseinfo (titel, code, datum, duur) values(?, ?, ?, ?)");

            courseStatement.setString(1, info.getTitel());
            courseStatement.setString(2, info.getCursuscode());
            courseStatement.setDate(3, java.sql.Date.valueOf(info.getStartdatum()));
            courseStatement.setString(4, info.getDuur());

            courseStatement.executeUpdate();

            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    public ArrayList<CourseInfo> getCourseInfo() throws SQLException, ClassNotFoundException {
        ArrayList<CourseInfo> courseInfos = new ArrayList<>();
        try {
            conn = dc.connect();

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/mm/yyyy");
            formatter = formatter.withLocale(Locale.GERMAN);

            PreparedStatement customerStatement = conn.prepareStatement("SELECT * FROM courseinfo");
            ResultSet rs = customerStatement.executeQuery();

            courseInfos = courseInfoRsToArrayList(rs);

            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
        return courseInfos;
    }

    public ArrayList<CourseInfo> getCourseInfo(LocalDate startDate) throws SQLException, ClassNotFoundException {
        ArrayList<CourseInfo> courseInfos = new ArrayList<>();
        try {
            conn = dc.connect();

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/mm/yyyy");
            formatter = formatter.withLocale(Locale.GERMAN);

            PreparedStatement customerStatement = conn.prepareStatement("SELECT * FROM courseinfo WHERE datum BETWEEN ? AND ?");

            customerStatement.setDate(1, java.sql.Date.valueOf(startDate));
            customerStatement.setDate(2, java.sql.Date.valueOf(startDate.plusDays(4)));

            ResultSet rs = customerStatement.executeQuery();

            courseInfos = courseInfoRsToArrayList(rs);

            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
        return courseInfos;
    }

    public CourseInfo getCourseInfo(int id) throws SQLException, ClassNotFoundException {
        CourseInfo.CourseInfoBuilder courseInfoBuilder = CourseInfo.builder();
        try {
            conn = dc.connect();

            PreparedStatement customerStatement = conn.prepareStatement("SELECT * FROM courseinfo WHERE id = " + id);
            ResultSet rs = customerStatement.executeQuery();

            while (rs.next()) {
                //Retrieve by column name
                int cid = rs.getInt("id");
                String titel = rs.getString("titel");
                String code = rs.getString("code");
                /* remove time from datetime stamp in SQL */
                LocalDate date = LocalDate.parse(rs.getString("datum").split(" ")[0]);
                String dura = rs.getString("duur");

                courseInfoBuilder.id(cid).titel(titel).cursuscode(code).startdatum(date).duur(dura).build();
            }

            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
        return courseInfoBuilder.build();
    }

    public void updateCourseInfo(CourseInfo info) throws SQLException, ClassNotFoundException {
        try {
            conn = dc.connect();

            PreparedStatement infoStatement = conn.prepareStatement("UPDATE courseinfo SET titel = ?, code = ?, datum = ?, duur = ? WHERE id IN ?");

            infoStatement.setString(1, info.getTitel());
            infoStatement.setString(2, info.getCursuscode());
            infoStatement.setDate(3, java.sql.Date.valueOf(info.getStartdatum()));
            infoStatement.setString(4, info.getDuur());

            infoStatement.setInt(5, info.getId());

            infoStatement.executeUpdate();

            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    public void removeCourseInfo(CourseInfo info) throws SQLException, ClassNotFoundException {
        try {
            conn = dc.connect();

            PreparedStatement customerStatement = conn.prepareStatement("DELETE FROM courseinfo WHERE id IN ?");

            customerStatement.setInt(1, info.getId());

            customerStatement.executeUpdate();

            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }



    private ArrayList<CourseInfo> courseInfoRsToArrayList(ResultSet rs) throws SQLException, ClassNotFoundException {
        ArrayList<CourseInfo> courseInfos = new ArrayList<>();

        while (rs.next()) {
            //Retrieve by column name
            int id = rs.getInt("id");
            String titel = rs.getString("titel");
            String code = rs.getString("code");
            LocalDate date = LocalDate.parse(rs.getString("datum").split(" ")[0]);
            String dura = rs.getString("duur");

            CourseInfo cInfo = CourseInfo.builder().id(id).titel(titel).cursuscode(code).startdatum(date).duur(dura).build();
            courseInfos.add(cInfo);
        }

        return courseInfos;
    }
}
