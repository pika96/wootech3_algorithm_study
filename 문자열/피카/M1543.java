package Algorithm.algorithm.Y1주차;

import java.util.Scanner;

public class M1543 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String text = sc.nextLine();
        String search = sc.nextLine();

        int count = 0;

        text = text.replaceAll(search, "1");

        for (int i = 0; i < text.length(); i++) {
            if(text.charAt(i) == '1') {
                count++;
            }
        }

        System.out.println(count);
    }
}
