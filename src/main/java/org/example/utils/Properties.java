package org.example.utils;

import java.util.ResourceBundle;

public abstract class Properties {

    public static String getProperty(String propertyName) {
        return ResourceBundle.getBundle("application").getString(propertyName);
    }
}
