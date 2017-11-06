package fms.database;

import fms.models.Person;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * Created by paulrogers on 11/3/17.
 */
public class PersonDAOTest {


    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void createTable() throws Exception {
        PersonDAO personDAO = new PersonDAO();

        personDAO.createTable();

    }


    @Test
    public void addPerson() throws Exception
    {
        Person person = new Person("Paul", "99", "Papi", "Rogers", "m", "000379", "00021", "015");
        Person person1 = new Person("Paul", "010", "Junior", "Rogers", "m", "0001", "0002", "002");

        PersonDAO personDAO = new PersonDAO();

        personDAO.addPerson(person);

    }

    @Test
    public void deletePerson() throws Exception
    {
        Person person = new Person("Paul", "001", "Junior", "Rogers", "m", "0001", "0002", "002");
        Person person1 = new Person("Paul", "010", "Junior", "Rogers", "m", "0001", "0002", "002");

        PersonDAO personDAO = new PersonDAO();

        personDAO.deletePerson(person);

    }




    @Test
    public void getPerson() throws Exception
    {
//        Person person = new Person("Paul", "001", "Junior", "Rogers", "m", "0001", "0002", "002");
//        Person person1 = new Person("Paul", "010", "Junior", "Rogers", "m", "0001", "0002", "002");


        PersonDAO personDAO = new PersonDAO();


        Person person = personDAO.getPerson("99");
        System.out.println(person.toString());


    }
    @Test
    public void getAllPeople() throws Exception
    {
//        Person person = new Person("Paul", "001", "Junior", "Rogers", "m", "0001", "0002", "002");
//        Person person1 = new Person("Paul", "010", "Junior", "Rogers", "m", "0001", "0002", "002");


        PersonDAO personDAO = new PersonDAO();

        ArrayList<Person> peopleArray = personDAO.getAllPeople();

        for (int i = 0; i < peopleArray.size(); i++)
        {
            System.out.println(peopleArray.get(i).toString());
        }


    }



    @Test
    public void destroyTable() throws Exception {
        PersonDAO personDAO = new PersonDAO();

        personDAO.destroyTable();

    }

}