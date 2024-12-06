import java.io.BufferedReader;
import java.io.FileReader;
import java.util.List;

public class Task1 {
    public static void main(String[] args) {
        try(BufferedReader bf = new BufferedReader(new FileReader("input.txt"))) {
            List<String> lines = bf.lines().toList();
            char[][] arr = new char[lines.size()][lines.get(0).length()];

            for (int i = 0; i < lines.size(); i++) {
                for (int j = 0; j < lines.get(i).length(); j++) {
                    arr[i][j] = lines.get(i).charAt(j);
                }
            }

            fillPath(arr);

            System.out.println(countZeros(arr));
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static int countZeros(char[][] arr) {
        int count = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                if (arr[i][j] == 'o') {
                    count++;
                }
            }
        }
        return count;
    }

    private static void fillPath(char[][] arr) {
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
        boolean xPath = true;
        boolean yPath = true;
        arr[x--][y] = 'o';
        int n = arr.length;
        int m = arr[0].length;
        while(x != n  && y != m && x != -1 && y != -1) {
            if (arr[x][y] == '#'){
                if (xPath && yPath) {
                    y++;
                    x++;
                    yPath = false;
                }else if (xPath && !yPath) {
                    x++;
                    y--;
                    xPath = false;
                }else if(!xPath && !yPath) {
                    y--;
                    x--;
                    yPath = true;
                }else {
                    x--;
                    y++;
                    xPath = true;
                }
            }else {
                arr[x][y] = 'o';
                if (xPath && yPath) {
                    x--;
                }else if (xPath && !yPath) {
                    y++;
                }else if(!xPath && !yPath) {
                    x++;
                }else {
                    y--;
                }
            }

        }

    }


}