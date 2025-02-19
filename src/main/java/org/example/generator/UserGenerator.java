package org.example.generator;

import org.apache.commons.lang3.RandomStringUtils;
import org.example.UserRequest;

public class UserGenerator {
    public final static String DEFAULT_NAME = "Lisa";
    public final static String DEFAULT_EMAIL = "test@test.ru";
    public final static String WORKING_EMAIL = "lisaiq@yandex.ru";
    public final static String DEFAULT_PASSWORD = "123456789";
    public final static String SHORT_PASSWORD = "12345";

    public static String getNewRandomEmail() {
        return Math.random() + DEFAULT_EMAIL;
    }

    public static UserRequest getUniqueUser() {
        return new UserRequest(
                RandomStringUtils.randomAlphanumeric(10) + "@test.com",
                RandomStringUtils.randomAlphanumeric(10),
                RandomStringUtils.randomAlphanumeric(10)
        );
    }
}
