package com.binghe.acmicpc.string;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Q11047 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] input = br.readLine().split(" ");

        int N = Integer.parseInt(input[0]);
        int K = Integer.parseInt(input[1]);

        int[] coins = new int[N];

        for (int i = 0; i < N; i++) {
            coins[i] = Integer.parseInt(br.readLine());
        }

        int result = 0;
        for (int i = N-1; i >= 0; i--) {
            if (K == 0) {
                break;
            }
            if (K / coins[i] != 0) {
                result += (K / coins[i]);
                K = K % coins[i];
            }
        }
        System.out.println(result);
    }
}
