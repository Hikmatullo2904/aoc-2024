import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class Task1 {
    public static void main(String[] args) {
        List<Integer> left = new ArrayList<>();
        List<Integer> right = new ArrayList<>();
        readInput(left, right);
        left.sort(Integer::compareTo);
        right.sort(Integer::compareTo);
        int sum = 0;
        for (int i = 0; i < left.size(); i++) {
            sum += Math.abs(left.get(i) - right.get(i));
        }
        System.out.println(sum);
    }


    private static void readInput(List<Integer> left, List<Integer> right) {
        try(BufferedReader bf = new BufferedReader(new FileReader("input.txt"))) {
            String line;
            while ((line = bf.readLine()) != null) {
                String[] split = line.split(" ");
                left.add(Integer.parseInt(split[0]));
                right.add(Integer.parseInt(split[3]));
            }

        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}