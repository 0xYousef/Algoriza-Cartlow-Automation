package org.algoriza.utils;

import java.util.List;
import java.util.Random;

public class RandomUtil {
    private static final Random random = new Random(); // single Random instance

    public static <T> T getRandomElement(List<T> list) {
        if (list == null || list.isEmpty()) {
            return null;
        }
        int index = random.nextInt(list.size());
        return list.get(index);
    }

}
