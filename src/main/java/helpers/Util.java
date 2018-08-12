package helpers;

import java.util.List;

public class Util {
    public static double extractDoubleFromString(String str) {
        int strLength = str.length();
        String ignoredFirstChar = str.subSequence(1, strLength).toString();
        return Double.parseDouble(ignoredFirstChar);

    }
}
