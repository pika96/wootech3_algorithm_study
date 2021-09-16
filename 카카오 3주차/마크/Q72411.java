package com.binghe.programmers.kakao_2021;

import java.util.*;

public class Q72411 {
    static HashMap<String, Integer> map;

    static int m;

    public String[] solution(String[] orders, int[] course) {
        PriorityQueue<String> pq = new PriorityQueue<>();

        for (int i = 0; i < course.length; i++) {
            map = new HashMap<>();
            m = 0;

            for (int j = 0; j < orders.length; j++) {
                find(0, "", course[i], 0, orders[j]);
            }

            for (String s : map.keySet()) {
                if (map.get(s) == m && m > 1) {
                    pq.offer(s);
                }
            }
        }

        String ans[] = new String[pq.size()];
        int k = 0;
        while (!pq.isEmpty()) {
            ans[k++] = pq.poll();
        }
        return ans;
    }

    static void find(int cnt, String str, int targetNum, int idx, String word) {
        if (cnt == targetNum) {
            // 문자열 하나하나에 대하여 알파벳순으로
            char[] c = str.toCharArray();

            // 정렬
            Arrays.sort(c);
            String temps = "";

            // 정렬한 부분 다시 합치기
            for (int i = 0; i < c.length; i++)
                temps += c[i];
            
            // 각각의 음식 카운팅값.
            map.put(temps, map.getOrDefault(temps, 0) + 1);
            m = Math.max(m, map.get(temps));
            return;
        }

        for (int i = idx; i < word.length(); i++) {
            char now = word.charAt(i);
            find(cnt + 1, str + now, targetNum, i + 1, word);
        }
    }
}
