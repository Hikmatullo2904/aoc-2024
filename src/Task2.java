import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class Task2 {
    public static void main(String[] args) {
        try (BufferedReader bf = new BufferedReader(new FileReader("input1.txt"))) {
            String line = bf.readLine();
            int count = 0;
            while (line != null) {
                if (isSafe(line)) {
                    count++;
                }
                line = bf.readLine();
            }
            System.out.println( count);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static boolean isSafe(String line) {
        String[] split = line.split(" ");
        List<Integer> levels = new ArrayList<>();
        for (String s : split) {
            levels.add(Integer.parseInt(s));
        }

        if (isSafeWithoutRemoval(levels)) {
            return true;
        }

        for (int i = 0; i < levels.size(); i++) {
            List<Integer> modified = new ArrayList<>(levels);
            modified.remove(i);
            if (isSafeWithoutRemoval(modified)) {
                return true;
            }
        }

        return false;
    }

    private static boolean isSafeWithoutRemoval(List<Integer> levels) {
        boolean isAscending = levels.get(0) < levels.get(1);

        for (int i = 0; i < levels.size() - 1; i++) {
            int diff = levels.get(i + 1) - levels.get(i);
            if ((isAscending && (diff <= 0 || diff > 3)) ||
                    (!isAscending && (diff >= 0 || Math.abs(diff) > 3))) {
                return false;
            }
        }

        return true;
    }
}
