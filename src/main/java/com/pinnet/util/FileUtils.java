package com.pinnet.util;

public class FileUtils {

    public static String handler(String fileName) {
        return System.currentTimeMillis() + "_" +fileName;
    }
}
