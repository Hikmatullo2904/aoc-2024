import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Task2 {

    public static void main(String[] args) {
        List<Integer> left = new ArrayList<>();
        Map<Integer, Integer> right = new HashMap<>();
        readInput(left, right);
        int sum = 0;
        for (Integer integer : left) {
            if (right.get(integer) != null) {
                sum += integer * right.get(integer);
            }
        }
        System.out.println(sum);
    }

    private static void readInput(List<Integer> left, Map<Integer, Integer> right) {
        try(BufferedReader bf = new BufferedReader(new FileReader("input2.txt"))) {
            String line;
            while ((line = bf.readLine()) != null) {
                String[] split = line.split(" ");
                left.add(Integer.parseInt(split[0]));
                Integer num = Integer.parseInt(split[3]);
                right.put(num, right.getOrDefault(num, 0) + 1);
            }

        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}
