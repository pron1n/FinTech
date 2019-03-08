package file_creator;

import com.google.gson.Gson;
import file_creator.web_api_user.WebApiUser;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class PeopleTableFilesCreator {
    private static Date currentDate = new Date();
    private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
    private static String excelFilePath = new File("PeopleTable.xlsx").getAbsolutePath();

    private static Person getRandomPersonOffline() throws IOException {
        Person person = new Person();

        Date dateOfBirth = RandomDataGenerator.getDateOfBirth();

        person.setGender(RandomDataGenerator.getGender());
        person.setNameByGender(person.getGender());
        person.setSurameByGender(person.getGender());
        person.setPatronymicByGender(person.getGender());
        person.setDateOfBirth(simpleDateFormat.format(dateOfBirth));
        person.setAge(getAgeByDateOfBirth(dateOfBirth));
        person.setCountry(RandomDataGenerator.getRandomValueFromResourceFile("Countries.txt"));
        person.setRegion(RandomDataGenerator.getRandomValueFromResourceFile("Regions.txt"));
        person.setCity(RandomDataGenerator.getRandomValueFromResourceFile("Cities.txt"));
        person.setStreet(RandomDataGenerator.getRandomValueFromResourceFile("Streets.txt"));
        person.setBldNumber(RandomDataGenerator.getBldNumber());
        person.setAptNumber(RandomDataGenerator.getAptNumber());
        person.setPostcode(RandomDataGenerator.getPostcode());
        person.setInn(RandomDataGenerator.getInn());

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

    private static String getRandomUserJsonString() throws IOException {
        OkHttpClient okHttpClient = new OkHttpClient();

        Request request = new Request.Builder()
                .url("https://randomuser.me/api/?inc=gender,name,location,dob,nat")
                .get()
                .build();

        try (Response response = okHttpClient.newCall(request).execute()) {
            return response.body().string();
        } catch (Exception e) {
            return "\nНе удалось получить ответ от randomuser.me";
        }
    }

    private static WebApiUser getWebApiUser(String userJson) {
        Gson gson = new Gson();
        try {
            WebApiUser webApiUser = gson.fromJson(userJson
                .substring(userJson.indexOf("[") + 1, userJson.indexOf("]")), WebApiUser.class);
            return webApiUser;
        } catch (Exception e) {
            return null;
        }
    }

    public static void main(String[] args) throws IOException {
        XSSFWorkbook wb = new XSSFWorkbook();
        FileOutputStream fos = new FileOutputStream("PeopleTable.xlsx");
        XSSFSheet sheet = wb.createSheet("People");
        sheet.setDefaultColumnWidth(15);
        addExcelTableTitleRow(sheet);

        int numberOfRows = RandomDataGenerator.getNumberOfRows();

        for (int row = 1; row <= numberOfRows; row++) {
            try {
                String jsonStringObject = getRandomUserJsonString();
                Person randomPerson = new Person();
                randomPerson.getPersonByWebApiUser(getWebApiUser(jsonStringObject));
                addPersonToExcelTableRow(randomPerson, sheet, row);
            } catch (Exception e) {
                Person randomPerson = getRandomPersonOffline();
                addPersonToExcelTableRow(randomPerson, sheet, row);
                System.out.println("Не удалось получить пользователя от randomuser.me\n" +
                        "Пользователь в строке " + (row + 1) + " сгенерен с использованием локальных ресурсов");
            }
        }

        wb.write(fos);
        fos.close();

        System.out.println("Файл создан. Путь: " + excelFilePath);
    }
}