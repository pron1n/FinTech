package file_creator.user;

import file_creator.RandomDataGenerator;

import java.util.HashMap;
import java.util.Map;

public class Location {
    private String state;
    private String city;
    private String street;
    private int bldNumber;
    private int aptNumber;
    private static Map countries = new HashMap<String, String>() {{
        put("AU", "Australia");
        put("BR", "Brazil");
        put("CA", "Canada");
        put("CH", "Switzerland");
        put("DE", "Germany");
        put("DK", "Denmark");
        put("ES", "Spain");
        put("FI", "Finland");
        put("FR", "France");
        put("GB", "Great Britain");
        put("IE", "Ireland");
        put("IR", "Iran");
        put("NO", "Norway");
        put("NL", "Netherlands");
        put("NZ", "New Zealand");
        put("TR", "Turkey");
        put("US", "United States");
    }};

    public Location() {
        this.bldNumber = RandomDataGenerator.getBldNumber();
        this.aptNumber = RandomDataGenerator.getAptNumber();
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

    public static String getFullCountryName(String shortName) {
        return (String) countries.get(shortName);
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
}