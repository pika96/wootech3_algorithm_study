package Algorithm.algorithm.Y9주차;

public class 상호평가 {

    static int[][] scores = {{100, 90, 98, 88, 65}, {50, 45, 99, 85, 77}, {47, 88, 95, 80, 67},
            {61, 57, 100, 80, 65}, {24, 90, 94, 75, 65}};

    public static void main(String[] args) {
        System.out.println(solution(scores));
    }

    public static String solution(int[][] scores) {
        String answer = "";

        for (int i = 0; i < scores[0].length; i++) {
            int maxNum = -1;
            int minNum = 101;

            for (int j = 0; j < scores.length; j++) {
                if (maxNum < scores[j][i]) {
                    maxNum = scores[j][i];
                }
                if (minNum > scores[j][i]) {
                    minNum = scores[j][i];
                }
            }

            boolean maxFlag = true;
            boolean minFlag = true;

            for (int j = 0; j < scores.length; j++) {
                if(j == i)
                    continue;

                if(maxNum == scores[j][i]) {
                    maxFlag = false;
                }

                if(minNum == scores[j][i]) {
                    minFlag = false;
                }
            }

            if(maxNum == scores[i][i] && maxFlag) {
                scores[i][i] = -1;
            }

            if(minNum == scores[i][i] && minFlag) {
                scores[i][i] = -1;
            }
        }

        for (int i = 0; i < scores[0].length; i++) {
            int count = 0;
            int score = 0;
            for (int j = 0; j < scores.length; j++) {
                if (scores[i][j] != -1) {
                    score += scores[j][i];
                    count++;
                }
            }
            long avg = score / count;
            answer += calculate(avg);
        }

        return answer;
    }

    private static String calculate(long avg) {
        if (avg >= 90) {
            return "A";
        } else if (avg >= 80) {
            return "B";
        } else if (avg >= 70) {
            return "C";
        } else if (avg >= 50) {
            return "D";
        } else {
            return "F";
        }
    }
}
