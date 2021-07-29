package Algorithm.algorithm.Y1주차;

import java.util.PriorityQueue;
import java.util.Scanner;

public class M1715 {

    public static void main(String[] args) {

        // 1~ 100000개 이기 때문에 long을 사용해야한다.
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();

        PriorityQueue<Long> q = new PriorityQueue<>();

        for (int i = 0; i < N; i++) {
            q.add(sc.nextLong());
        }

        long compare = 0;

        while (true) {

            if (q.size() == 1) {
                break;
            }

            long a = q.poll();
            long b = q.poll();

            compare += a;
            compare += b;



            q.add(a + b);
        }

        System.out.println(compare);
    }
}
