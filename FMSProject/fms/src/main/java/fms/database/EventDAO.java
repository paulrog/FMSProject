package fms.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import fms.models.Event;
import fms.models.Person;


public class EventDAO
{
    private Event event = null;
    private Database db = new Database();

    public EventDAO()
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
            System.out.println("Failure in EventDAO safeClose()");
            db.closeConnection(false);
        }
    }


    public ArrayList<Event> getAllEvents()throws SQLException
    {
        ArrayList<Event> eventsArray = new ArrayList<>();

        String sqlGet = "Select * FROM events";
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
                String eventId = resultSet.getString("eventId");
                String personId = resultSet.getString("personId");
                int latitude = resultSet.getInt("latitude");
                int longitude = resultSet.getInt("longitude");
                String country = resultSet.getString("country");
                String city = resultSet.getString("city");
                String eventType = resultSet.getString("eventType");
                int year = resultSet.getInt("year");



                eventsArray.add(new Event(descendant, eventId, personId, latitude, longitude, country, city, eventType, year));
            }



            safeClose(con,stmt);

        }
        catch (SQLException e)
        {
            e.printStackTrace();
            System.out.println("Looks like there was a failure in EventDAO getAllEvents()");
            db.closeConnection(false);
        }

        return eventsArray;
    }

    public Event getEvent(String eventId)throws SQLException
    {
        String sqlGet = "Select * FROM events WHERE eventId = '" + eventId + "'";
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
                String descendant = resultSet.getString("descendant");
                eventId = resultSet.getString("eventId");
                String personId = resultSet.getString("personId");
                int latitude = resultSet.getInt("latitude");
                int longitude = resultSet.getInt("longitude");
                String country = resultSet.getString("country");
                String city = resultSet.getString("city");
                String eventType = resultSet.getString("eventType");
                int year = resultSet.getInt("year");


                Event event = new Event(descendant, eventId, personId, latitude, longitude, country, city, eventType, year);

                return event;
            }

            safeClose(con,stmt);
        }
        catch(SQLException e)
        {
            e.printStackTrace();
            System.out.println("Failure in EventDAO getEvent()");
            db.closeConnection(false);
            event = null;

        }
        return event;
    }



    public void deleteEvent(Event event)throws SQLException
    {
        String sqlDeleteRow = "DELETE FROM events "
                + "WHERE eventId = '" + event.getEventId() + "'";
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
            System.out.println("Failure in EventDAO deleteUser()");
            db.closeConnection(false);
        }

    }


    public void createTable() throws SQLException
    {

        destroyTable();

        String sqlCreate = "CREATE TABLE IF NOT EXISTS events (descendant text NOT NULL, eventId text NOT NULL PRIMARY KEY, "
                + "personId text NOT NULL, latitude int, longitude, country text NOT NULL, city text NOT NULL, "
                + "eventType text NOT NULL, year int)";
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
            System.out.println("Failure in EventDAO createTable()");
            db.closeConnection(false);
        }



    }


    public void addEvent(Event event) throws SQLException
    {


        String sqlAddRow = "INSERT INTO events (descendant, eventId, personId, latitude, longitude, country, city, eventType, year)"
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement stmt = null;

        try {
            Connection con = db.openConnection();
            stmt = con.prepareStatement(sqlAddRow);

            stmt.setString(1, event.getDescendant());
            stmt.setString(2, event.getEventId());
            stmt.setString(3, event.getPersonId());
            stmt.setInt(4, event.getLatitude());
            stmt.setInt(5, event.getLongitude());
            stmt.setString(6, event.getCountry());
            stmt.setString(7, event.getCity());
            stmt.setString(8, event.getEventType());
            stmt.setInt(9, event.getYear());

            stmt.executeUpdate();

            safeClose(con, stmt);

        }

        catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Failure in EventDAO addUser()");
            db.closeConnection(false);
        }
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    

    public void destroyTable() throws SQLException
    {
        String sqlDrop = "DROP TABLE events";
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
            System.out.println("Failure in EventDAO destroyTable()");
            db.closeConnection(false);
        }
    }


}