package kakao2021;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.OptionalInt;
import java.util.stream.Collectors;

public class 매뉴리뉴얼 {

    public static void main(String[] args) {

        String[] orders = {"XYZ", "XWY", "WXA"};
        int[] course = {2, 3, 4};

        String[] answer = solution(orders, course);
        for (String menu : answer) {
            System.out.println(menu);
        }
    }

    public static String[] solution(String[] orders, int[] course) {
        HashMap<String, Integer> menuCount = new HashMap<>();

        for (int c : course) {
            for (String s : orders) {
                char[] order = s.toCharArray();
                Arrays.sort(order);
                combination(menuCount, order, new boolean[order.length], 0, order.length, c);
            }
        }

        List<String> answer = new ArrayList<>();

        for (int c : course) {
            OptionalInt maxMenu = menuCount.keySet().stream()
                .filter(menu -> menu.length() == c)
                .filter(menu -> menuCount.get(menu) >= 2)
                .mapToInt(menu -> menuCount.get(menu))
                .max();

            if (maxMenu.isEmpty()) {
                continue;
            }

            List<String> courseMenu = menuCount.keySet().stream()
                .filter(menu -> menu.length() == c)
                .filter(menu -> menuCount.get(menu) == maxMenu.getAsInt())
                .collect(Collectors.toList());

            answer.addAll(courseMenu);
        }

        answer.sort(Comparator.naturalOrder());
        return answer.toArray(new String[0]);
    }

    private static void combination(HashMap<String, Integer> menuCount, char[] arr,
        boolean[] visited, int depth, int n, int r) {
        if (r == 0) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < arr.length; i++) {
                if (visited[i]) {
                    sb.append(arr[i]);
                }
            }

            Integer numberOfMenu = menuCount.getOrDefault(sb.toString(), 0);
            menuCount.put(sb.toString(), numberOfMenu + 1);
            return;
        }

        if (depth == n) {
            return;
        }

        visited[depth] = true;
        combination(menuCount, arr, visited, depth + 1, n, r - 1);

        visited[depth] = false;
        combination(menuCount, arr, visited, depth + 1, n, r);
    }

}
