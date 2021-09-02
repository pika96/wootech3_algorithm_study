package com.binghe.acmicpc.graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Q2206 {

    private static final int[] dx = {0, 0, -1, 1};
    private static final int[] dy = {-1, 1, 0, 0};
    private static int height;
    private static int width;
    private static char[][] map;
    private static boolean[][][] visited;
    private static int result = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 입력
        String[] input = br.readLine().split(" ");
        height = Integer.parseInt(input[0]);
        width = Integer.parseInt(input[1]);

        // 입력
        map = new char[height][width];
        for (int i = 0; i < height; i++) {
            String mapInput = br.readLine();
            for (int j = 0; j < width; j++) {
                // 벽 찾으면서 맵 채우기
                map[i][j] = mapInput.charAt(j);
            }
        }

        // 방문 체크 초기화
        // visited[n][m][0] -> 벽을 부수고 탐색하는 경우
        // visited[n][m][1] -> 벽을 한 번 부수고 탐색하는 경우
        visited = new boolean[height][width][2];

        // bfs
        Queue<Node> queue = new LinkedList<>();
        queue.add(new Node(0, 0, 1, false));

        while (!queue.isEmpty()) {
            Node now = queue.poll();

            // 도착지점이면 종료
            if (now.x == height-1 && now.y == width-1) {
                result = now.dis;
                break;
            }

            for (int i = 0; i < 4; i++) {
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];

                if(nx >= 0 && nx < height && ny >= 0 && ny < width) {
                    if (map[nx][ny] == '0') { // 벽이 아닌 경우
                        if (!now.broken && !visited[nx][ny][0]) { // 벽을 한번도 부순 적이 없다면
                            queue.add(new Node(nx, ny, now.dis+1, false));
                            visited[nx][ny][0] = true;
                        } else if (now.broken && !visited[nx][ny][1]) { // 벽을 한번 주순 적이 있다면
                            queue.add(new Node(nx, ny, now.dis+1, true));
                            visited[nx][ny][1] = true;
                        }
                    } else if (map[nx][ny] == '1') { // 벽인 경우
                        if (!now.broken) { // 한번도 벽을 부순 적이 없다면
                            queue.add(new Node(nx, ny, now.dis + 1, true));
                            visited[nx][ny][1] = true;
                        }
                        // 한번 부순 적이 있다면 또 부수고 갈 수 없으므로 넘어감.
                    }
                }
            }
        }
        if (result == Integer.MAX_VALUE) {
            System.out.println(-1);
        } else {
            System.out.println(result);
        }
    }

    static class Node {
        int x;
        int y;
        int dis; // 이동 거리
        boolean broken; // 부순 여분

        public Node(int x, int y, int dis, boolean broken) {
            this.x = x;
            this.y = y;
            this.dis = dis;
            this.broken = broken;
        }
    }
}
