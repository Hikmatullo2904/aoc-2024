import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;

public class Task2 {

    public static void main(String[] args) {
        List<List<Integer>> list = new ArrayList<>();
        getNumbers(list);
        Map<Integer, Set<Integer>> map = numberOrders(list);
        for (Map.Entry<Integer, Set<Integer>> entry : map.entrySet()) {
            System.out.println(entry.getKey() + " ->" + entry.getValue());
        }
        System.out.println(finsResult(map));
    }

    private static int finsResult(Map<Integer, Set<Integer>> map) {

        try(BufferedReader bf = new BufferedReader(new FileReader("input2.txt"))) {
            String line;
            int sum = 0;
            while ((line = bf.readLine()) != null) {
                int middleOfNum = getMiddleOfNum(map, line);

                if (middleOfNum != -1) {
                    sum += middleOfNum;
                }
            }
            return sum;
        }catch (Exception e) {
            e.printStackTrace();
        }

        return 0;
    }

    public static int getMiddleOfNum(Map<Integer, Set<Integer>> map, String str) {
        String[] split = str.split(",");
        int[] list = new int[split.length];

        for (int i = 0; i < split.length; i++) {
            list[i] = Integer.parseInt(split[i]);
        }

        boolean isWrongOrder = false;
        for(int i = 0; i < list.length; i++) {
            int a = list[i];
            for (int j = i+1; j < list.length; j++) {
                Set<Integer> set = map.get(list[j]);
                if (set != null && set.contains(a)) {
                    isWrongOrder = true;
                    list[i] = list[j];
                    list[j] = a;
                    i--;
                    break;
                }
            }
        }

        return isWrongOrder ?  list[list.length / 2] : -1;
    }


    public static void getNumbers(List<List<Integer>> list)  {
        try(BufferedReader bf = new BufferedReader(new FileReader("input1.txt"))) {
            String line;
            while ((line = bf.readLine()) != null) {
                String[] numbers = line.split("\\|");

                List<Integer> numberList = new ArrayList<>();
                for (String number : numbers) {
                    numberList.add(Integer.parseInt(number));
                }
                list.add(numberList);
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Map<Integer, Set<Integer>> numberOrders(List<List<Integer>> list) {
        Map<Integer, Set<Integer>> map = new HashMap<>();
        for (int i = 0; i < list.size(); i++) {
            int num = list.get(i).get(0);
            Set<Integer> set = new HashSet<>();
            for (int j = 0; j < list.size(); j++) {
                if (list.get(j).get(0) == num) {
                    set.add(list.get(j).get(1));
                }
            }
            map.put(num, set);
        }
        return map;
    }
}