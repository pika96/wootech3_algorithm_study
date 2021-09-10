package kakao2018;

public class FriendsBlock {

    public static void main(String[] args) {
        int m = 6;
        int n = 6;
        String[] input = {"TTTANT", "RRFACC", "RRRFCC", "TRRRAA", "TTMMMF", "TMMTTJ"};

        int ans = solution(m, n, input);
        System.out.println(ans);
    }

    public static int solution(int m, int n, String[] board) {
        String[][] map = new String[m][n];

        for (int i = 0; i < m; i++) {
            String[] row = board[i].split("");
            for (int j = 0; j < row.length; j++) {
                map[i][j] = row[j];
            }
        }

        int answer = 0;

        int fin = 0;
        String none = "-1";
        while (fin == 0) {
            int[][] erase = new int[m][n];
            fin = 1;

            for (int i = 0; i < m - 1; i++) {
                for (int j = 0; j < n - 1; j++) {
                    String start = map[i][j];

                    if (start.equals(none)) {
                        continue;
                    }

                    if (start.equals(map[i][j + 1]) && start.equals(map[i + 1][j])
                        && start.equals(map[i + 1][j + 1])) {
                        erase[i][j] = 1;
                        erase[i][j + 1] = 1;
                        erase[i + 1][j] = 1;
                        erase[i + 1][j + 1] = 1;
                    }
                }
            }

            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (erase[i][j] == 1) {
                        map[i][j] = none;
                        answer++;
                    }
                }
            }

            for (int i = m - 1; i > 0; i--) {
                for (int j = n - 1; j >= 0; j--) {
                    if (map[i][j].equals(none)) {
                        int check = i - 1;
                        while (check >= 0) {
                            if (!map[check][j].equals(none)) {
                                break;
                            }

                            check--;
                        }

                        if (check != -1) {
                            fin = 0;
                            map[i][j] = map[check][j];
                            map[check][j] = none;
                        }
                    }
                }
            }
        }

        return answer;
    }
}
