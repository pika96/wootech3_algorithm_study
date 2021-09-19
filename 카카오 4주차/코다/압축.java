package kakao2018;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class 압축 {

    public static void main(String[] args) {

        String msg = "TOBEORNOTTOBEORTOBEORNOT";
        int[] ans = solution(msg);

        for (int i = 0; i < ans.length; i++) {
            System.out.println(ans[i] + " ");
        }
    }

    private static int[] solution(String msg) {
        Map<String, Integer> dic = new HashMap<>();

        // 사전 초기화
        char word = 'A';
        for (int i = 1; i < 27; i++) {
            dic.put(String.valueOf(word), i);
            word++;
        }

        int current = 0;
        List<Integer> indexes = new ArrayList<>();

        while(current < msg.length()) {
            StringBuilder sb = new StringBuilder();
            sb.append(msg.charAt(current));
            current++;

            int index = dic.get(sb.toString());
            while (current < msg.length() && dic.containsKey(sb.append(msg.charAt(current)).toString())) {
                index = dic.get(sb.toString());
                current++;
            }

            indexes.add(index);
            dic.put(sb.toString(), dic.size() + 1);
        }

        return indexes.stream().mapToInt(i -> i).toArray();
    }
}
