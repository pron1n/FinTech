package files_creator;

public class PersonFromWebApi {
    private String gender;
    private Name name;
    private Location location;
    private DateOfBirth dob;
    private String nat;

    public PersonFromWebApi(String gender, Name name, Location location, DateOfBirth dob, String nat) {
        this.gender = gender;
        this.name = name;
        this.location = location;
        this.dob = dob;
        this.nat = nat;
    }

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

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setName(Name name) {
        this.name = name;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public void setDob(DateOfBirth dob) {
        this.dob = dob;
    }

    public void setNat(String nat) {
        this.nat = nat;
    }
}
