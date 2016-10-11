package database;

import model.CourseInfo;

import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Locale;

public class CourseInfoDb {
    private static Connection conn = null;

    public void saveCourseInfo(ArrayList<CourseInfo> coursesInfo) throws SQLException, ClassNotFoundException {
        try {
            conn = DatabaseConnector.connect();

            PreparedStatement courseStatement = conn.prepareStatement("insert into courseinfo (titel, code, datum, duur) values(?, ?, ?, ?)");

            coursesInfo.forEach(info -> {
                try {
                    courseStatement.setString(1, info.getTitel());
                    courseStatement.setString(2, info.getCursuscode());
                    courseStatement.setDate(3, java.sql.Date.valueOf(info.getStartdatum()));
                    courseStatement.setString(4, info.getDuur());

                    courseStatement.executeUpdate();
                }catch(Exception e){
                    e.printStackTrace();
                }
            });

            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    public void saveCourseInfo(CourseInfo info) throws SQLException, ClassNotFoundException {
        try {
            conn = DatabaseConnector.connect();

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
        ArrayList<CourseInfo>customers = new ArrayList<>();
        try {
            conn = DatabaseConnector.connect();

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/mm/yyyy");
            formatter = formatter.withLocale(Locale.GERMAN);

            PreparedStatement customerStatement = conn.prepareStatement("SELECT * FROM courseinfo");
            ResultSet rs = customerStatement.executeQuery();

            while(rs.next()){
                //Retrieve by column name
                int id  = rs.getInt("id");
                String titel = rs.getString("titel");
                String code = rs.getString("code");
                LocalDate date = LocalDate.parse(rs.getString("datum"));
                String dura = rs.getString("duur");

                CourseInfo customer = CourseInfo.builder().id(id).titel(titel).cursuscode(code).startdatum(date).duur(dura).build();
                customers.add(customer);
            }

            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
        return customers;
    }

    public CourseInfo getCourseInfo(int id) throws SQLException, ClassNotFoundException {
        CourseInfo.CourseInfoBuilder courseInfoBuilder = CourseInfo.builder();
        try {
            conn = DatabaseConnector.connect();

            PreparedStatement customerStatement = conn.prepareStatement("SELECT * FROM courseinfo WHERE id = " + id);
            ResultSet rs = customerStatement.executeQuery();

            while(rs.next()){
                //Retrieve by column name
                int cid  = rs.getInt("id");
                String titel = rs.getString("titel");
                String code = rs.getString("code");
                LocalDate date = LocalDate.parse(rs.getString("datum"));
                String dura = rs.getString("duur");

                CourseInfo customer = CourseInfo.builder().id(cid).titel(titel).cursuscode(code).startdatum(date).duur(dura).build();
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
            conn = DatabaseConnector.connect();

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
            conn = DatabaseConnector.connect();

            PreparedStatement customerStatement = conn.prepareStatement("DELETE FROM courseinfo WHERE id IN ?");

            customerStatement.setInt(1, info.getId());

            customerStatement.executeUpdate();

            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }
}
