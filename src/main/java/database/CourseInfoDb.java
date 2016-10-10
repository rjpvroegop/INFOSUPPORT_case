package database;

import model.CourseInfo;

import java.sql.*;
import java.util.ArrayList;

public class CourseInfoDb {
    private static Connection conn = null;

    public void saveCourseInfo(CourseInfo info){
        try {
            conn = DatabaseConnector.connect();

            PreparedStatement courseStatement = conn.prepareStatement("insert into courseinfo (name, description) values(?, ?)");

            courseStatement.setString(1, info.getName());
            courseStatement.setString(2, info.getDescription());

            courseStatement.executeUpdate();

            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ArrayList<CourseInfo> getCourseInfo(){
        ArrayList<CourseInfo>customers = new ArrayList<>();
        try {
            conn = DatabaseConnector.connect();

            PreparedStatement customerStatement = conn.prepareStatement("SELECT * FROM courseinfo");
            ResultSet rs = customerStatement.executeQuery();

            while(rs.next()){
                //Retrieve by column name
                int id  = rs.getInt("id");
                String name = rs.getString("name");
                String desc = rs.getString("description");

                CourseInfo customer = CourseInfo.builder().id(id).name(name).description(desc).build();
                customers.add(customer);
            }

            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return customers;
    }

    public CourseInfo getCourseInfo(int id) {
        CourseInfo.CourseInfoBuilder courseInfoBuilder = CourseInfo.builder();
        try {
            conn = DatabaseConnector.connect();

            PreparedStatement customerStatement = conn.prepareStatement("SELECT * FROM courseinfo WHERE id = " + id);
            ResultSet rs = customerStatement.executeQuery();

            while(rs.next()){
                //Retrieve by column name
                int cid  = rs.getInt("id");
                String name = rs.getString("name");
                String desc = rs.getString("description");

                courseInfoBuilder = courseInfoBuilder.id(cid).name(name).description(desc);
            }

            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return courseInfoBuilder.build();
    }

    public void updateCourseInfo(CourseInfo info) {
        try {
            conn = DatabaseConnector.connect();

            PreparedStatement infoStatement = conn.prepareStatement("UPDATE courseinfo SET name = ?, description = ? WHERE id IN ?");

            infoStatement.setString(1, info.getName());
            infoStatement.setString(2, info.getDescription());
            infoStatement.setInt(3, info.getId());

            infoStatement.executeUpdate();

            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void removeCourseInfo(CourseInfo info) {
        try {
            conn = DatabaseConnector.connect();

            PreparedStatement customerStatement = conn.prepareStatement("DELETE FROM courseinfo WHERE id IN ?");

            customerStatement.setInt(1, info.getId());

            customerStatement.executeUpdate();

            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
