package Algorithm.algorithm.Y4주차;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class M2206 {

    static int[][] map;
    static int N;
    static int M;
    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] input = br.readLine().split(" ");

        N = Integer.parseInt(input[0]);
        M = Integer.parseInt(input[1]);

        map = new int[N][M];

        for (int i = 0; i < N; i++) {
            String[] line = br.readLine().split("");
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(line[j]);
            }
        }

        boolean[][][] visited = new boolean[N][M][2];

        Queue<Pair> q = new LinkedList<>();
        q.add(new Pair(0, 0, false, 0));
        visited[0][0][0] = true;
        visited[0][0][1] = true;

        while (!q.isEmpty()) {
            Pair p = q.poll();

            int x = p.x;
            int y = p.y;
            boolean breakWall = p.breakWall;
            int distance = p.distance;

            if(x == N-1 && y == M-1) {
                System.out.println(distance + 1);
                return;
            }

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if (nx >= 0 && ny >= 0 && nx < N && ny < M) {
                    if (!breakWall) { // 벽을 안부쉈을때
                        if(visited[nx][ny][0]) {
                            continue;
                        }

                        if(map[nx][ny] == 1) {
                            visited[nx][ny][1] = true;
                            q.add(new Pair(nx, ny, true, distance + 1));
                        }
                        else {
                            visited[nx][ny][0] = true;
                            q.add(new Pair(nx, ny, false, distance + 1));
                        }
                    } else { // 벽을 부쉈을 때
                        if(!visited[nx][ny][1] && map[nx][ny] == 0) {
                            visited[nx][ny][1] = true;
                            q.add(new Pair(nx, ny, breakWall, distance + 1));
                        }
                    }
                }
            }
        }

        System.out.println(-1);
    }

    public static class Pair {

        public int x, y;
        public boolean breakWall;
        public int distance;

        Pair(int x, int y, boolean breakWall, int distance) {
            this.x = x;
            this.y = y;
            this.breakWall = breakWall;
            this.distance = distance;
        }
    }
}

/*
13 13
0100011011000
0001001010000
0000100001000
1100010101011
1111101111000
1011010001001
0100001001001
0100111010001
0101010000100
0001110100000
0000001000100
1010001000111
1001000100000

정답 : 27
* */