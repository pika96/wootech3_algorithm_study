package com.binghe.acmicpc.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Q11052 {
    public static int N;
    public static int[] P;
    public static int[] memo = new int[1001];

    public static int dp(int n) {
        if(n == 0)
            return 0;
        if(memo[n] > 0)
            return memo[n];

        // 카드 몇 개인가? 알 수 없으므로 1개부터 n개까지 모두 든 카드를 돌면서 최대 값을 찾는다.
        for(int i = 1; i <= n; i++) {
            memo[n] = Math.max(memo[n], dp(n-i) + P[i]);
        }
        return memo[n];
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        P = new int[N+1];

        String[] input = br.readLine().split(" ");
        for(int i = 0; i < N; i++) {
            P[i+1] = Integer.parseInt(input[i]);
        }

        System.out.println(dp(N));
    }
}
