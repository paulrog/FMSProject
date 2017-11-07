package fms.services;

import fms.database.*;
import fms.models.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Random;
import java.util.UUID;


public class FillService
{
    private int count = 0;
    private String descendant = "";
    private PersonDAO personDAO = new PersonDAO();
//    private UserDAO userDAO = new UserDAO();
    private EventDAO eventDAO = new EventDAO();



    public void setDescendant(String userName)
    {
        descendant = userName;
    }

    public String randomMale()
    {
        ArrayList<String> maleNames = new ArrayList<>();

        maleNames.add("Paul");
        maleNames.add("Stephen");
        maleNames.add("Mike");
        maleNames.add("Jimmy");
        maleNames.add("LeBron");

        Random rand = new Random();
        int random = rand.nextInt(maleNames.size());

        return maleNames.get(random);

    }

    public String randomFemale()
    {
        ArrayList<String> femaleNames = new ArrayList<>();

        femaleNames.add("Beyonce");
        femaleNames.add("Elen");
        femaleNames.add("Hermione");
        femaleNames.add("Jennifer");
        femaleNames.add("Serena");

        Random rand = new Random();
        int random = rand.nextInt(femaleNames.size());

        return femaleNames.get(random);

    }

    public String randomApellido()
    {
        ArrayList<String> lastNames = new ArrayList<>();

        lastNames.add("James");
        lastNames.add("Carr");
        lastNames.add("Tyson");
        lastNames.add("Knowles");
        lastNames.add("DeGeneres");
        lastNames.add("Granger");
        lastNames.add("Lawrence");
        lastNames.add("Williams");

        Random rand = new Random();
        int random = rand.nextInt(lastNames.size());

        return lastNames.get(random);

    }


    public void testRandoms()
    {
        System.out.println("Random male: " + randomMale() + " " + randomApellido());
        System.out.println("Random female: " + randomFemale() + " " + randomApellido());

    }

    public String randomId()
    {


        return UUID.randomUUID().toString();
    }

    public void makePeople(Person p, String dadId, int numGeneration)throws SQLException
    {
        //registerService will take charge of adding the new user (to the user table AND the people table)
        //and THEN calling this method

//        System.out.println(p.getFirstName());
        descendant = p.getDescendant();
        try {

            if ((personDAO.getPerson(p.getFather()) == null) && (count < numGeneration)) {
                Person dad = new Person(descendant, p.getFather(), randomMale(), randomApellido(), "m", randomId(), randomId(), p.getMother());
                personDAO.addPerson(dad);
                count++;
                makePeople(dad, dad.getFather(), numGeneration);
            }
            if ((personDAO.getPerson(p.getMother()) == null) && (count < numGeneration)) {
                //when creating the new mom I've got to try to connect her to the most recent dad in the spouse column

                String dadSpouseID = personDAO.getPerson(p.getFather()).getSpouse();
                Person mom = new Person(descendant, dadSpouseID, randomFemale(), randomApellido(), "f", randomId(), randomId(), randomId());
                personDAO.addPerson(mom);
                count++;
                makePeople(mom, mom.getFather(), numGeneration);
            }

            count--;
        }catch(SQLException e)
        {
            e.printStackTrace();
            System.out.println("Failure in FillService makePeople()");

        }



    }



}
