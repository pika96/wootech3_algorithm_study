package com.binghe.acmicpc.graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Q16929 {
    private static final int[] dx = {0, 0, -1, 1};
    private static final int[] dy = {-1, 1, 0, 0};

    private static int width;
    private static int height;
    private static char[][] map;
    private static boolean[][] visited;

    private static boolean dfs(int currentX, int currentY, int prevX, int prevY, char color) {
        if (visited[currentX][currentY]) {
            return true;
        }
        visited[currentX][currentY] = true;
        for (int i = 0; i < 4; i++) {
            int nextX = currentX + dx[i];
            int nextY = currentY + dy[i];
            if(nextX >= 0 && nextX < height && nextY >= 0 && nextY < width) {
                if ( !(prevX == nextX && prevY == nextY) && (map[nextX][nextY] == color) ){
                    if (dfs(nextX, nextY, currentX, currentY, color))
                        return true;
                }
            }
        }
        return false;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] input = br.readLine().split(" ");
        height = Integer.parseInt(input[0]);
        width = Integer.parseInt(input[1]);

        // 입력
        map = new char[height][width];
        for (int i = 0; i < height; i++) {
            String mapInput = br.readLine();
            for (int j = 0; j < width; j++) {
                map[i][j] = mapInput.charAt(j);
            }
        }

        // 방문 체크
        visited = new boolean[height][width];

        // 순회하며 사이클 찾기
        for(int i = 0; i < height; i++){
            for(int j = 0; j < width; j++){
                if(!visited[i][j]){
                    if(dfs(i, j, -1, -1, map[i][j])){
                        System.out.println("Yes");
                        System.exit(0);
                    }
                }
            }
        }
        System.out.println("No");
    }

}
