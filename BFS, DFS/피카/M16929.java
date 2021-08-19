package Algorithm.algorithm.Y4주차;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class M16929 {

    static int[] dx = {0, -1, 0, 1};
    static int[] dy = {1, 0, -1, 0};

    static int N;
    static int M;
    static boolean[][] visited;
    static boolean cycle = false;
    static int initPositionX;
    static int initPositionY;
    static String[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] input = br.readLine().split(" ");

        N = Integer.parseInt(input[0]);
        M = Integer.parseInt(input[1]);

        map = new String[N][M];

        for (int i = 0; i < N; i++) {
            map[i] = br.readLine().split("");
        }

//        LinkedList<String> dots = new LinkedList<String>();
//
//        for (int i = 0; i < N; i++) {
//            for (int j = 0; j < M; j++) {
//                String dot = map[i][j];
//                if (!dots.contains(dot)) {
//                    dots.add(dot);
//                }
//            }
//        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                visited = new boolean[N][M];
                initPositionX = i;
                initPositionY = j;
                DFS(i, j, map[i][j], 0);

                if (cycle) {
                    System.out.println("Yes");
                    return;
                }
            }
        }

        System.out.println("No");
    }

    public static void DFS(int x, int y, String dot, int distance) {
        if (initPositionX == x && initPositionY == y && distance >= 4) {
            cycle = true;
            return;
        }

        if (cycle) {
            return;
        }

        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (nx >= 0 && ny >= 0 && nx < N && ny < M) {
                if(map[nx][ny].equals(dot)) {
                    if(!visited[nx][ny]) {
                        visited[nx][ny] = true;
                        DFS(nx, ny, dot, distance + 1);
                        visited[nx][ny] = false;
                    }
                }
            }
        }
    }

    public static class Pair {

        public int x, y;

        Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
