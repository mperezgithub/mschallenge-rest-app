package com.intercorpretail.mschallenge.utils;

import java.security.SecureRandom;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;

public class DodGeneratorUtil {
    private static SecureRandom secureRandom = new SecureRandom();
    private static final Integer LIFE_EXPECTANCY_AR = 77;

    private DodGeneratorUtil() {
        throw new IllegalStateException("Utility class");
    }

    public static Date generateDOD(Integer age) {
        Integer yearsTL = LIFE_EXPECTANCY_AR - age;
        LocalDate estimatedDod = LocalDate.now().plusYears(secureRandom.nextInt(yearsTL > 0 ? yearsTL + 10 : 20));
        Calendar calendar = Calendar.getInstance();
        calendar.set(estimatedDod.getYear(), secureRandom.nextInt(11), secureRandom.nextInt(31));
        return calendar.getTime();
    }
}