package database;

import java.sql.*;

/**
 * Created by rjpvr on 5-10-2016.
 */
public class DatabaseConnector {


    private static Connection connection = null;

    public static Connection connect() throws ClassNotFoundException, SQLException {

        System.out.println("-------- Oracle JDBC Connection Testing ------");

        try {

            Class.forName("oracle.jdbc.driver.OracleDriver");

        } catch (ClassNotFoundException e) {

            System.out.println("Where is your Oracle JDBC Driver?");
            e.printStackTrace();

            throw e;
        }

        System.out.println("Oracle JDBC Driver Registered!");

        try {

            connection = DriverManager.getConnection(
                "jdbc:oracle:thin:@localhost:1521:xe", "case1",
                "test");

        } catch (SQLException e) {

            System.out.println("Connection Failed! Check output console");
            e.printStackTrace();
            throw e;

        }

        if (connection != null) {
            return connection;
        } else {
            throw new SQLException("Failed to setup the connection");
        }
    }
}
