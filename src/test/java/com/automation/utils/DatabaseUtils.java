package com.automation.utils;

import java.sql.*;

public class DatabaseUtils {
    private static Connection con;
    private static Statement stmt;
    private static ResultSet result;
    private static String hostName = ConfigFileReaderUtils.getProperty("db.host");
    private static String userName = ConfigFileReaderUtils.getProperty("db.username");
    private static String password = ConfigFileReaderUtils.getProperty("db.password");

    public static void initDatabase() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(hostName, userName, password);
            stmt = con.createStatement();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public static ResultSet executeQuery(String query) {
        try {
            result = stmt.executeQuery(query);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public static void closeDatabase() {
        try {
            con.close();
            stmt.close();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

}
