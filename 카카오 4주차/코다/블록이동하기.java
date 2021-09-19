package kakao2020;

import java.util.LinkedList;
import java.util.Queue;

public class 블록이동하기 {

    public static void main(String[] args) {

        int[][] board = {{0, 0, 0, 1, 1}, {0, 0, 0, 1, 0}, {0, 1, 0, 1, 1}, {1, 1, 0, 0, 1}, {0, 0, 0, 0, 0}};
        System.out.println(solution(board));

    }

    private static int solution(int[][] board) {
        int[][] newboard = new int[board.length + 2][board.length + 2];

        for (int i = 0; i < newboard.length; i++) {
            for (int j = 0; j < newboard.length; j++) {
                if (i == 0 || i == newboard.length - 1 || j == 0 || j == newboard.length - 1) {
                    newboard[i][j] = 1;
                } else {
                    newboard[i][j] = board[i - 1][j - 1];
                }
            }
        }

        return bfs(newboard);
    }

    private static int bfs(int[][] board) {
        int[] moveX = {0, 0, 1, -1};
        int[] moveY = {1, -1, 0, 0};

        Queue<Robot> qu = new LinkedList<>();
        qu.add(new Robot(1, 1, 1, 2, 0));
        boolean[][][] visit = new boolean[2][board.length][board[0].length];
        visit[0][1][1] = true;

        int[][][] distance = new int[2][board.length][board[0].length];
        distance[0][1][1] = 0;

        while (!qu.isEmpty()) {

            int quSize = qu.size();

            for (int i = 0; i < quSize; i++) {
                Robot current = qu.poll();
                int currentDistance = distance[current.dir][current.x][current.y];

                if ((current.x == board.length - 2 && current.y == board.length - 2) ||
                    (current.s == board.length - 2 && current.t == board.length - 2)) {

                    return currentDistance; //N, N에 도착하면 return
                }

                for (int j = 0; j < 4; j++) { //평행 이동
                    int nextX = current.x + moveX[j];
                    int nextY = current.y + moveY[j];
                    int nextS = current.s + moveX[j];
                    int nextT = current.t + moveY[j];

                    if (board[nextX][nextY] == 0 && board[nextS][nextT] == 0
                        && visit[current.dir][nextX][nextY] == false) {
                        qu.add(new Robot(nextX, nextY, nextS, nextT, current.dir));
                        visit[current.dir][nextX][nextY] = true;
                        distance[current.dir][nextX][nextY] = currentDistance + 1;
                    }
                }

                if (current.dir == 0) { //로봇이 가로
                    if (board[current.x + 1][current.y] == 0
                        && board[current.s + 1][current.t] == 0) { //위로 회전

                        if (visit[1][current.x][current.y] == false) {
                            qu.add(new Robot(current.x, current.y, current.x + 1, current.y, 1));
                            visit[1][current.x][current.y] = true;
                            distance[1][current.x][current.y] = currentDistance + 1;
                        }

                        if (visit[1][current.s][current.t] == false) {
                            qu.add(new Robot(current.s, current.t, current.s + 1, current.t, 1));
                            visit[1][current.s][current.t] = true;
                            distance[1][current.s][current.t] = currentDistance + 1;
                        }
                    }

                    if (board[current.x - 1][current.y] == 0
                        && board[current.s - 1][current.t] == 0) { //아래로 회전

                        if (visit[1][current.x - 1][current.y] == false) {
                            qu.add(new Robot(current.x - 1, current.y, current.x, current.y, 1));
                            visit[1][current.x - 1][current.y] = true;
                            distance[1][current.x - 1][current.y] = currentDistance + 1;
                        }

                        if (visit[1][current.s - 1][current.t] == false) {
                            qu.add(new Robot(current.s - 1, current.t, current.s, current.t, 1));
                            visit[1][current.s - 1][current.t] = true;
                            distance[1][current.s - 1][current.t] = currentDistance + 1;
                        }
                    }
                }

                if (current.dir == 1) { //로봇이 세로
                    if (board[current.x][current.y - 1] == 0
                        && board[current.s][current.t - 1] == 0) { //왼쪽으로 회전

                        if (visit[0][current.x][current.y - 1] == false) {
                            qu.add(new Robot(current.x, current.y - 1, current.x, current.y, 0));
                            visit[0][current.x][current.y - 1] = true;
                            distance[0][current.x][current.y - 1] = currentDistance + 1;
                        }

                        if (visit[0][current.s][current.t - 1] == false) {
                            qu.add(new Robot(current.s, current.t - 1, current.s, current.t, 0));
                            visit[0][current.s][current.t - 1] = true;
                            distance[0][current.s][current.t - 1] = currentDistance + 1;
                        }
                    }

                    if (board[current.x][current.y + 1] == 0
                        && board[current.s][current.t + 1] == 0) { //오른쪽으로 회전

                        if (visit[0][current.x][current.y] == false) {
                            qu.add(new Robot(current.x, current.y, current.x, current.y + 1, 0));
                            visit[0][current.x][current.y] = true;
                            distance[0][current.x][current.y] = currentDistance + 1;
                        }

                        if (visit[0][current.s][current.t] == false) {
                            qu.add(new Robot(current.s, current.t, current.s, current.t + 1, 0));
                            visit[0][current.s][current.t] = true;
                            distance[0][current.s][current.t] = currentDistance + 1;
                        }
                    }
                }
            }

        }

        return -1;
    }

    public static class Robot {

        int x;
        int y;
        int s;
        int t;

        int dir;

        public Robot(int x, int y, int s, int t, int dir) {
            this.x = x;
            this.y = y;
            this.s = s;
            this.t = t;
            this.dir = dir;
        }
    }
}
