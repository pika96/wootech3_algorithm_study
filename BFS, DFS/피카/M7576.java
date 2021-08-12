package Algorithm.algorithm.Y3주차;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class M7576 {

    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] input = br.readLine().split(" ");
        int M = Integer.parseInt(input[0]);
        int N = Integer.parseInt(input[1]);

        int[][] box = new int[N][M];
        Queue<Pair> q = new LinkedList<>();
        int day = 1;

        for (int i = 0; i < N; i++) {
            String[] tomato = br.readLine().split(" ");
            for (int j = 0; j < tomato.length; j++) {
                box[i][j] = Integer.parseInt(tomato[j]);
                if (box[i][j] == 1) {
                    q.add(new Pair(i, j));
                }
            }
        }

        while (!q.isEmpty()) {
            int qsize = q.size();

            for (int i = 0; i < qsize; i++) {
                Pair curPosition = q.poll();

                int x = curPosition.x;
                int y = curPosition.y;

                for (int j = 0; j < 4; j++) {
                    int nx = x + dx[j];
                    int ny = y + dy[j];

                    if (nx >= 0 && nx < N && ny >= 0 && ny < M) {
                        if (box[nx][ny] == 0) {
                            box[nx][ny] = day;
                            q.add(new Pair(nx, ny));
                        }
                    }
                }
            }
            day++;
        }

        day = day - 2;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (box[i][j] == 0) {
                    day = -1;
                }
            }
        }

        System.out.println(day);
    }

    public static class Pair {

        int x, y;

        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
