package Algorithm.algorithm.Y1주차;

import java.util.Scanner;

public class M9322 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int T = Integer.parseInt(scanner.nextLine());

        for (int t = 0; t < T; t++) {
            int n = Integer.parseInt(scanner.nextLine());

            String[] first_pub = scanner.nextLine().split(" ");
            String[] second_pub = scanner.nextLine().split(" ");
            String[] cypher = scanner.nextLine().split(" ");

            String[] plain = new String[n];

            for (int i = 0; i < n; i++) {
                String f = first_pub[i];
                for (int j = 0; j < n; j++) {
                    if (second_pub[j].equals(f)) {
                        plain[i] = cypher[j];
                    }
                }
            }

            for (int i = 0; i < n; i++) {
                System.out.print(plain[i] + " ");
            }
            System.out.println();
        }
    }
}
