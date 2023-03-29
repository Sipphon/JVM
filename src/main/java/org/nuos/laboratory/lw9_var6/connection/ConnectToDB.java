package org.nuos.laboratory.lw9_var6.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectToDB {

    public static final String URL = "jdbc:mysql://localhost:3306/house";
    public static final String USER = "root";
    public static final String PASSWORD = "root";

    private ConnectToDB() {
    }

    public static Connection getConnection() {
        java.sql.Connection connection = null;
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }
}
