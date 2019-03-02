import java.util.ArrayList;

public class Person {
    private String name;
    private String surname;
    private String patronymic;
    private int age;
    private String sex;
    private String birthday;
    private String inn;
    private int postcode;
    private String country;
    private String region;
    private String city;
    private String street;
    private int bldNumber;
    private int aptNumber;

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

    public void setSex(String sex) {
        this.sex = sex;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
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

    public ArrayList<String> getAttributes() {
        ArrayList<String> personAttributes = new ArrayList();
        personAttributes.add(name);
        personAttributes.add(surname);
        personAttributes.add(patronymic);
        personAttributes.add(Integer.toString(age));
        personAttributes.add(sex);
        personAttributes.add(birthday);
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
}