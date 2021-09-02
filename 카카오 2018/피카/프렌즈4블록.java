package Algorithm.algorithm.Y6주차;

import java.util.LinkedList;
import java.util.Queue;

public class 프렌즈4블록 {

    static int m = 4;
    static int n = 5;
    static String[] board = {"CCBDE", "AAADE", "AAABF", "CCBBF"};

    public static void main(String[] args) {
        System.out.println(solution(m, n, board));
    }

    public static int solution(int m, int n, String[] board) {
        int answer = 0;

        String[][] blocks = new String[m][n];

        for (int i = 0; i < m; i++) {
            String[] split = board[i].split("");
            for (int j = 0; j < n; j++) {
                blocks[i][j] = split[j];
            }
        }

        while (true) {
            int count = 0;

            Queue<Pair> q = new LinkedList<>();

            for (int i = 0; i < m - 1; i++) {
                for (int j = 0; j < n - 1; j++) {
                    String block = blocks[i][j];

                    if (block.equals("0")) {
                        continue;
                    }

                    String down = blocks[i+1][j];
                    String right = blocks[i][j+1];
                    String diag = blocks[i + 1][j + 1];

                    if (block.equals(down) && block.equals(right) && block.equals(diag)) {
                        q.add(new Pair(i, j));
                    }
                }
            }

            while (!q.isEmpty()) {
                Pair p = q.poll();

                int x = p.x;
                int y = p.y;

                if (!blocks[x][y].equals("0")) {
                    blocks[x][y] = "0";
                    count++;
                }
                if (!blocks[x + 1][y].equals("0")) {
                    blocks[x + 1][y] = "0";
                    count++;
                }
                if (!blocks[x][y + 1].equals("0")) {
                    blocks[x][y + 1] = "0";
                    count++;
                }
                if (!blocks[x + 1][y + 1].equals("0")) {
                    blocks[x + 1][y + 1] = "0";
                    count++;
                }
            }

            answer += count;
            if (count == 0) {
                break;
            }

            for (int i = 0; i < n; i++) {
                Queue<String> line = new LinkedList<>();
                for (int j = m-1; j >= 0; j--) {
                    String block = blocks[j][i];
                    if(!block.equals("0")) {
                        line.add(block);
                    }
                }

                for (int j = m-1; j >= 0; j--) {
                    String block = line.poll();
                    if(block == null) {
                        blocks[j][i] = "0";
                    } else {
                        blocks[j][i] = block;
                    }
                }

            }
        }

        return answer;
    }

    static class Pair {

        int x, y;

        Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
