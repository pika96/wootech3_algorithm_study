package com.binghe.acmicpc.brute_force;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Q1182 {

    private static int[] num = new int[21];
    private static int[] res = new int[21];
    private static int S;
    private static int result = 0;

    private static void combination(int currentDepth, int currentNumIdx, int maxNumIdx, int maxDepth) {
//        if (currentDepth == maxDepth) {
//            for (int i = 0; i < maxDepth; i++) {
//                System.out.print(res[i]);
//                if (i != maxDepth - 1) {
//                    System.out.print(' ');
//                }
//            }
//            System.out.println();
//            return;
//        }
        if (currentDepth == maxDepth) {
            int sum = 0;
            for (int i = 0; i < maxDepth; i++) {
                sum += res[i];
            }
            if (sum == S) {
                result++;
            }
            return;
        }

        if (currentNumIdx > maxNumIdx - 1) {
            return;
        }

        // O
        res[currentDepth] = num[currentNumIdx];
        combination(currentDepth + 1, currentNumIdx + 1, maxNumIdx, maxDepth);

        // X
        combination(currentDepth, currentNumIdx + 1, maxNumIdx, maxDepth);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] input = br.readLine().split(" ");
        int N = Integer.parseInt(input[0]);
        S = Integer.parseInt(input[1]);

        String[] inputNums = br.readLine().split(" ");
        for (int i = 0; i < N; i++) {
            num[i] = Integer.parseInt(inputNums[i]);
        }

        for (int i = 1; i <= N; i++) {
            combination(0, 0, N, i);
        }
        System.out.println(result);
    }
}
