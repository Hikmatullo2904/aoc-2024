import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Task1 {
    public static void main(String[] args) {

        try(BufferedReader bf = new BufferedReader(new FileReader("input.txt"))) {
            List<List<Character>> lines = new ArrayList<>();
            String line;
            while ((line = bf.readLine()) != null) {
                List<Character> charList = line.chars()
                        .mapToObj(c -> (char) c)
                        .collect(Collectors.toList());
                lines.add(charList);
            }

            System.out.println(countWord(lines));
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static int countWord(List<List<Character>> list) {
        int count = 0;
        count += countDiagonal(list);
        count += countDiagonalReverse(list);
        count += countHorizontal(list);
        count += countUpDown(list);
        return count;
    }

    private static int countDiagonal(List<List<Character>> list) {
        int count = 0;
        for (int i = 0; i < list.size() - 3; i++) {
            for (int j = 0; j < list.get(i).size() - 3; j++) {
                String word = "" +list.get(i).get(j) + list.get(i+1).get(j+1) + list.get(i+2).get(j+2) + list.get(i+3).get(j+3);
                if (isWordEqualToXMAS(word)) {
                    count++;
                }
            }
        }
        return count;
    }

    private static int countDiagonalReverse(List<List<Character>> list) {
        int count = 0;
        for (int i = 0; i < list.size() - 3; i++) {
            for (int j = list.get(i).size() - 1; j >= 3; j--) {
                String word = "" +list.get(i).get(j) + list.get(i+1).get(j-1) + list.get(i+2).get(j-2) + list.get(i+3).get(j-3);
                if (isWordEqualToXMAS(word)) {
                    count++;
                }
            }
        }
        return count;
    }

    private static int countHorizontal(List<List<Character>> list) {
        int count = 0;
        for (int i = 0; i < list.size(); i++) {
            for (int j = 0; j < list.get(i).size() - 3; j++) {
                String word = "" +list.get(i).get(j) + list.get(i).get(j+1) + list.get(i).get(j+2) + list.get(i).get(j+3);
                if (isWordEqualToXMAS(word)) {
                    count++;
                }
            }
        }
        return count;
    }

    private static int countUpDown(List<List<Character>> list) {
        int count = 0;
        for (int i = 0; i < list.size() - 3; i++) {
            for (int j = 0; j < list.get(i).size(); j++) {
                String word = "" +list.get(i).get(j) + list.get(i+1).get(j) + list.get(i+2).get(j) + list.get(i+3).get(j);
                if (isWordEqualToXMAS(word)) {
                    count++;
                }
            }
        }
        return count;
    }

    private static boolean isWordEqualToXMAS(String word) {
        return word.equals("XMAS") || word.equals("SAMX");
    }
}