package files_creator;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class PeopleTableFilesCreator {
    private static Date currentDate = new Date();
    private static ThreadLocalRandom random = ThreadLocalRandom.current();
    private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
    private static String excelFilePath = new File("PeopleTable.xlsx").getAbsolutePath();

    private static String getRandomValueFromResourceFile(String fileName) throws IOException {
        String randomValue = "";
        BufferedReader br = new BufferedReader(new FileReader("./src/main/resources/" + fileName));
        String value;
        List<String> values = new ArrayList<>();
        int valueCount = 0;
        while ((value = br.readLine()) != null) {
            values.add(value);
            valueCount++;
        }
        int randomIndex = random.nextInt(0, valueCount);
        randomValue = values.get(randomIndex);

        return randomValue;
    }

    private static Person getRandomPerson() throws IOException {
        Person person = new Person();

        Date dateOfBirth = new Date(random.nextLong(1000000000000L));

        String[] sexVariats = {"М", "Ж"};
        String sex = sexVariats[random.nextInt(2)];
        person.setSex(sex);

        if (sex.equals("М")) {
            person.setName(getRandomValueFromResourceFile("Male_names.txt"));
            person.setSurname(getRandomValueFromResourceFile("Male_surnames.txt"));
            person.setPatronymic(getRandomValueFromResourceFile("Male_patronymics.txt"));
        } else if (sex.equals("Ж")) {
            person.setName(getRandomValueFromResourceFile("Female_names.txt"));
            person.setSurname(getRandomValueFromResourceFile("Female_surnames.txt"));
            person.setPatronymic(getRandomValueFromResourceFile("Female_patronymics.txt"));
        }
        person.setBirthday(simpleDateFormat.format(dateOfBirth));
        person.setAge(getAgeByDateOfBirth(dateOfBirth));
        person.setCountry(getRandomValueFromResourceFile("Countries.txt"));
        person.setRegion(getRandomValueFromResourceFile("Regions.txt"));
        person.setCity(getRandomValueFromResourceFile("Cities.txt"));
        person.setStreet(getRandomValueFromResourceFile("Streets.txt"));
        person.setBldNumber(random.nextInt(1,100));
        person.setAptNumber(random.nextInt(1, 200));
        person.setPostcode(random.nextInt(100000, 200001));
        person.setInn(getRandomInn());

        return person;
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

    private static String getRandomInn() {
        String inn = "77" + Integer.toString(random.nextInt(10000000, 99999999));

        int firstControlNum = ((7 * Character.getNumericValue(inn.charAt(0)) +
                2 * Character.getNumericValue(inn.charAt(1)) +
                4 * Character.getNumericValue(inn.charAt(2)) +
                10 * Character.getNumericValue(inn.charAt(3)) +
                3 * Character.getNumericValue(inn.charAt(4)) +
                5 * Character.getNumericValue(inn.charAt(5)) +
                9 * Character.getNumericValue(inn.charAt(6)) +
                4 * Character.getNumericValue(inn.charAt(7)) +
                6 * Character.getNumericValue(inn.charAt(8)) +
                8 * Character.getNumericValue(inn.charAt(9))) % 11) % 10;

        inn += Integer.toString(firstControlNum);

        int secondControlNum = ((3 * Character.getNumericValue(inn.charAt(0)) +
                7 * Character.getNumericValue(inn.charAt(1)) +
                2 * Character.getNumericValue(inn.charAt(2)) +
                4 * Character.getNumericValue(inn.charAt(3)) +
                10 * Character.getNumericValue(inn.charAt(4)) +
                3 * Character.getNumericValue(inn.charAt(5)) +
                5 * Character.getNumericValue(inn.charAt(6)) +
                9 * Character.getNumericValue(inn.charAt(7)) +
                4 * Character.getNumericValue(inn.charAt(8)) +
                6 * Character.getNumericValue(inn.charAt(9)) +
                8 * Character.getNumericValue(inn.charAt(10))) % 11) % 10;

        inn += Integer.toString(secondControlNum);

        return inn;
    }

    private static void addExcelTableTitleRow(XSSFSheet sheet) {
        XSSFRow titleRow = sheet.createRow(0);
        int currentColumnIndex = 0;
        for (TableFields tableFields : TableFields.values()) {
            XSSFCell nextTitleCell = titleRow.createCell(currentColumnIndex);
            nextTitleCell.setCellValue(tableFields.getField());
            currentColumnIndex++;
        }
    }

    private static void addPersonToExcelTableRow(Person person, XSSFSheet sheet, int rowNum) {
        XSSFRow personRow = sheet.createRow(rowNum);
        ArrayList<String> personAttributes = person.getStringAttributes();
        for (int fieldNum = 0; fieldNum < personAttributes.size(); fieldNum++) {
            XSSFCell nextCell = personRow.createCell(fieldNum);
            nextCell.setCellValue(personAttributes.get(fieldNum));
        }
    }

    public static void main(String[] args) throws IOException {
        XSSFWorkbook wb = new XSSFWorkbook();
        FileOutputStream fos = new FileOutputStream("PeopleTable.xlsx");
        XSSFSheet sheet = wb.createSheet("People");
        sheet.setDefaultColumnWidth(15);
        addExcelTableTitleRow(sheet);

        int numberOfRows = random.nextInt(1, 31);

        for (int row = 1; row <= numberOfRows; row++) {
            Person randomPerson = getRandomPerson();
            addPersonToExcelTableRow(randomPerson, sheet, row);
        }

        wb.write(fos);
        fos.close();

        System.out.println("Файл создан. Путь: " + excelFilePath);
    }
}