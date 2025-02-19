package org.example.utils;

import com.github.javafaker.Faker;

public abstract class UserInfoGenerator {

    public static String getRandomEmail() {
        return new Faker().internet().emailAddress();
    }

    public static String getRandomFirstName() {
        return new Faker().name().firstName();
    }

    public static String getRandomLastName() {
        return new Faker().name().lastName();
    }

}
