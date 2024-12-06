import java.io.BufferedReader;
import java.io.FileReader;
import java.security.Guard;
import java.util.List;

public class Task2 {
    public static void main(String[] args) {
        try(BufferedReader bf = new BufferedReader(new FileReader("input.txt"))) {
            List<String> lines = bf.lines().toList();
            char[][] arr = new char[lines.size()][lines.get(0).length()];

            for (int i = 0; i < lines.size(); i++) {
                for (int j = 0; j < lines.get(i).length(); j++) {
                    arr[i][j] = lines.get(i).charAt(j);
                }
            }

            int i = fillPath(arr);
            System.out.println(i);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static int fillPath(char[][] arr) {
        int x = 0;
        int y = 0;
        x:for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                if (arr[i][j] == '^') {
                    x = i;
                    y = j;
                    break x;
                }
            }
        }

        return 0;
    }


}