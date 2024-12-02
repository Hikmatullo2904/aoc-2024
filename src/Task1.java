import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class Task1 {
    public static void main(String[] args) {
        try(BufferedReader bf = new BufferedReader(new FileReader("input1.txt"))) {
            String line = bf.readLine();
            int count = 0;
            while (line != null) {
                if (isSafe(line)) {
                    count++;
                }else
                    System.out.println(line);
                line = bf.readLine();
            }
            System.out.println(count);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static boolean isSafe(String line) {
        String[] split = line.split(" ");
        List<Integer> list = new ArrayList<>();
        for (String s : split) {
            list.add(Integer.parseInt(s));
        }
        boolean isAsc = list.get(0) < list.get(1);
        if (isAsc) {
            return isSafeInAsc(list);
        }else
            return isSafeInDesk(list);
    }

    private static boolean isSafeInAsc(List<Integer> list) {
        for (int i = 0; i < list.size() - 1; i++) {
            if (list.get(i+1) - list.get(i) <= 0 || list.get(i+1) - list.get(i) > 3) {
                return false;
            }
        }
        return true;
    }

    private static boolean isSafeInDesk(List<Integer> list) {
        for (int i = 0; i < list.size() - 1; i++) {
            if (list.get(i) - list.get(i+1) <= 0 || list.get(i) - list.get(i+1) > 3) {
                return false;
            }
        }
        return true;
    }
}