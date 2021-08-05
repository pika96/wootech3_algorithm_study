package com.binghe.acmicpc.brute_force;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Q1339 {

    private static int N;
    private static String[] words;
    private static List<Character> alpha = new ArrayList<>();
    private static int[] alphaScore;
    private static boolean[] visited = new boolean[10];
    private static int result = 0;

    private static void permutation(int currentDepth) {
//        if (currentDepth == alpha.size()) {
//            for (int i = 0; i < alpha.size(); i++) {
//                System.out.print(alpha.get(i) + ": " + alphaScore[i]);
//                if (i != alpha.size() - 1) {
//                    System.out.print(", ");
//                }
//            }
//            System.out.println();
//            return;
//        }
        int maxDepth = alpha.size();
        if (currentDepth == maxDepth) {
            int sum = 0;
            for (int i = 0; i < N; i++) {
                int tmp = 0;
                for (int j = 0; j < words[i].length(); j++) {
                    tmp *= 10;
                    int alphaIdx = alpha.indexOf(words[i].charAt(j));
                    tmp += alphaScore[alphaIdx];
                }
                sum += tmp;
            }
            result = Math.max(result, sum);
            return;
        }

        for (int i = 0; i <= 9; i++) {
            if (!visited[i]) {
                visited[i] = true;
                alphaScore[currentDepth] = i;
                permutation(currentDepth + 1);
                visited[i] = false;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        // 단어 넣기
        words = new String[N];
        for (int i = 0; i < N; i++) {
            words[i] = br.readLine();
            for (char _alpha : words[i].toCharArray()) {
                if (!alpha.contains(_alpha)) {
                    alpha.add(_alpha);
                }
            }
        }

        alphaScore = new int[alpha.size()];
        permutation(0);
        System.out.println(result);
    }

}
