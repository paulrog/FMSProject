package fms.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import fms.models.Person;
import fms.models.User;


public class PersonDAO
{
    private Person person = null;
    private Database db = new Database();

    public PersonDAO()
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
            System.out.println("Failure in PersonDAO safeClose()");
            db.closeConnection(false);
        }
    }

    public ArrayList<Person> getAllPeople()throws SQLException
    {
        ArrayList<Person> peopleArray = new ArrayList<>();

        String sqlGet = "Select * FROM people";
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
                String descendant = resultSet.getString("descendant");
                String personId = resultSet.getString("personId");
                String firstName = resultSet.getString("firstName");
                String lastName = resultSet.getString("lastName");
                String gender = resultSet.getString("gender");
                String father = resultSet.getString("father");
                String mother = resultSet.getString("mother");
                String spouse = resultSet.getString("spouse");



                peopleArray.add(new Person(descendant, personId, firstName, lastName, gender, father, mother, spouse));
            }



            safeClose(con,stmt);

        }
        catch (SQLException e)
        {
            e.printStackTrace();
            System.out.println("Looks like there was a failure in PersonDAO getAllPeople()");
            db.closeConnection(false);
        }

        return peopleArray;
    }

    public Person getPerson(String personId)throws SQLException
    {
        String sqlGet = "Select * FROM people WHERE personId = '" + personId + "'";
        PreparedStatement stmt = null;
        Connection con = null;


        try{
            con = db.openConnection();
            stmt = con.prepareStatement(sqlGet);

            ResultSet resultSet = stmt.executeQuery();
//            ResultSetMetaData metaData = resultSet.getMetaData();
//
//            metaData.

            while (resultSet.next())
            {
                //either figure out how to grab a whole User object from the result set or
                //piece one together from the individual data members.
                String descendant = resultSet.getString("descendant");
                personId = resultSet.getString("personId");
                String firstName = resultSet.getString("firstName");
                String lastName = resultSet.getString("lastName");
                String gender = resultSet.getString("gender");
                String father = resultSet.getString("father");
                String mother = resultSet.getString("mother");
                String spouse = resultSet.getString("spouse");


                Person person = new Person(descendant, personId, firstName, lastName, gender, father, mother, spouse);

                return person;
            }

        }
        catch(SQLException e)
        {
            e.printStackTrace();
            System.out.println("Failure in UserDAO getUser()");
            db.closeConnection(false);
            person = null;

        }

        finally {
            //I did this because before I was returning the person object before calling the safeClose() method.
            //The finally statement basically serves to run whatever it contains even if you've aready returned something
            safeClose(con,stmt);

        }
        return person;
    }



    public Person getPersonByName(String firstName)throws SQLException
    {
        String sqlGet = "Select * FROM people WHERE firstName = '" + firstName + "'";
        PreparedStatement stmt = null;
        Connection con = null;

        try{
            con = db.openConnection();
            stmt = con.prepareStatement(sqlGet);

            ResultSet resultSet = stmt.executeQuery();
//            ResultSetMetaData metaData = resultSet.getMetaData();
//
//            metaData.

            while (resultSet.next())
            {
                //either figure out how to grab a whole User object from the result set or
                //piece one together from the individual data members.
                String descendant = resultSet.getString("descendant");
                String personId = resultSet.getString("personId");
                firstName = resultSet.getString("firstName");
                String lastName = resultSet.getString("lastName");
                String gender = resultSet.getString("gender");
                String father = resultSet.getString("father");
                String mother = resultSet.getString("mother");
                String spouse = resultSet.getString("spouse");


                Person person = new Person(descendant, personId, firstName, lastName, gender, father, mother, spouse);

                return person;
            }

        }
        catch(SQLException e)
        {
            e.printStackTrace();
            System.out.println("Failure in UserDAO getUserByName()");
            db.closeConnection(false);
            person = null;

        }
        finally {
            //I did this because before I was returning the person object before calling the safeClose() method.
            //The finally statement basically serves to run whatever it contains even if you've aready returned something
            safeClose(con,stmt);

        }
        return person;
    }


    public void deletePerson(Person person)throws SQLException
    {
        String sqlDeleteRow = "DELETE FROM people "
                + "WHERE personId = '" + person.getPersonId() + "'";
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
            System.out.println("Failure in PersonDAO deleteUser()");
            db.closeConnection(false);
        }

    }


    public void createTable() throws SQLException
    {

        destroyTable();

        String sqlCreate = "CREATE TABLE IF NOT EXISTS people (descendant text NOT NULL, personId text NOT NULL PRIMARY KEY, "
                + "firstName text NOT NULL, lastName text NOT NULL, gender text NOT NULL, father text, mother text, spouse text)";
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
            System.out.println("Failure in PersonDAO createTable()");
            db.closeConnection(false);
        }



    }


    public void addPerson(Person person) throws SQLException
    {


        String sqlAddRow = "INSERT INTO people (descendant, personId, firstName, lastName, gender, father, mother, spouse)"
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement stmt = null;

        try{
            Connection con = db.openConnection();
            stmt = con.prepareStatement(sqlAddRow);

            stmt.setString(1, person.getDescendant());
            stmt.setString(2, person.getPersonId());
            stmt.setString(3, person.getFirstName());
            stmt.setString(4, person.getLastName());
            stmt.setString(5, person.getGender());
            stmt.setString(6, person.getFather());
            stmt.setString(7, person.getMother());
            stmt.setString(8, person.getSpouse());

            stmt.executeUpdate();

            safeClose(con, stmt);
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            System.out.println("Failure in PersonDAO addUser()");
            db.closeConnection(false);
        }
    }

















    public void destroyTable() throws SQLException
    {
        String sqlDrop = "DROP TABLE people";
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
            System.out.println("Failure in PersonDAO destroyTable()");
            db.closeConnection(false);
        }
    }


}