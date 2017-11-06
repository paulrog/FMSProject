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
    String authToken;

    public User(String userName, String password, String email, String firstName, String lastName, String gender, String authToken)
    {
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.authToken = authToken;

        //Generate a random authToken here
//        randomAuthToken();

    }

    public void randomAuthToken()
    {
        UUID uuid = UUID.randomUUID();
        String random = uuid.toString();

        this.authToken = random;
    }


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

    public String getAuthToken() {
        return authToken;
    }

    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }

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
        sb.append("authToken: " + getAuthToken() + "\n");
        return sb.toString();
    }

}
