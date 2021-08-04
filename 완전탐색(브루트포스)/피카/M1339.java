package Algorithm.algorithm.Y2주차;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class M1339 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[] alphaDigit = new int[26];

        String[] alpha = new String[N];

        for (int i = 0; i < N; i++) {
            alpha[i] = br.readLine();
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < alpha[i].length(); j++) {
                char c = alpha[i].charAt(j);
                alphaDigit[c - 'A'] += Math.pow(10, alpha[i].length() - j - 1);
            }
        }

        Arrays.sort(alphaDigit);

        int num = 9;
        int ans = 0;

        for (int i = 25; i > 0; i--) {
            if(alphaDigit[i] == 0) {
                break;
            }
            ans += alphaDigit[i] * num;
            num--;
        }

        System.out.println(ans);
    }
}
