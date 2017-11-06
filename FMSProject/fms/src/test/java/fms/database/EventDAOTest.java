package fms.database;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import fms.models.Event;

import static org.junit.Assert.*;

/**
 * Created by paulrogers on 11/3/17.
 */
public class EventDAOTest {
    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void createTable() throws Exception {
        EventDAO eventDAO = new EventDAO();

        eventDAO.createTable();
    }

    @Test
    public void addEvent()throws Exception
    {
        Event event = new Event("Paul", "3939", "001", -5, 17, "Peru", "Lima", "Alien Abduction", 1988);
        Event event1 = new Event("Paul", "2525", "001", -5, 17, "Spain", "Barcelona", "Coronation", 1397);

        EventDAO eventDAO = new EventDAO();

        eventDAO.addEvent(event1);
    }

    @Test
    public void deleteEvent()throws Exception
    {
        Event event = new Event("Paul", "3939", "001", -5, 17, "Peru", "Lima", "Alien Abduction", 1988);
        Event event1 = new Event("Paul", "2525", "001", -5, 17, "Spain", "Barcelona", "Coronation", 1397);

        EventDAO eventDAO = new EventDAO();

        eventDAO.deleteEvent(event);
    }


    @Test
    public void getEvent()throws Exception
    {
//        Event event = new Event("Paul", "1919", "001", -5, 17, "Peru", "Lima", "Alien Abduction", 1988);
//        Event event1 = new Event("Paul", "1921", "001", -5, 17, "Peru", "Lima", "Alien Abduction", 1988);

        EventDAO eventDAO = new EventDAO();
        Event event = eventDAO.getEvent("3939");
        System.out.println(event.toString());
    }

    @Test
    public void getAllEvents()throws Exception
    {
//        Event event = new Event("Paul", "1919", "001", -5, 17, "Peru", "Lima", "Alien Abduction", 1988);
//        Event event1 = new Event("Paul", "1921", "001", -5, 17, "Peru", "Lima", "Alien Abduction", 1988);

        EventDAO eventDAO = new EventDAO();

        ArrayList<Event> eventsArray = eventDAO.getAllEvents();

        for (int i = 0; i < eventsArray.size(); i++)
        {
            System.out.println(eventsArray.get(i).toString());
        }
    }









    @Test
    public void destroyTable() throws Exception {
        EventDAO eventDAO = new EventDAO();

        eventDAO.destroyTable();
    }

}