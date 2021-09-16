package Algorithm.algorithm.Y8주차;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

public class 압축 {

    static String msg = "TOBEORNOTTOBEORTOBEORNOT";

    public static void main(String[] args) {
        for (int i : solution(msg)) {
            System.out.println(i);
        }
    }

    public static int[] solution(String msg) {
        int[] answer = {};

        Deque<String> q = new ArrayDeque<>();

        for (int i = 0; i < msg.length(); i++) {
            q.add(msg.charAt(i) + "");
        }

        Map<String, Integer> dic = new HashMap<>();

        for (int i = 1; i <= 26; i++) {
            char c = (char) (i - 1 + 'A');
            dic.put(String.valueOf(c), i);
        }

        ArrayList<Integer> ans = new ArrayList<>();

        int dicIdx = 27;

        while (!q.isEmpty()) {

            String word = q.poll();
            while (true) {
                if (q.isEmpty()) {
                    if (!dic.containsKey(word)) {
                        ans.add(dic.get(word.substring(0, word.length() - 1)));
                        q.addFirst(word.charAt(word.length() - 1) + "");
                    } else {
                        ans.add(dic.get(word));
                    }
                    break;
                }

                if (!dic.containsKey(word)) {
                    ans.add(dic.get(word.substring(0, word.length() - 1)));
                    q.addFirst(word.charAt(word.length() - 1) + "");
                    break;
                }

                word += q.poll();
            }

            dic.put(word, dicIdx++);
        }

        answer = new int[ans.size()];

        for (int i = 0; i < ans.size(); i++) {
            answer[i] = ans.get(i);
        }
        return answer;
    }
}
