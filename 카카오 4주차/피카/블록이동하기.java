package Algorithm.algorithm.Y8주차;

import java.util.LinkedList;
import java.util.Queue;

public class 블록이동하기 {

    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, -1, 0, 1};

    static int[] rx0 = {-1, 0, -1, 0};
    static int[] ry0 = {0, 0, 1, 1};

    static int[] rx1 = {0, 1, 0, 1};
    static int[] ry1 = {-1, -1, 0, 0};

    static int N;

    static int[][] board = {{0, 0, 0, 1, 1}, {0, 0, 0, 1, 0}, {0, 1, 0, 1, 1}, {1, 1, 0, 0, 1},
            {0, 0, 0, 0, 0}};

    public static void main(String[] args) {
        System.out.println(solution(board));
    }

    public static int solution(int[][] board) {
        int answer = 0;
        N = board.length;
        answer = BFS(board);
        return answer;
    }

    public static int BFS(int[][] board) {

        boolean[][][] visited = new boolean[2][N][N];

        Queue<robot> q = new LinkedList();

        q.add(new robot(0, 0, 0, 0));
        visited[0][0][0] = true;
        while (!q.isEmpty()) {

            robot r = q.poll();
            int x = r.x;
            int y = r.y;
            int s = r.s;
            int t = r.t;

            if (isarrive(r)) {
                return t;
            }

            //이동
            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if (s == 0) {
                    if (nx >= 0 && ny >= 0 && nx < N && ny < N && ny + 1 < N) {
                        if (board[nx][ny] == 0 && board[nx][ny + 1] == 0) {
                            if (visited[s][nx][ny] == false) {
                                q.add(new robot(nx, ny, s, t + 1));
                                visited[s][nx][ny] = true;
                            }
                        }
                    }
                } else if (s == 1) {
                    if (nx >= 0 && ny >= 0 && nx < N && ny < N && nx + 1 < N) {
                        if (board[nx][ny] == 0 && board[nx + 1][ny] == 0) {
                            if (visited[s][nx][ny] == false) {
                                q.add(new robot(nx, ny, s, t + 1));
                                visited[s][nx][ny] = true;
                            }
                        }
                    }
                }
            }

            //회전
            for (int i = 0; i < 4; i++) {
                if (s == 0) {
                    int nx = x + rx0[i];
                    int ny = y + ry0[i];

                    if (nx >= 0 && ny >= 0 && nx + 1 < N && ny < N) {
                        if (board[nx][ny] == 0 && board[nx + 1][ny] == 0) {
                            if (visited[1][nx][ny] == false) {

                                //4가지 회전 검사
                                if (i == 0) {
                                    if (board[nx][ny + 1] == 0) {
                                        q.add(new robot(nx, ny, 1, t + 1));
                                        visited[1][nx][ny] = true;
                                    }
                                } else if (i == 1) {
                                    if (board[nx + 1][ny + 1] == 0) {
                                        q.add(new robot(nx, ny, 1, t + 1));
                                        visited[1][nx][ny] = true;
                                    }
                                } else if (i == 2) {
                                    if (board[nx][ny - 1] == 0) {
                                        q.add(new robot(nx, ny, 1, t + 1));
                                        visited[1][nx][ny] = true;
                                    }
                                } else if (i == 3) {
                                    if (board[nx + 1][ny - 1] == 0) {
                                        q.add(new robot(nx, ny, 1, t + 1));
                                        visited[1][nx][ny] = true;
                                    }
                                }

                            }
                        }
                    }
                } else if (s == 1) {
                    int nx = x + rx1[i];
                    int ny = y + ry1[i];

                    if (nx >= 0 && ny >= 0 && nx < N && ny + 1 < N) {
                        if (board[nx][ny] == 0 && board[nx][ny + 1] == 0) {
                            if (visited[0][nx][ny] == false) {

                                //4가지 회전 검사
                                if (i == 0) {
                                    if (board[nx + 1][ny] == 0) {
                                        q.add(new robot(nx, ny, 0, t + 1));
                                        visited[1][nx][ny] = true;
                                    }
                                } else if (i == 1) {
                                    if (board[nx - 1][ny] == 0) {
                                        q.add(new robot(nx, ny, 0, t + 1));
                                        visited[1][nx][ny] = true;
                                    }
                                } else if (i == 2) {
                                    if (board[nx + 1][ny + 1] == 0) {
                                        q.add(new robot(nx, ny, 0, t + 1));
                                        visited[1][nx][ny] = true;
                                    }
                                } else if (i == 3) {
                                    if (board[nx - 1][ny + 1] == 0) {
                                        q.add(new robot(nx, ny, 0, t + 1));
                                        visited[1][nx][ny] = true;
                                    }
                                }

                            }
                        }
                    }
                }
            }

        }
        return 0;
    }

    public static boolean isarrive(robot r) {

        int x = r.x;
        int y = r.y;
        int s = r.s;

        if (s == 0) {
            if (x == N - 1) {
                if (y == N - 1 || y + 1 == N - 1) {
                    return true;
                }
            }
        } else if (s == 1) {
            if (y == N - 1) {
                if (x == N - 1 || x + 1 == N - 1) {
                    return true;
                }
            }
        }
        return false;
    }

    public static class robot {

        int x, y, s, t;

        // *s -> ㅡ = 0 ㅣ  = 1
        robot(int x, int y, int s, int t) {
            this.x = x;
            this.y = y;
            this.s = s;
            this.t = t;
        }
    }
}
