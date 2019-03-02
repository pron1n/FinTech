import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class ExcelPeopleTableCreator {
    private static Date currentDate = new Date();

    private static ThreadLocalRandom random = ThreadLocalRandom.current();

    private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
    public static String filePath = new File("PeopleTable.xls").getAbsolutePath();

    public static void createExcelPeopleTableFile() {

    }

    public static void addTitleRow(HSSFSheet sheet) {
        HSSFRow titleRow = sheet.createRow(0);
        int currentColumnIndex = 0;
        for (TableFields tableFields : TableFields.values()) {
            HSSFCell nextTitleCell = titleRow.createCell(currentColumnIndex);
            nextTitleCell.setCellValue(tableFields.getField());
            currentColumnIndex++;
        }
    }

    public static int getRandomNumberOfRows() {
        int numberOfRows = random.nextInt(1, 1000);
        return numberOfRows;

    }

    public static Date getRandomDateOfBirth() {
        Date dateOfBirth = new Date(random.nextLong(1000000000000L));
        return dateOfBirth;
    }

    public static int calculateAgeByDateOfBirth(Date dateOfBirth) {
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

    public static String getRandomInn() {
        String inn = "77";
        String someCode = Integer.toString(random.nextInt(10000000, 99999999));
        inn += someCode;

        int firstControlNum = ((7 * Integer.valueOf(inn.charAt(0)) +
                2 * Integer.valueOf(inn.charAt(1)) +
                4 * Integer.valueOf(inn.charAt(2)) +
                10 * Integer.valueOf(inn.charAt(3)) +
                3 * Integer.valueOf(inn.charAt(4)) +
                5 * Integer.valueOf(inn.charAt(5)) +
                9 * Integer.valueOf(inn.charAt(6)) +
                4 * Integer.valueOf(inn.charAt(7)) +
                6 * Integer.valueOf(inn.charAt(8)) +
                8 * Integer.valueOf(inn.charAt(9))) % 11) % 10;

        inn += Integer.toString(firstControlNum);

        int secondControlNum = ((3 * Integer.valueOf(inn.charAt(0)) +
                7 * Integer.valueOf(inn.charAt(1)) +
                2 * Integer.valueOf(inn.charAt(2)) +
                4 * Integer.valueOf(inn.charAt(3)) +
                10 * Integer.valueOf(inn.charAt(4)) +
                3 * Integer.valueOf(inn.charAt(5)) +
                5 * Integer.valueOf(inn.charAt(6)) +
                9 * Integer.valueOf(inn.charAt(7)) +
                4 * Integer.valueOf(inn.charAt(8)) +
                6 * Integer.valueOf(inn.charAt(9)) +
                8 * Integer.valueOf(inn.charAt(10))) % 11) % 10;

        inn += Integer.toString(secondControlNum);

        return inn;
    }

    public static String getRandomValueFromResourceFile(String fileName) throws IOException {
        String randomValue = "";
        BufferedReader br = new BufferedReader(new FileReader("./src/main/resources/" + fileName));
        String value;
        List<String> values = new ArrayList<String>();
        int valueCount = 0;
        while ((value = br.readLine()) != null) {
            values.add(value);
            valueCount++;
        }
        int randomIndex = random.nextInt(0, valueCount);
        randomValue = values.get(randomIndex);

        return randomValue;
    }

    public static Person generateNewRandomPerson() throws IOException {
        Person person = new Person();

        Date dateOfBirth = getRandomDateOfBirth();

        String[] sexVariats = {"М", "Ж"};
        String sex = sexVariats[random.nextInt(2)];
        person.setSex(sex);

        if (sex == "М") {
            person.setName(getRandomValueFromResourceFile("Male_names.txt"));
            person.setSurname(getRandomValueFromResourceFile("Male_surnames.txt"));
            person.setPatronymic(getRandomValueFromResourceFile("Male_patronymics.txt"));
        } else if (sex == "Ж") {
            person.setName(getRandomValueFromResourceFile("Female_names.txt"));
            person.setSurname(getRandomValueFromResourceFile("Female_surnames.txt"));
            person.setPatronymic(getRandomValueFromResourceFile("Female_patronymics.txt"));
        }
        person.setBirthday(simpleDateFormat.format(dateOfBirth));
        person.setAge(calculateAgeByDateOfBirth(dateOfBirth));
        person.setCountry(getRandomValueFromResourceFile("Countries.txt"));
        person.setRegion(getRandomValueFromResourceFile("Regions.txt"));
        person.setCity(getRandomValueFromResourceFile("Cities.txt"));
        person.setStreet(getRandomValueFromResourceFile("Streets.txt"));
        person.setBldNumber(random.nextInt(1,200));
        person.setAptNumber(random.nextInt(1, 500));
        person.setPostcode(random.nextInt(100000, 200001));
        person.setInn(getRandomInn());

        return person;
    }

    public static void mapPersonToTableRow(Person person, HSSFSheet sheet, int rowNum) {
        HSSFRow personRow = sheet.createRow(rowNum);
        ArrayList personAttributes = new ArrayList();
        personAttributes = person.getAttributes();
        for (int fieldNum = 0; fieldNum < personAttributes.size(); fieldNum++) {
            HSSFCell nextCell = personRow.createCell(fieldNum);
            nextCell.setCellValue((String) personAttributes.get(fieldNum));
        }
    }

    public static void main(String[] args) throws IOException {
        HSSFWorkbook wb = new HSSFWorkbook();
        FileOutputStream fos = new FileOutputStream("PeopleTable.xls");
        HSSFSheet sheet = wb.createSheet("People");

        addTitleRow(sheet);

        int numberOfRows = getRandomNumberOfRows();

        for (int row = 1; row < numberOfRows; row++) {
            Person randomPerson = new Person();
            randomPerson = generateNewRandomPerson();
            mapPersonToTableRow(randomPerson, sheet, row);
        }

        wb.write(fos);
        fos.close();

        System.out.println("Файл создан. Путь: " + filePath);
    }
}
