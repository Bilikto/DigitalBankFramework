package com.automation.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Random;
import java.util.Scanner;

public class HelperUtils {

    public static int generateRandomSSN(int n) {
        int m = (int) Math.pow(10, n - 1);
        int ssn = new Random().nextInt(9 * m);
        return ssn;
    }

    public static String readDataFromFile(String filePath) {
        File file = new File(filePath);
        String content = null;
        try {
            content = new Scanner(file).useDelimiter("\\Z").next();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return content;
    }

}
