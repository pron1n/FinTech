package files_creator;

public class DateOfBirth {
    private String date;
    private int age;

    public DateOfBirth(String date, int age) {
        this.date = date;
        this.age = age;
    }

    public String getDate() {
        return date;
    }

    public int getAge() {
        return age;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
