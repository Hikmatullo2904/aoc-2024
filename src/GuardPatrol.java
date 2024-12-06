import java.io.*;
import java.util.*;

public class GuardPatrol {
    public static void main(String[] args) throws IOException {
        // Input file handling

        List<String> grid = readInputFile("input.txt");

        int rows = grid.size();
        int cols = grid.get(0).length();
        int sr = 0, sc = 0; // Starting position of the guard

        // Find the guard's starting position marked by '^'
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (grid.get(r).charAt(c) == '^') {
                    sr = r;
                    sc = c;
                    break;
                }
            }
        }

        int p1 = 0, p2 = 0;

        // Loop through all possible obstruction positions
        for (int o_r = 0; o_r < rows; o_r++) {
            for (int o_c = 0; o_c < cols; o_c++) {
                int r = sr, c = sc;
                int d = 0; // Direction: 0=up, 1=right, 2=down, 3=left
                Set<String> seen = new HashSet<>();
                Set<String> seenRC = new HashSet<>();

                while (true) {
                    // Check if the guard gets stuck in a loop
                    String state = r + "," + c + "," + d;
                    if (seen.contains(state)) {
                        p2++;
                        break;
                    }
                    seen.add(state);
                    seenRC.add(r + "," + c);

                    // Calculate the next position
                    int[] direction = getDirection(d);
                    int rr = r + direction[0];
                    int cc = c + direction[1];

                    // Check boundaries
                    if (rr < 0 || rr >= rows || cc < 0 || cc >= cols) {
                        if (grid.get(o_r).charAt(o_c) == '#') {
                            p1 = seenRC.size();
                        }
                        break;
                    }

                    // Check for obstacles or the obstruction position
                    char cell = grid.get(rr).charAt(cc);
                    if (cell == '#' || (rr == o_r && cc == o_c)) {
                        d = (d + 1) % 4; // Turn clockwise
                    } else {
                        r = rr;
                        c = cc; // Move to the next cell
                    }
                }
            }
        }

        // Output the results
        System.out.println(p1);
        System.out.println(p2);
    }

    // Read input file into a list of strings
    private static List<String> readInputFile(String fileName) throws IOException {
        List<String> lines = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                lines.add(line);
            }
        }
        return lines;
    }

    // Get direction vector based on the current direction
    private static int[] getDirection(int d) {
        switch (d) {
            case 0: return new int[]{-1, 0}; // Up
            case 1: return new int[]{0, 1};  // Right
            case 2: return new int[]{1, 0};  // Down
            case 3: return new int[]{0, -1}; // Left
            default: return new int[]{0, 0}; // Default (should not happen)
        }
    }
}
