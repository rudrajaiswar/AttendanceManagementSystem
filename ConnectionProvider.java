/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author Sahil
 */
public class ConnectionProvider {

    private static final String DB_NAME = "attendenceJFramebd";
    private static final String DB_URL = "jdbc:mysql://localhost:3306/";
    private static final String DB_USERNAME = "root";
    private static final String DB_PASSWORD = "@Rudra19102004";

    public static Connection getCon() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            // 🔹 Connect without database (for checking / creating DB)
            Connection con = DriverManager.getConnection(
                    DB_URL + "?useSSL=false&allowPublicKeyRetrieval=true",
                    DB_USERNAME,
                    DB_PASSWORD
            );

            if (!databaseExists(con, DB_NAME)) {
                createDatabase(con, DB_NAME);
            }

            // 🔹 Connect with database
            con = DriverManager.getConnection(
                    DB_URL + DB_NAME + "?useSSL=false&allowPublicKeyRetrieval=true",
                    DB_USERNAME,
                    DB_PASSWORD
            );

            return con;

        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    private static boolean databaseExists(Connection con, String dbName) throws Exception {
        Statement stmt = con.createStatement();
        return stmt.executeQuery("SHOW DATABASES LIKE '" + dbName + "'").next();
    }

    private static void createDatabase(Connection con, String dbName) throws Exception {
        Statement stmt = con.createStatement();
        stmt.executeUpdate("CREATE DATABASE " + dbName);
        System.out.println("DATABASE '" + dbName + "' CREATED SUCCESSFULLY.");
    }
}
