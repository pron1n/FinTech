package files_creator;

import java.util.concurrent.ThreadLocalRandom;

public class Inn {
    private static ThreadLocalRandom random = ThreadLocalRandom.current();

    public String getRandomInn() {
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
}
