package sk.kosickaakademia.deco.util;

public class Utility {
    public static String capitalizeWords(String str) {
        if (str == null || str.isEmpty() || str.isBlank())
            return null;

        StringBuilder result = new StringBuilder();

        str = str.toLowerCase().trim();
        char[] arr = str.trim().toCharArray();
        result.append(Character.toUpperCase(arr[0]));

        for (int i = 1; i < arr.length; i++) {
            if (arr[i-1] == ' ')
                arr[i] = Character.toUpperCase(arr[i]);
            result.append(arr[i]);
        }

        return result.toString();
    }
}
