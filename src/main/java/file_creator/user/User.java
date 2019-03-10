package file_creator.user;

import file_creator.RandomDataGenerator;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class User {
    private String gender;
    private Name name;
    public Location location;
    public DateOfBirth dob;
    private String inn;
    private int postcode;
    private String nat;

    public void setNat(String nat) {
        this.nat = nat;
    }

    public String getNat() {
        return nat;
    }

    private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
    private static Date currentDate = new Date();

    public User() throws IOException {
        this.name = new Name();
        this.location = new Location();
        this.dob = new DateOfBirth();
        this.inn = RandomDataGenerator.getInn();
        this.postcode = RandomDataGenerator.getPostcode();
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setFirstNameByGender(String gender) throws IOException {
        if (gender.equals("М") || gender.equals("male"))
            this.name.setFirst(RandomDataGenerator.getRandomValueFromResourceFile("Male_names.txt"));
        else
            this.name.setFirst(RandomDataGenerator.getRandomValueFromResourceFile("Female_names.txt"));
    }

    public void setLastNameByGender(String gender) throws IOException {
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

    private static int getAgeByDateOfBirth(Date dateOfBirth) {
        int currentYear = currentDate.getYear() + 1900;
        int currentMonth = currentDate.getMonth() + 1;
        int currentDay = currentDate.getDate();
        int birthYear = dateOfBirth.getYear() + 1900;
        int birthMonth = dateOfBirth.getMonth() + 1;
        int birthDay = dateOfBirth.getDate();

        int age  = 0;

        if (currentMonth > birthMonth) {
            age = currentYear - birthYear;
            return age;
        }
        else if (currentMonth < birthMonth) {
            age = currentYear - birthYear - 1;
            return age;
        }
        else {
            if (currentDay >= birthDay) {
                age = currentYear - birthYear;
                return age;
            }
            else {
                age = currentYear - birthYear - 1;
                return age;
            }
        }
    }

    public User getRandomUserOffline() throws IOException {

        Date dateOfBirth = RandomDataGenerator.getDateOfBirth();

        this.setGender(RandomDataGenerator.getGender());
        this.setFirstNameByGender(this.getGender());
        this.setLastNameByGender(this.getGender());
        this.setPatronymicByGender(this.getGender());
        this.dob.setDate(simpleDateFormat.format(dateOfBirth));
        this.dob.setAge(getAgeByDateOfBirth(dateOfBirth));
        this.setNat(RandomDataGenerator.getRandomValueFromResourceFile("Countries.txt"));
        this.location.setState(RandomDataGenerator.getRandomValueFromResourceFile("Regions.txt"));
        this.location.setCity(RandomDataGenerator.getRandomValueFromResourceFile("Cities.txt"));
        this.location.setStreet(RandomDataGenerator.getRandomValueFromResourceFile("Streets.txt"));

        return this;
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
        personAttributes.add(this.getNat());
        personAttributes.add(this.location.getState());
        personAttributes.add(this.location.getCity());
        personAttributes.add(this.location.getStreet());
        personAttributes.add(Integer.toString(this.location.getBldNumber()));
        personAttributes.add(Integer.toString(this.location.getAptNumber()));

        return personAttributes;
    }
}