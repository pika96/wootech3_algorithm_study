package Algorithm.algorithm.Y2주차;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class M9251 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String A = br.readLine();
        String B = br.readLine();

        int[][] LCS = new int[A.length() + 1][B.length() + 1];

        for (int i = 1; i < A.length() + 1; i++) {
            for (int j = 1; j < B.length() + 1; j++) {
                if (A.charAt(i - 1) == B.charAt(j - 1)) {
                    LCS[i][j] = Math
                            .max(Math.max(LCS[i - 1][j - 1] + 1, LCS[i][j - 1]), LCS[i - 1][j]);
                } else {
                    LCS[i][j] = Math.max(LCS[i][j - 1], LCS[i - 1][j]);
                }
            }
        }

        int ans = 0;

        for (int i = 1; i < A.length() + 1; i++) {
            for (int j = 1; j < B.length() + 1; j++) {
                ans = Math.max(ans, LCS[i][j]);
            }
        }

        System.out.println(ans);
    }
}
