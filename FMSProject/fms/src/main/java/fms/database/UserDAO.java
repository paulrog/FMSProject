package fms.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.UUID;

import fms.models.User;


public class UserDAO
{
    private User user = null;
    private Database db = new Database();
    public UserDAO()
    {}

    public void safeClose(Connection con, PreparedStatement stmt) throws SQLException
    {
        try {


            if (!con.isClosed()) {
                System.out.println("it was open");
                stmt.close();
                db.closeConnection(true);
            }
            else
            {
                System.out.println("already closed");

            }
        }catch(SQLException e)
        {
            e.printStackTrace();
            System.out.println("Failure in UserDAO safeClose()");
            db.closeConnection(false);
        }
    }

    public String randomAuthToken()
    {
        String authToken;
        UUID uuid = UUID.randomUUID();
        String random = uuid.toString();

        authToken = random;
        return authToken;
    }


    public void updateRow(String columnName, String newValue)
    {

    }


//    public void tableForGetUsers()throws SQLException
//    {
//        //Create a new table that contains the Users asked for
//        String sqlCreate = "CREATE TABLE IF NOT EXISTS getUsers (userName text NOT NULL PRIMARY KEY, password text NOT NULL, "
//                + "email text NOT NULL, firstName text NOT NULL, lastName text NOT NULL, gender text NOT NULL, "
//                + "authToken text NOT NULL)";
//        PreparedStatement stmt = null;
//
//        try{
//            Connection con = db.openConnection();
//
//
//
//            if (con == null)
//            {
//                System.out.println("connection is null");
//            }
//            stmt = con.prepareStatement(sqlCreate);
//            stmt.executeUpdate();
//
//
//            safeClose(con, stmt);
//
//
//
//        }
//        catch(SQLException e)
//        {
//            e.printStackTrace();
//            System.out.println("Failure in UserDAO tableForGetUsers()");
//            db.closeConnection(false);
//        }
//    }

    public ArrayList<User> getAllUsers()throws SQLException
    {
        ArrayList<User> usersArray = new ArrayList<>();

        String sqlGet = "Select * FROM users";
        PreparedStatement stmt = null;

        try
        {
            Connection con = db.openConnection();
            stmt = con.prepareStatement(sqlGet);
            ResultSet resultSet = stmt.executeQuery();


            while (resultSet.next())
            {
                //either figure out how to grab a whole User object from the result set or
                //piece one together from the individual data members.
                String userName = resultSet.getString("userName");
                String password = resultSet.getString("password");
                String email = resultSet.getString("email");
                String firstName = resultSet.getString("firstName");
                String lastName = resultSet.getString("lastName");
                String gender = resultSet.getString("gender");
                String authToken = resultSet.getString("authToken");


//                User user = new User(userName, password, email, firstName, lastName, gender, authToken);

                usersArray.add(new User(userName, password, email, firstName, lastName, gender, authToken));
            }



            safeClose(con,stmt);

        }
        catch (SQLException e)
        {
            e.printStackTrace();
            System.out.println("Looks like there was a failure in UserDAO getAllUsers()");
            db.closeConnection(false);
        }

        return usersArray;
    }

    public User getUser(String userName)throws SQLException
    {
        String sqlGet = "Select * FROM users WHERE userName = '" + userName + "'";
        PreparedStatement stmt = null;

        try{
            Connection con = db.openConnection();
            stmt = con.prepareStatement(sqlGet);

            ResultSet resultSet = stmt.executeQuery();
//            ResultSetMetaData metaData = resultSet.getMetaData();
//
//            metaData.

            while (resultSet.next())
            {
                //either figure out how to grab a whole User object from the result set or
                //piece one together from the individual data members.
                userName = resultSet.getString("userName");
                String password = resultSet.getString("password");
                String email = resultSet.getString("email");
                String firstName = resultSet.getString("firstName");
                String lastName = resultSet.getString("lastName");
                String gender = resultSet.getString("gender");
                String authToken = resultSet.getString("authToken");


                User user = new User(userName, password, email, firstName, lastName, gender, authToken);

                return user;
            }

            safeClose(con,stmt);
        }
        catch(SQLException e)
        {
            e.printStackTrace();
            System.out.println("Failure in UserDAO getUser()");
            db.closeConnection(false);
            user = null;

        }
        return user;
    }

    public void createTable() throws SQLException
    {

        destroyTable();
        String sqlCreate = "CREATE TABLE IF NOT EXISTS users (userName text NOT NULL PRIMARY KEY, password text NOT NULL, "
                + "email text NOT NULL, firstName text NOT NULL, lastName text NOT NULL, gender text NOT NULL, "
                + "authToken text NOT NULL)";
        PreparedStatement stmt = null;

        try{
            Connection con = db.openConnection();



            if (con == null)
            {
                System.out.println("connection is null");
            }
            stmt = con.prepareStatement(sqlCreate);
            stmt.executeUpdate();


            safeClose(con, stmt);



        }
        catch(SQLException e)
        {
            e.printStackTrace();
            System.out.println("Failure in UserDAO createTable()");
            db.closeConnection(false);
        }



    }


    public void deleteUser(User user)throws SQLException
    {
        String sqlDeleteRow = "DELETE FROM users "
                + "WHERE userName = '" + user.getUserName() + "'";
        PreparedStatement stmt = null;

        try{
            Connection con = db.openConnection();

            stmt = con.prepareStatement(sqlDeleteRow);

            stmt.executeUpdate();

            safeClose(con, stmt);
        }
        catch(SQLException e)
        {
            e.printStackTrace();
            System.out.println("Failure in UserDAO deleteUser()");
            db.closeConnection(false);
        }

    }


    public void addUser(User user, String tableName) throws SQLException
    {
        String sqlAddRow = "INSERT INTO " + tableName + " (userName, password, email, firstName, lastName, gender, authToken)"
                + "VALUES (?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement stmt = null;

        try
        {
            Connection con = db.openConnection();

            stmt = con.prepareStatement(sqlAddRow);

            stmt.setString(1, user.getUserName());
            stmt.setString(2, user.getPassword());
            stmt.setString(3, user.getEmail());
            stmt.setString(4, user.getFirstName());
            stmt.setString(5, user.getLastName());
            stmt.setString(6, user.getGender());

            //generate random authToken
            stmt.setString(7, randomAuthToken());


//            System.out.println("User info (in addUser(). NOT the junit4 test):");
//            System.out.println("------------------------------------");
//            System.out.println(user.getUserName());
//            System.out.println(user.getPassword());
//            System.out.println(user.getAuthToken());




//            if (stmt.executeUpdate() != 2)
//            {
//                System.out.println("# of Rows Added was NOT 2");
//                throw new SQLException();
//            }

            stmt.executeUpdate();

//            System.out.println("The toString:::");
//            System.out.println(user.toString());

            safeClose(con, stmt);
        }
        catch(SQLException e)
        {
            e.printStackTrace();
            System.out.println("Failure in UserDAO addUser()");
            db.closeConnection(false);
        }

    }



































    public void destroyTable() throws SQLException
    {
        String sqlDrop = "DROP TABLE users";
        PreparedStatement stmt = null;
        try
        {
            Connection con = db.openConnection();

            stmt = con.prepareStatement(sqlDrop);
            stmt.executeUpdate();

            safeClose(con,stmt);


        }
        catch(SQLException e)
        {
            e.printStackTrace();
            System.out.println("Failure in UserDAO destroyTable()");
            db.closeConnection(false);
        }
    }


}