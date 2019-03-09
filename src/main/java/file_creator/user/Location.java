package file_creator.user;

import file_creator.RandomDataGenerator;

public class Location {
    private String country;
    private String state;
    private String city;
    private String street;
    private int bldNumber;
    private int aptNumber;

    public String getCountry() {
        return country;
    }

    public String getState() {
        return state;
    }

    public String getCity() {
        return city;
    }

    public String getStreet() {
        return street;
    }

    public int getBldNumber() {
        return bldNumber;
    }
    public int getAptNumber() {
        return aptNumber;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setBldNumber(int bldNumber) {
        this.bldNumber = RandomDataGenerator.getBldNumber();
    }

    public void setAptNumber(int aptNumber) {
        this.aptNumber = RandomDataGenerator.getAptNumber();
    }
}
