package Algorithm.algorithm.Y1주차;

import java.util.Scanner;

public class M11047 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();
        int K = sc.nextInt();

        sc.nextLine();

        int[] coin = new int[N];
        for (int i = 0; i < N; i++) {
            coin[i] = Integer.parseInt(sc.nextLine());
        }

        int count = 0;

        for (int i = 1; i <= N; i++) {
            count += K / coin[N-i];
            K = K % coin[N-i];
        }

        System.out.println(count);
    }
}
