package fms.database;

import java.sql.*;
import java.util.*;

public class Database {

    static {
        try {
            final String driver = "org.sqlite.JDBC";
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private Connection conn;

    public Connection openConnection() throws SQLException {
        try {
            final String CONNECTION_URL = "jdbc:sqlite:fmsDatabase.sqlite";

            // Open a database connection
            conn = DriverManager.getConnection(CONNECTION_URL);

            // Start a transaction
            conn.setAutoCommit(false);
            return conn;
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("openConnection failed");
            return null;
        }

    }

    public void closeConnection(boolean commit) throws SQLException {

        try {
            if (commit) {
                conn.commit();
            } else {
                conn.rollback();
            }

            conn.close();
            conn = null;
        } catch (SQLException e) {
            throw new SQLException("closeConnection failed", e);
        }
    }
}
