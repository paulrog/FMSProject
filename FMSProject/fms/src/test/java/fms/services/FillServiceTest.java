package fms.services;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import fms.database.PersonDAO;
import fms.models.Person;

import static org.junit.Assert.*;

/**
 * Created by paulrogers on 11/6/17.
 */
public class FillServiceTest {
    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testRandoms() throws Exception {

        FillService fillService = new FillService();

        fillService.testRandoms();
    }

    @Test
    public void makePeople() throws Exception {

        FillService fillService = new FillService();
        PersonDAO personDAO = new PersonDAO();

        Person p = personDAO.getPersonByName("Paul");
        String dadID = p.getFather();

//        System.out.println(p.toString());




        fillService.makePeople(p, dadID, 2);
    }
}