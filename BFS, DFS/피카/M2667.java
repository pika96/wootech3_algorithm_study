package Algorithm.algorithm.Y3주차;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class M2667 {

    static boolean[][] visited;
    static int[][] map;
    static int N;
    static int label;
    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        visited = new boolean[N][N];

        label = 2;

        for (int i = 0; i < N; i++) {
            map[i] = Arrays.stream(br.readLine().split(""))
                    .mapToInt(Integer::parseInt).toArray();
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (visited[i][j] == false && map[i][j] == 1) {
                    DFS(i, j);
                    label++;
                }
            }
        }


        int[] result = new int[label-2];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if(map[i][j] != 0) {
                    result[map[i][j] - 2]++;
                }
            }
        }

        Arrays.sort(result);

        System.out.println(label - 2);
        for (int i = 0; i < label-2; i++) {
            System.out.println(result[i]);
        }
    }

    public static void DFS(int x, int y) {
        visited[x][y] = true;
        map[x][y] = label;
        for (int k = 0; k < 4; k++) {
            int nx = x + dx[k];
            int ny = y + dy[k];

            if (nx >= 0 && nx < N && ny >= 0 && ny < N) {
                if (visited[nx][ny] == false && map[nx][ny] == 1) {
                    DFS(nx, ny);
                }
            }
        }
    }
}
