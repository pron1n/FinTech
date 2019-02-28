import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Random;

public class ExcelPeopleTableCreator {
    private static Random random = new Random();
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

    public Person generateNewRandomPerson() { //возможно сделать методом класса Person
        Person person = new Person();

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
    }
}
