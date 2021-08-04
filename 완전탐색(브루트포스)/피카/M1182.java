package Algorithm.algorithm.Y2주차;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class M1182 {

    public static int count = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] input = br.readLine().split(" ");
        String[] stringNumbers = br.readLine().split(" ");
        int N = Integer.parseInt(input[0]);
        int S = Integer.parseInt(input[1]);

        int[] numbers = new int[N];

        for (int i = 0; i < N; i++) {
            numbers[i] = Integer.parseInt(stringNumbers[i]);
        }

        for (int i = 0; i < N; i++) {
            sum(i, S, numbers, 0);
        }
        System.out.println(count);
    }

    public static void sum(int n, int S, int[] numbers, int sum) {
        if (n == numbers.length) {
            return;
        }

        sum += numbers[n];

        if (sum == S) {
            count++;
        }

        for (int i = n; i < numbers.length; i++) {
            sum(i + 1, S, numbers, sum);
        }
    }
}
