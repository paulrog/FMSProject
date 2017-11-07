package fms.services;

import fms.database.*;
import fms.models.Person;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.UUID;


public class ClearService
{


    private UserDAO userDAO = new UserDAO();
    private PersonDAO personDAO = new PersonDAO();
    private EventDAO eventDAO = new EventDAO();


//    public void safeClose(Connection con, PreparedStatement stmt) throws SQLException
//    {
//        try {
//
//
//            if (!con.isClosed()) {
//                System.out.println("it was open");
//                stmt.close();
//                db.closeConnection(true);
//            }
//            else
//            {
//                System.out.println("already closed");
//
//            }
//        }catch(SQLException e)
//        {
//            e.printStackTrace();
//            System.out.println("Failure in UserDAO safeClose()");
//            db.closeConnection(false);
//        }
//    }



    public void clearAll()throws SQLException
    {
        try {

            userDAO.createTable();
            personDAO.createTable();
            eventDAO.createTable();
        }catch (SQLException e)
        {
            e.printStackTrace();
            System.out.println("Failure in ClearService clearAll()");


        }

    }



}
