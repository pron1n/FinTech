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


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getInn() {
        return inn;
    }

    public void setInn(String inn) {
        this.inn = inn;
    }

    public int getPostcode() {
        return postcode;
    }

    public void setPostcode(Integer postcode) {
        this.postcode = postcode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public int getBldNumber() {
        return bldNumber;
    }

    public void setBldNumber(int bldNumber) {
        this.bldNumber = bldNumber;
    }

    public int getAptNumber() {
        return aptNumber;
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
