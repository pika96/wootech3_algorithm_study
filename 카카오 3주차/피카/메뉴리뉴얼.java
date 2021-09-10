package Algorithm.algorithm.Y7주차;

import java.util.ArrayList;
import java.util.Collections;

public class 메뉴리뉴얼 {

    static String[] orders = {"ABCDE", "AB", "CD", "ADE", "XYZ", "XYZ", "ACD"};
    static int[] course = {2, 3, 5};

    static ArrayList<String> cook = new ArrayList<>();
    static ArrayList<String> temp = new ArrayList<>();
    static ArrayList<String> alpha = new ArrayList<>();
    static int maxCount = 0;

    public static void main(String[] args) {
        for (String s : solution(orders, course)) {
            System.out.println(s);
        }
    }

    public static String[] solution(String[] orders, int[] course) {
        String[] answer = {};

        for (int i = 0; i < orders.length; i++) {
            for (int j = 0; j < orders[i].length(); j++) {
                String s = String.valueOf(orders[i].charAt(j));
                if (!alpha.contains(s)) {
                    alpha.add(s);
                }
            }
        }

        Collections.sort(alpha);

        for (int i = 0; i < course.length; i++) {
            int len = course[i];
            temp = new ArrayList<>();
            maxCount = 0;
            DFS(len, "", 0, orders);
            cook.addAll(temp);
        }

        Collections.sort(cook);

        answer = new String[cook.size()];

        for(int i=0;i< cook.size();i++){
            answer[i] = cook.get(i);
        }

        return answer;
    }

    public static void DFS(int max, String result, int idx, String[] orders) {
        if (max == result.length() || idx >= alpha.size()) {
            if (max == result.length()) {
                int count = check(orders, result);
                if(count > maxCount) {
                    temp = new ArrayList<>();
                    temp.add(result);
                    maxCount = count;
                }
                else if(count == maxCount) {
                    temp.add(result);
                }
            }
            return;
        }

        DFS(max, result + alpha.get(idx), idx + 1, orders);
        DFS(max, result, idx + 1, orders);
    }

    public static int check(String[] orders, String result) {

        int count = 0;

        for (int i = 0; i < orders.length; i++) {
            String order = orders[i];
            boolean flag = true;

            for (int j = 0; j < result.length(); j++) {
                String s = String.valueOf(result.charAt(j));
                if(!order.contains(s)) {
                    flag = false;
                    break;
                }
            }

            if(flag) {
                count++;
            }
        }

        if(count >= 2) {
            return count;
        }

        return -1;
    }
}
