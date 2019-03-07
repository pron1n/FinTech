package files_creator;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class Person {
    private String name;
    private String surname;
    private String patronymic;
    private int age;
    private String gender;
    private String dateOfBirth;
    private String inn;
    private int postcode;
    private String country;
    private String region;
    private String city;
    private String street;
    private int bldNumber;
    private int aptNumber;

    private static ThreadLocalRandom random = ThreadLocalRandom.current();

    public String getGender() {
        return gender;
    }


    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public void setInn(String inn) {
        this.inn = inn;
    }

    public void setPostcode(Integer postcode) {
        this.postcode = postcode;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public void setBldNumber(int bldNumber) {
        this.bldNumber = bldNumber;
    }

    public void setAptNumber(int aptNumber) {
        this.aptNumber = aptNumber;
    }

    public ArrayList<String> getStringAttributes() {
        ArrayList<String> personAttributes = new ArrayList<>();
        personAttributes.add(name);
        personAttributes.add(surname);
        personAttributes.add(patronymic);
        personAttributes.add(Integer.toString(age));
        personAttributes.add(gender);
        personAttributes.add(dateOfBirth);
        personAttributes.add(inn);
        personAttributes.add(Integer.toString(postcode));
        personAttributes.add(country);
        personAttributes.add(region);
        personAttributes.add(city);
        personAttributes.add(street);
        personAttributes.add(Integer.toString(bldNumber));
        personAttributes.add(Integer.toString(aptNumber));

        return personAttributes;
    }

    public void setNameByGender(String gender) throws IOException {
        if (gender.equals("М") || gender.equals("male"))
            this.setName(FileOperator.getRandomValueFromResourceFile("Male_names.txt"));
        else
            this.setName(FileOperator.getRandomValueFromResourceFile("Female_names.txt"));
    }

    public void setSurameByGender(String gender) throws IOException {
        if (gender.equals("М") || gender.equals("male"))
            this.setSurname(FileOperator.getRandomValueFromResourceFile("Male_surnames.txt"));
        else
            this.setSurname(FileOperator.getRandomValueFromResourceFile("Female_surnames.txt"));
    }

    public void setPatronymicByGender(String gender) throws IOException {
        if (gender.equals("М") || gender.equals("male"))
            this.setPatronymic(FileOperator.getRandomValueFromResourceFile("Male_patronymics.txt"));
        else
            this.setPatronymic(FileOperator.getRandomValueFromResourceFile("Female_patronymics.txt"));
    }

    public Person getPersonByWebApiUser(WebApiUser webApiUser) throws IOException {
        if (webApiUser.getGender().equals("male"))
            this.setGender("М");
        else
            this.setGender("Ж");

        this.setName(webApiUser.getName().getFirst());
        this.setSurname(webApiUser.getName().getLast());
        this.setPatronymicByGender(this.gender);
        this.setAge(webApiUser.getDob().getAge());
        this.setDateOfBirth(webApiUser.getDob().getDate());
        this.setInn(new Inn().getRandomInn());
        this.setPostcode(random.nextInt(100000, 200001));
        this.setCountry(webApiUser.getNat());
        this.setRegion(webApiUser.getLocation().getState());
        this.setCity(webApiUser.getLocation().getCity());
        this.setStreet(webApiUser.getLocation().getStreet());
        this.setBldNumber(random.nextInt(1, 100));
        this.setAptNumber(random.nextInt(1, 200));

        return this;
    }
}