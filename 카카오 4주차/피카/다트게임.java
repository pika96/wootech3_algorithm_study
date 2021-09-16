package Algorithm.algorithm.Y8주차;

import java.util.Arrays;

public class 다트게임 {

    static String dartResult = "1T2D3D#";

    public static void main(String[] args) {
        System.out.println(solution(dartResult));
    }

    public static int solution(String dartResult) {
        int answer = 0;

        String[] game = splitScore(dartResult);

        int[] score = new int[3];

        Arrays.fill(score, 1);

        for (int i = 2; i >= 0; i--) {

            String num = "";
            char SDT = 'S';
            char option = 'X';


            for (int j = 0; j < game[i].length(); j++) {
                char c = game[i].charAt(j);
                if(c >= '0' && c <= '9') {
                    num += c;
                } else if(c == 'S' || c == 'D' || c == 'T') {
                    SDT = c;
                } else {
                    option = c;
                }
            }

            int sq = 1;

            if (SDT == 'D') {
                sq = 2;
            } else if (SDT == 'T') {
                sq = 3;
            }

            score[i] *= Math.pow(Integer.valueOf(num), sq);

            if (option != 'X') {

                if (option == '*') {
                    if (i == 0) {
                        score[i] *= 2;
                    } else {
                        score[i] *= 2;
                        score[i - 1] *= 2;
                    }
                } else if (option == '#') {
                    score[i] *= -1;
                }
            }
        }

        answer = score[0] + score[1] + score[2];

        return answer;
    }

    private static String[] splitScore(String dartResult) {

        String[] game = new String[3];

        int idx = 0;

        for (int i = 0; i < 3; i++) {
            StringBuilder sb = new StringBuilder();

            for (int j = idx; j < dartResult.length(); j++) {
                char c = dartResult.charAt(j);

                if (c >= '0' && c <= '9') {
                    sb.append(c);
                } else {
                    if (c == '*' || c == '#') {
                        sb.append(c);
                        idx = j + 1;
                        break;
                    } else {
                        if (j + 1 >= dartResult.length()) {
                            sb.append(c);
                            idx = j + 1;
                            break;
                        }

                        char next = dartResult.charAt(j + 1);
                        if (next == '*' || next == '#') {
                            sb.append(c);
                        } else {
                            sb.append(c);
                            idx = j + 1;
                            break;
                        }
                    }
                }

            }
            game[i] = sb.toString();
        }

        return game;
    }
}
