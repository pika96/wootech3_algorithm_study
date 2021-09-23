package weeklychallenge;

import java.util.HashMap;
import java.util.Map;

public class 상호평가 {

    public static void main(String[] args) {

        int[][] scores = {{75, 50, 100},{75, 100, 20}, {100, 100, 20}};

        System.out.println(solution(scores));

    }

    public static String solution(int[][] scores) {
        for (int j = 0; j < scores.length; j++) {
            int min = 101;
            int max = 0;
            Map<Integer, Integer> count = new HashMap<>();
            for (int i = 0; i < scores.length; i++) {
                if (scores[i][j] < min) {
                    min = scores[i][j];
                }

                if (scores[i][j] > max) {
                    max = scores[i][j];
                }

                int num = count.getOrDefault(scores[i][j], 0);
                num++;
                count.put(scores[i][j], num);
            }

            if (scores[j][j] == min && count.get(scores[j][j]) == 1) {
                scores[j][j] = -1;
            }

            if (scores[j][j] == max && count.get(scores[j][j]) == 1) {
                scores[j][j] = -1;
            }
        }

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < scores.length; i++) {
            int count = 0;
            int sum = 0;
            for (int j = 0; j < scores.length; j++) {
                int current = scores[j][i];
                if (current != -1) {
                    count++;
                    sum += current;
                }
            }

            calculateGrade(sb, sum / count);
        }

        return sb.toString();
    }

    private static void calculateGrade(StringBuilder sb, int score) {
        if (score >= 90) {
            sb.append("A");
            return;
        }

        if (score >= 80) {
            sb.append("B");
            return;
        }

        if (score >= 70) {
            sb.append("C");
            return;
        }

        if (score >= 50) {
            sb.append("D");
            return;
        } else {
            sb.append("F");
        }
    }
}
