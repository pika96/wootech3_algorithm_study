package Algorithm.algorithm.Y2주차;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class M11052 {

    // DP[i]는 카드가 i개일 경우 최대 금액
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        String[] input = br.readLine().split(" ");

        int[] cardPrice = new int[N + 1];
        int[] DP = new int[N + 1];

        for (int i = 1; i <= N; i++) {
            cardPrice[i] = Integer.parseInt(input[i - 1]);
        }

        if (N == 1) {
            System.out.println(DP[1]);
            return;
        }

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= i; j++) {
                DP[i] = Math.max(DP[i], DP[i - j] + cardPrice[j]);
            }
        }

        int maxPrice = 0;

        for (int i = 1; i <= N; i++) {
            maxPrice = Math.max(maxPrice, DP[i]);
        }

        System.out.println(maxPrice);

    }
}
