package fms.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.UUID;


import fms.models.User;
import fms.models.Person;
import fms.services.FillService;


public class UserDAO
{

    private String descendant = "";
    private FillService fillService = new FillService();
//    Bad to declare it for some reason. Better to set it to null;
//    private FillService fillService = new FillService();
    private PersonDAO personDAO = new PersonDAO();
    private UserDAO userDAO = null;
    private User aUser = null;
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

    public String randomPersonId()
    {
//        String authToken;
//        UUID uuid = UUID.randomUUID();
//        String random = uuid.toString();
//
//        authToken = random;
//        return authToken;

        return UUID.randomUUID().toString();
    }


//    public void updateRow(String columnName, String newValue)
//    {
//
//    }


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
                String personId = resultSet.getString("personId");


//                User user = new User(userName, password, email, firstName, lastName, gender, authToken);

                usersArray.add(new User(userName, password, email, firstName, lastName, gender, personId));
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
                String personId = resultSet.getString("personId");


                User user = new User(userName, password, email, firstName, lastName, gender, personId);

                return user;
            }

            safeClose(con,stmt);
        }
        catch(SQLException e)
        {
            e.printStackTrace();
            System.out.println("Failure in UserDAO getUser()");
            db.closeConnection(false);
            return null;

        }
        return null;
    }

    public void createAuthTokenTable()throws SQLException
    {
        String sqlCreate = "CREATE TABLE IF NOT EXISTS authTokens (authToken text NOT NULL PRIMARY KEY, personId text NOT NULL)";
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
            System.out.println("Failure in UserDAO createAuthTokenTable()");
            db.closeConnection(false);
        }
    }


    public void createTable() throws SQLException
    {

        destroyTable();
        String sqlCreate = "CREATE TABLE IF NOT EXISTS users (userName text NOT NULL PRIMARY KEY, password text NOT NULL, "
                + "email text NOT NULL, firstName text NOT NULL, lastName text NOT NULL, gender text NOT NULL, "
                + "personId text NOT NULL)";
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

        createAuthTokenTable();


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


    public void makeUserIntoPerson(User user)throws SQLException
    {

//        aUser = getUser(user.getUserName());
        String father = "";
        String mother = "";
        String spouse = "";

        String sqlAddRow = "INSERT INTO people" + " (descendant, personId, firstName, lastName, gender, father, mother, spouse)"
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement stmt = null;

        try
        {
            Connection con = db.openConnection();

            stmt = con.prepareStatement(sqlAddRow);

            stmt.setString(1, aUser.getUserName());
            stmt.setString(2, aUser.getPersonId());
            stmt.setString(3, aUser.getFirstName());
            stmt.setString(4, aUser.getLastName());
            stmt.setString(5, aUser.getGender());

            //generate random authToken
            father = randomPersonId();
            stmt.setString(6, father);

            mother = randomPersonId();
            stmt.setString(7, mother);

            spouse = randomPersonId();
            stmt.setString(8, spouse);


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


            safeClose(con, stmt);
        }
        catch(SQLException e)
        {
            e.printStackTrace();
            System.out.println("Failure in UserDAO makeUserIntoPerson()");
            db.closeConnection(false);
        }
    }


    public void addUser(User user) throws SQLException
    {

//        String userName = "";
//        String password = "";
//        String email = "";
//        String firstName = "";
//        String lastName = "";
//        String gender = "";
        String personId = "";
        //Make this method automatically add a row to the People table for the new user

        String sqlAddRow = "INSERT INTO users" + " (userName, password, email, firstName, lastName, gender, personId)"
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
            personId = randomPersonId();
            stmt.setString(7, personId);


            user.setPersonId(personId);
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

            aUser = user;






            safeClose(con, stmt);
        }
        catch(SQLException e)
        {
            e.printStackTrace();
            System.out.println("Failure in UserDAO addUser()");
            db.closeConnection(false);
        }


        storeAuthToken(personId);
        makeUserIntoPerson(user);
//        setDescendantForFillService(aUser.getUserName());

        Person p = personDAO.getPerson(aUser.getPersonId());
        String dadID = p.getFather();
        fillService.makePeople(p, dadID, 2);



    }



//    public void setDescendantForFillService(String userName)
//    {
//
//        fillService.setDescendant(userName);
//    }




    public void storeAuthToken(String personId)throws SQLException
    {
        String sqlAddRow = "INSERT INTO authTokens" + " (authToken, personId)"
                + "VALUES (?, ?)";
        PreparedStatement stmt = null;

        String authToken = "";

        try
        {
            Connection con = db.openConnection();

            stmt = con.prepareStatement(sqlAddRow);

            //generate random authToken
            authToken = randomPersonId();

            stmt.setString(1, authToken);
            stmt.setString(2, personId);


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
            System.out.println("Failure in UserDAO storeAuthToken()");
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

        destroyAuthTokenTable();
    }

    public void destroyAuthTokenTable() throws SQLException
    {
        String sqlDrop = "DROP TABLE authTokens";
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
            System.out.println("Failure in UserDAO destroyAuthTokenTable()");
            db.closeConnection(false);
        }
    }




}