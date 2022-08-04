package jm.task.core.jdbc.util;

import java.sql.*;

public class Util {
    // реализуйте настройку соеденения с БД
    private static final String URL = "jdbc:mysql://localhost:3306/pp_bd_113";
    private static final String NAME = "root";
    private static final String PSWD = "root";
    private static Connection connection;

    public static Connection getConnection() {
        if (connection == null) {
            try {
                connection = DriverManager.getConnection(URL, NAME, PSWD);
                return connection;
            } catch (SQLException e) {
                System.out.println("Some connection problem");
            }
        }
        return connection;
    }


}
