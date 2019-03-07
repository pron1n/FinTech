package files_creator;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class FileOperator {

    public static ThreadLocalRandom random = ThreadLocalRandom.current();

    public static String getRandomValueFromResourceFile(String fileName) throws IOException {
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
}
