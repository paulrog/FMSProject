package fms.models;

public class Event
{
    String eventId;
    String descendant;
    String personId;
    int latitude;
    int longitude;
    String country;
    String city;
    String eventType;
    int year;

    public Event(String descendant, String eventId, String personId, int latitude, int longitude, String country, String city, String eventType, int year)
    {
        this.eventId = eventId;
        this.descendant = descendant;
        this.personId = personId;
        this.latitude = latitude;
        this.longitude = longitude;
        this.country = country;
        this.city = city;
        this.eventType = eventType;
        this.year = year;
    }

    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    public String getDescendant() {
        return descendant;
    }

    public void setDescendant(String descendant) {
        this.descendant = descendant;
    }

    public String getPersonId() {
        return personId;
    }

    public void setPersonId(String personId) {
        this.personId = personId;
    }

    public int getLatitude() {
        return latitude;
    }

    public void setLatitude(int latitude) {
        this.latitude = latitude;
    }

    public int getLongitude() {
        return longitude;
    }

    public void setLongitude(int longitude) {
        this.longitude = longitude;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }


    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();

        sb.append("descendant: " + getDescendant() + "\n");
        sb.append("eventId: " + getEventId() + "\n");
        sb.append("personId: " + getPersonId() + "\n");
        sb.append("latitude: " + getLatitude() + "\n");
        sb.append("longitude: " + getLongitude() + "\n");
        sb.append("country: " + getCountry() + "\n");
        sb.append("city: " + getCity() + "\n");
        sb.append("eventType: " + getEventType() + "\n");
        sb.append("year: " + getYear() + "\n");
        return sb.toString();
    }



















}
