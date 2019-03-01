import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ThreadLocalRandom;

public class ExcelPeopleTableCreator {
    private static Date currentDate = new Date();

    private static ThreadLocalRandom random = ThreadLocalRandom.current();

    private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
    //public static String filePath = new File(".").getAbsolutePath();

    public static void createExcelPeopleTableFile() {

    }

    public static void addTitleRow(HSSFSheet sheet) {
        HSSFRow titleRow = sheet.createRow(0);
        int currentColumnIndex = 0;
        for (PeopleTableField peopleTableField : PeopleTableField.values()) {
            HSSFCell nextTitleCell = titleRow.createCell(currentColumnIndex);
            nextTitleCell.setCellValue(peopleTableField.getField());
            currentColumnIndex += 1;
        }
    }

    public static int getNumberOfRows() {
        int numberOfRows = random.nextInt();
        return numberOfRows;

    }

    public static Date getRandomDateOfBirth() {
        Date dateOfBirth = new Date(random.nextLong(1000000000000L));
        return dateOfBirth;
    }

    public static int calculateAge(Date dateOfBirth) {
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
        else if (currentMonth == birthMonth) {
            if (currentDay >= birthDay) {
                age = currentYear - birthYear;
                return age;
            }
            else if (currentDay < birthDay) {
                age = currentYear - birthYear - 1;
                return age;
            }
        }
        return age;
    }

    public static Person generateNewRandomPerson() { //возможно сделать методом класса Person
        Person person = new Person();

        Date dateOfBirth = getRandomDateOfBirth();
        Boolean sex = random.nextBoolean();

        person.setSex(sex);
        //switch (sex)
        person.setBirthday(simpleDateFormat.format(dateOfBirth));
        person.setAge(calculateAge(dateOfBirth));
        person.setBldNumber(random.nextInt(300));
        person.setAptNumber(random.nextInt(500));


        return person;
    }

    public void mapPersonToTableFields() {

    }

    public static void main(String[] args) throws IOException {
        HSSFWorkbook wb = new HSSFWorkbook();
        FileOutputStream fos = new FileOutputStream("PeopleTable.xls");
        HSSFSheet sheet = wb.createSheet("People");

        addTitleRow(sheet);



        wb.write(fos);
        fos.close();

      //  System.out.println("Файл создан. Путь: " + filePath);
//      System.out.println(generateNewRandomPerson().getBirthday());
//      System.out.println("rand:");
//      System.out.println(random.nextInt(1,10));
//      Date date = getRandomDateOfBirth();
//        System.out.println("dateofbirth");
//        System.out.println(date);
//        System.out.println("age");
//      System.out.println(calculateAge(date));
    }
}
