package file_creator;

import com.google.gson.Gson;
import file_creator.user.DateOfBirth;
import file_creator.user.Location;
import file_creator.user.Name;
import file_creator.user.User;
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

    private static User getRandomUserOffline() throws IOException {
        User user = new User();

        Date dateOfBirth = RandomDataGenerator.getDateOfBirth();

        user.setName(new Name());
        user.setLocation(new Location());
        user.setDob(new DateOfBirth());

        user.setGender(RandomDataGenerator.getGender());
        user.setNameByGender(user.getGender());
        user.setSurnameByGender(user.getGender());
        user.setPatronymicByGender(user.getGender());
        user.dob.setDate(simpleDateFormat.format(dateOfBirth));
        user.dob.setAge(getAgeByDateOfBirth(dateOfBirth));
        user.location.setCountry(RandomDataGenerator.getRandomValueFromResourceFile("Countries.txt"));
        user.location.setState(RandomDataGenerator.getRandomValueFromResourceFile("Regions.txt"));
        user.location.setCity(RandomDataGenerator.getRandomValueFromResourceFile("Cities.txt"));
        user.location.setStreet(RandomDataGenerator.getRandomValueFromResourceFile("Streets.txt"));
        user.location.setBldNumber(RandomDataGenerator.getBldNumber());
        user.location.setAptNumber(RandomDataGenerator.getAptNumber());
        user.setPostcode(RandomDataGenerator.getPostcode());
        user.setInn(RandomDataGenerator.getInn());

        return user;
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

    private static void addPersonToExcelTableRow(User user, XSSFSheet sheet, int rowNum) {
        XSSFRow personRow = sheet.createRow(rowNum);
        ArrayList<String> personAttributes = user.getStringAttributes();
        for (int fieldNum = 0; fieldNum < personAttributes.size(); fieldNum++) {
            XSSFCell nextCell = personRow.createCell(fieldNum);
            nextCell.setCellValue(personAttributes.get(fieldNum));
        }
    }

    private static String getUserDataJsonString() throws IOException {
        OkHttpClient okHttpClient = new OkHttpClient();

        Request request = new Request.Builder()
                .url("https://randomuser.me/api/?inc=gender,name,location,dob,nat")
                .get()
                .build();

        try (Response response = okHttpClient.newCall(request).execute()) {
            return response.body().string();
        }
    }

    private static User getUserDataFromWebApi(String jsonUser) {
        Gson gson = new Gson();
        try
        {
            User user = gson.fromJson(jsonUser
                .substring(jsonUser.indexOf("[") + 1, jsonUser.indexOf("]")), User.class);
            return user;
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
                String jsonStringObject = getUserDataJsonString();
                User randomPerson = getUserDataFromWebApi(jsonStringObject);
                addPersonToExcelTableRow(randomPerson, sheet, row);
            } catch (Exception e) {
                User randomPerson = getRandomUserOffline();
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