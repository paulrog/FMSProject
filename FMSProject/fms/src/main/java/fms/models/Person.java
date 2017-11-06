package fms.models;


public class Person
{
    String personId;
    String descendant;
    String firstName;
    String lastName;
    String gender;
    String father;
    String mother;
    String spouse;

    public Person(String descendant, String personId, String firstName, String lastName, String gender, String father, String mother, String spouse)
    {

        this.personId = personId;
        this.descendant = descendant;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.father = father;
        this.mother = mother;
        this.spouse = spouse;
    }

    public String getPersonId() {
        return personId;
    }

    public void setPersonId(String personId) {
        this.personId = personId;
    }

    public String getDescendant() {
        return descendant;
    }

    public void setDescendant(String descendant) {
        this.descendant = descendant;
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

    public String getFather() {
        return father;
    }

    public void setFather(String father) {
        this.father = father;
    }

    public String getMother() {
        return mother;
    }

    public void setMother(String mother) {
        this.mother = mother;
    }

    public String getSpouse() {
        return spouse;
    }

    public void setSpouse(String spouse) {
        this.spouse = spouse;
    }


    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();

        sb.append("descendant: " + getDescendant() + "\n");
        sb.append("personId: " + getPersonId() + "\n");
        sb.append("firstName: " + getFirstName() + "\n");
        sb.append("lastName: " + getLastName() + "\n");
        sb.append("gender: " + getGender() + "\n");
        sb.append("father: " + getFather() + "\n");
        sb.append("mother: " + getMother() + "\n");
        sb.append("spouse: " + getSpouse() + "\n");
        return sb.toString();
    }
}