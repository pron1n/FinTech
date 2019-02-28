import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class ExcelPeopleTableCreator {

    public static String filePath = new File(".").getAbsolutePath();

    public static void main(String[] args) throws IOException {
        HSSFWorkbook workBook = new HSSFWorkbook();
        FileOutputStream fos = new FileOutputStream(filePath + "People_table.xls");

        workBook.createSheet("New Shit");

        workBook.write(fos);

        fos.close();

        System.out.println("Файл создан. Путь: " + filePath);
    }
}
