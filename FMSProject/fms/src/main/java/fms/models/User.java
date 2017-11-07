package fms.models;

import java.util.UUID;

public class User
{
    String userName;
    String password;
    String email;
    String firstName;
    String lastName;
    String gender;
    String personId;


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPersonId() {
        return personId;
    }

    public void setPersonId(String personId) {
        this.personId = personId;
    }

    public User(String userName, String password, String email, String firstName, String lastName, String gender, String personId)
    {
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.personId = personId;
    }

//    public void randomAuthToken()
//    {
//        UUID uuid = UUID.randomUUID();
//        String random = uuid.toString();
//
//        this.authToken = random;
//    }




    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();

        sb.append("userName: " + getUserName() + "\n");
        sb.append("password: " + getPassword() + "\n");
        sb.append("email: " + getEmail() + "\n");
        sb.append("firstName: " + getFirstName() + "\n");
        sb.append("lastName: " + getLastName() + "\n");
        sb.append("gender: " + getGender() + "\n");
        sb.append("personId: " + getPersonId() + "\n");
        return sb.toString();
    }

}
