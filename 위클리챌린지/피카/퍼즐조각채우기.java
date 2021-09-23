package Algorithm.algorithm.Y9주차;

import java.util.ArrayList;

public class 퍼즐조각채우기 {

//    static int[][] game_board = {{1, 1, 0, 0, 1, 0}, {0, 0, 1, 0, 1, 0}, {0, 1, 1, 0, 0, 1},
//            {1, 1, 0, 1, 1, 1}, {1, 0, 0, 0, 1, 0}, {0, 1, 1, 1, 0, 0}};
//    static int[][] table = {{1, 0, 0, 1, 1, 0}, {1, 0, 1, 0, 1, 0}, {0, 1, 1, 0, 1, 1},
//            {0, 0, 1, 0, 0, 0}, {1, 1, 0, 1, 1, 0}, {0, 1, 0, 0, 0, 0}};

    static int[][] game_board = {{0, 0, 0}, {1, 1, 0}, {1, 1, 1}};
    static int[][] table = {{1, 1, 1}, {1, 0, 0}, {0, 0, 0}};

    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, 1, 0, -1};
    static boolean[][] visited;
    static int N;
    static ArrayList<ArrayList<Pair>> game_board_list = new ArrayList<>();
    static ArrayList<ArrayList<Pair>> table_list = new ArrayList<>();

    public static void main(String[] args) {
        System.out.println(solution(game_board, table));
    }

    public static int solution(int[][] game_board, int[][] table) {
        int answer = 0;

        N = game_board.length;

        findBlock(game_board, 1, game_board_list);
        findBlock(table, 0, table_list);

        for (int i = 0; i < table_list.size(); i++) {
            ArrayList<Pair> table_pairs = table_list.get(i);
            boolean flag = false;

            for (int j = 0; j < game_board_list.size(); j++) {
                ArrayList<Pair> games_pairs = game_board_list.get(j);
                int[][] gameTable = initCloser(arrayTo2D(games_pairs));

                for (int d = 0; d < 4; d++) {
                    int[][] rotate = rotate(table_pairs, d);

                    int[][] zeroClose = initCloser(rotate);

                    if (isEqual2D(gameTable, zeroClose)) {
                        answer += games_pairs.size();

                        game_board_list.remove(games_pairs);
                        flag = true;
                        break;
                    }
                }

                if (flag) {
                    break;
                }
            }
        }

        return answer;
    }

    public static boolean isEqual2D(int[][] array1, int[][] array2) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (array1[i][j] != array2[i][j]) {
                    return false;
                }
            }
        }

        return true;
    }

    public static int[][] arrayTo2D(ArrayList<Pair> pairs) {

        int[][] result = new int[N][N];

        for (Pair pair : pairs) {
            result[pair.x][pair.y] = 1;
        }

        return result;
    }

    public static int[][] rotate(ArrayList<Pair> list, int dir) {
        // dir 0 : 0, 1 : 90, 2 : 180, 3 : 270

        int[][] board = new int[N][N];
        int[][] rotate = new int[N][N];

        for (int i = 0; i < list.size(); i++) {
            Pair pair = list.get(i);
            board[pair.x][pair.y] = 1;
        }

        if (dir == 0) {
            return board;
        }

        if (dir == 1) {
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    rotate[i][j] = board[N - j - 1][i];
                }
            }
        }

        if (dir == 2) {
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    rotate[i][j] = board[N - 1 - i][N - 1 - j];
                }
            }
        }

        if (dir == 3) {
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    rotate[i][j] = board[j][N - i - 1];
                }
            }
        }

        return rotate;
    }

    public static int[][] initCloser(int[][] board) {
        ArrayList<Pair> list = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (board[i][j] == 1) {
                    list.add(new Pair(i, j));
                }
            }
        }

        int minX = N;
        int minY = N;

        for (int i = 0; i < list.size(); i++) {
            Pair pair = list.get(i);

            minX = Math.min(minX, pair.x);
            minY = Math.min(minY, pair.y);
        }

        for (int i = 0; i < list.size(); i++) {
            Pair pair = list.get(i);

            list.set(i, new Pair(pair.x - minX, pair.y - minY));
        }

        int[][] result = new int[N][N];

        for (int i = 0; i < list.size(); i++) {
            Pair p = list.get(i);
            result[p.x][p.y] = 1;
        }

        return result;
    }

    public static void findBlock(int[][] map, int wall,
            ArrayList<ArrayList<Pair>> list) {
        int label = 2;

        visited = new boolean[N][N];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (visited[i][j] == false && map[i][j] != wall) {
                    list.add(new ArrayList<>());
                    DFS(i, j, label, map, wall, list);
                    label++;
                }
            }
        }

    }

    public static void DFS(int x, int y, int label, int[][] map, int wall,
            ArrayList<ArrayList<Pair>> list) {
        visited[x][y] = true;
        map[x][y] = label;
        list.get(label - 2).add(new Pair(x, y));
        for (int k = 0; k < 4; k++) {
            int nx = x + dx[k];
            int ny = y + dy[k];

            if (nx >= 0 && nx < N && ny >= 0 && ny < N) {
                if (visited[nx][ny] == false && map[nx][ny] != wall) {
                    DFS(nx, ny, label, map, wall, list);
                }
            }
        }
    }

    public static class Pair {

        int x, y;

        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
