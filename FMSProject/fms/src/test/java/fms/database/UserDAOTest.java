package fms.database;
import fms.models.User;



import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * Created by paulrogers on 11/2/17.
 */
public class UserDAOTest {


    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void createTable() throws Exception {

        UserDAO userDAO = new UserDAO();

        userDAO.createTable();
    }


    @Test
    public void addUser() throws Exception {
        User user = new User("pauly", "foolproof", "pr@mail.net", "Paul", "Rogers", "m", "");
        User user1 = new User("paula", "foolproof", "pr@mail.net", "Paul", "Rogers", "m", "");

//        System.out.println("User info:");
//        System.out.println("------------------------------------");
//        System.out.println(user.getUserName());
//        System.out.println(user.getPassword());

        UserDAO userDAO = new UserDAO();

        userDAO.addUser(user);
    }


    @Test
    public void deleteUser() throws Exception
    {
        User user = new User("pauly", "foolproof", "pr@mail.net", "Paul", "Rogers", "m", "");
        User user1 = new User("paula", "foolproof", "pr@mail.net", "Paul", "Rogers", "m", "");

        UserDAO userDAO = new UserDAO();

        userDAO.deleteUser(user);


    }

    @Test
    public void getUser() throws Exception
    {

        UserDAO userDAO = new UserDAO();

//        User user = new User("pauly", "foolproof", "pr@mail.net", "Paul", "Rogers", "m", "");
//        User user1 = new User("paula", "foolproof", "pr@mail.net", "Paul", "Rogers", "m", "");

        User user = userDAO.getUser("paula");

//        System.out.println(user.toString());

        //The problem is that you appear to be creating a new random authToken here


//        System.out.println(user.toString());



//        ArrayList<User> userArrayList = new ArrayList<>();
//        userArrayList.add(user);
//        userArrayList.add(user1);

    }




    @Test
    public void getAllUsers() throws Exception
    {

        UserDAO userDAO = new UserDAO();

//        User user = new User("pauly", "foolproof", "pr@mail.net", "Paul", "Rogers", "m", "");
//        User user1 = new User("paula", "foolproof", "pr@mail.net", "Paul", "Rogers", "m", "");

        ArrayList<User> userArrayList = new ArrayList<>();


        userArrayList = userDAO.getAllUsers();


        for (int i = 0; i < userArrayList.size(); i++)
        {
            System.out.println(userArrayList.get(i).toString());
        }
    }








    @Test
    public void destroyTable() throws Exception
    {
        UserDAO userDAO = new UserDAO();

        userDAO.destroyTable();

    }


}