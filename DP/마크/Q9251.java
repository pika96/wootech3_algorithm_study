package com.binghe.acmicpc.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Q9251 {

    static int[][] memo;

    static int LCS(String a, String b) {
        for(int i = 0; i <= b.length(); i++)
            memo[i][0] = 0;
        for(int i = 0; i <= a.length(); i++)
            memo[0][i] = 0;
        for(int i = 1; i <= b.length(); i++) {
            for(int j = 1; j <= a.length(); j++) {
                if(a.charAt(j-1) == b.charAt(i-1)) {
                    memo[i][j] = memo[i-1][j-1]+1;
                } else {
                    memo[i][j] = Math.max(memo[i-1][j], memo[i][j-1]);
                }
            }
        }
        return memo[b.length()][a.length()];
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String a = br.readLine();
        String b = br.readLine();

        memo = new int[b.length()+1][a.length()+1];

        System.out.println(LCS(a,b));
    }
}
