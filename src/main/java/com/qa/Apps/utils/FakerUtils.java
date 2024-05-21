package com.qa.Apps.utils;

import com.github.javafaker.Faker;

public class FakerUtils {

    static Faker faker = new Faker();

    String name = faker.name().fullName(); // Miss Samanta Schmidt
    String firstName = faker.name().firstName(); // Emory
    String lastName = faker.name().lastName(); // Barton

    String streetAddress = faker.address().streetAddress(); // 60018 Sawayn Brooks Suite 449

    public static String getRandomCourseName() {
        return faker.gameOfThrones().character() + " " + faker.artist().name();
    }

    public static String getRandomDescriptions() {
        return faker.address().fullAddress();
    }

    public static String getRandomNumber() {
        return faker.number().digits(5);
    }
}
