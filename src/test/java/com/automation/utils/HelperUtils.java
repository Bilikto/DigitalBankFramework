package com.automation.utils;

import java.util.Random;

public class HelperUtils {

    public static int generateRandomSSN(int n) {
        int m = (int) Math.pow(10, n - 1);
        int ssn = new Random().nextInt(9 * m);
        return ssn;
    }

}
