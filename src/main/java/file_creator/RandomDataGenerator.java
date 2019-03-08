package file_creator;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class RandomDataGenerator {
    private static ThreadLocalRandom random = ThreadLocalRandom.current();
    private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
    private static String[] genderVariants = {"М", "Ж"};

    public static int getNumberOfRows() {
        return random.nextInt(1, 31);
    }

    public static Date getDateOfBirth() {
        return new Date(random.nextLong(1000000000000L));

    }

    public static int getPostcode() {
        return random.nextInt(100000, 200001);
    }

    public static int getBldNumber() {
        return random.nextInt(1, 100);
    }

    public static int getAptNumber() {
        return random.nextInt(1, 200);
    }

    public static String getGender() {
        return genderVariants[random.nextInt(2)];
    }


    public static String getInn() {
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
