package org.example.generator;

public class UserGenerator {

    public static String DEFAULT_NAME = "Lisa";
    public static String DEFAULT_EMAIL = "test@test.ru";

    public static String WORKING_EMAIL = "lisaiq@yandex.ru";
    public static String DEFAULT_PASSWORD = "123456789";
    public static String SHORT_PASSWORD = "12345";

    public static String getNewRandomEmail() {
        return Math.random() + DEFAULT_EMAIL;
    }
}
