package file_creator.user;

import file_creator.RandomDataGenerator;

import java.io.IOException;
import java.util.ArrayList;

public class User {
    private String gender;
    public Name name;
    public Location location;
    public DateOfBirth dob;
    private String nat;
    private String inn;
    private int postcode;

    public String getGender() {
        return gender;
    }

    public Name getName() {
        return name;
    }

    public Location getLocation() {
        return location;
    }

    public DateOfBirth getDob() {
        return dob;
    }

    public String getNat() {
        return nat;
    }

    public String getInn() {
        return inn;
    }

    public int getPostcode() {
        return postcode;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setName(Name name) {
        this.name = name;
    }

    public void setNameByGender(String gender) throws IOException {
        if (gender.equals("М") || gender.equals("male"))
            this.name.setFirst(RandomDataGenerator.getRandomValueFromResourceFile("Male_names.txt"));
        else
            this.name.setFirst(RandomDataGenerator.getRandomValueFromResourceFile("Female_names.txt"));
    }

    public void setSurnameByGender(String gender) throws IOException {
        if (gender.equals("М") || gender.equals("male"))
            this.name.setLast(RandomDataGenerator.getRandomValueFromResourceFile("Male_surnames.txt"));
        else
            this.name.setLast(RandomDataGenerator.getRandomValueFromResourceFile("Female_surnames.txt"));
    }

    public void setPatronymicByGender(String gender) throws IOException {
        if (gender.equals("М") || gender.equals("male"))
            this.name.setPatronymic(RandomDataGenerator.getRandomValueFromResourceFile("Male_patronymics.txt"));
        else
            this.name.setPatronymic(RandomDataGenerator.getRandomValueFromResourceFile("Female_patronymics.txt"));
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public void setDob(DateOfBirth dob) {
        this.dob = dob;
    }

    public void setInn(String inn) {
        this.inn = RandomDataGenerator.getInn();
    }

    public void setPostcode(int postcode) {
        this.postcode = RandomDataGenerator.getPostcode();
    }

    public ArrayList<String> getStringAttributes() {
        ArrayList<String> personAttributes = new ArrayList<>();

        personAttributes.add(this.name.getFirst());
        personAttributes.add(this.name.getLast());
        personAttributes.add(this.name.getPatronymic());
        personAttributes.add(Integer.toString(this.dob.getAge()));
        personAttributes.add(gender);
        personAttributes.add(this.dob.getDate());
        personAttributes.add(inn);
        personAttributes.add(Integer.toString(postcode));
        personAttributes.add(this.location.getCountry());
        personAttributes.add(this.location.getState());
        personAttributes.add(this.location.getCity());
        personAttributes.add(this.location.getStreet());
        personAttributes.add(Integer.toString(this.location.getBldNumber()));
        personAttributes.add(Integer.toString(this.location.getAptNumber()));

        return personAttributes;
    }
}
