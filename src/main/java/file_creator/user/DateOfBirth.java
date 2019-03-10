package file_creator.user;

import java.util.Date;

public class DateOfBirth {
    private String date;
    private int age;
    private static Date currentDate = new Date();

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

    public static int getAgeByDateOfBirth(Date dateOfBirth) {
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

    public static String getDateInYYYYMMDD(String timestamp) {
        return timestamp.substring(0, 10);
    }

    public static String getDateInDDMMYYYY(String dateInYYYYMMDD) {
        String dd = dateInYYYYMMDD.substring(8);
        String mm = dateInYYYYMMDD.substring(5,7);
        String yyyy = dateInYYYYMMDD.substring(0,4);

        return dd + "-" + mm + "-" + yyyy;
    }
}