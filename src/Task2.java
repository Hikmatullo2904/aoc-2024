import java.io.BufferedReader;
import java.io.FileReader;

public class Task2 {

    private static boolean isEnabled;
    public static void main(String[] args) {
        isEnabled = true;
        try (BufferedReader bf = new BufferedReader(new FileReader("input.txt"))) {
            String line;
            long result = 0;
            while ((line = bf.readLine()) != null) {
                result += parseLine(line);
            }
            System.out.println(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static long parseLine(String line) {
        long result = 0;
        for (int i = 0; i < line.length(); i++) {

            String substring = line.substring(i);
            if (substring.startsWith("do()")) {
                isEnabled = true;
            } else if (substring.startsWith("don't()")) {
                isEnabled = false;
            }

            if (isEnabled) {
                long mul = isMul(substring);
                if (mul != -1) {
                    result += mul;
                }
            }
        }
        return result;
    }

    private static long isMul(String str) {
        if (str.length() < 6) {
            return -1;
        }
        if(str.length() > 12) {
            str = str.substring(0, 12);
        }
        if (!str.startsWith("mul")) {
            return -1;
        }
        str = str.substring(3);


        int ind = str.indexOf('(');
        int ind2 = str.indexOf(')');
        if (ind == -1 || ind2 == -1 || ind >= ind2) {
            return -1;
        }

        if (hasLetters(str.substring(0, ind2))) {
            return -1;
        }

        str = str.substring(ind + 1, ind2);
        String[] arr = str.split(",");
        if (arr.length != 2) {
            return -1;
        }

        try {
            long num1 = Long.parseLong(arr[0]);
            long num2 = Long.parseLong(arr[1]);
            if (num1 > 999 || num2 > 999) {
                return -1;
            }
            return num1 * num2;
        } catch (Exception e) {
            return -1;
        }
    }

    private static boolean hasLetters(String str) {
        for (int i = 0; i < str.length(); i++) {
            if (Character.isLetter(str.charAt(i))) {
                return true;
            }
        }
        return false;
    }
}
