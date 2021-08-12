package Algorithm.algorithm.Y3주차;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class M1260 {

    static boolean[] visited;
    static ArrayList<ArrayList<Integer>> list;
    static int[] DFS;
    static int[] BFS;
    static int idx;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] input = br.readLine().split(" ");

        int N = Integer.parseInt(input[0]);
        int M = Integer.parseInt(input[1]);
        int V = Integer.parseInt(input[2]);

        visited = new boolean[N + 1];

        list = new ArrayList<>();

        for (int i = 0; i <= N; i++) {
            list.add(new ArrayList<>());
        }

        for (int i = 0; i < M; i++) {
            String[] numbers = br.readLine().split(" ");
            int A = Integer.parseInt(numbers[0]);
            int B = Integer.parseInt(numbers[1]);

            list.get(A).add(B);
            list.get(B).add(A);
        }

        for (int i = 0; i <= N; i++) {
            Collections.sort(list.get(i));
        }
        // DFS
        idx = 0;
        DFS = new int[N];
        DFS(V);

        // BFS
        Queue<Integer> q = new LinkedList<>();
        visited = new boolean[N + 1];

        idx = 0;
        BFS = new int[N];

        q.add(V);
        visited[V] = true;
        while (!q.isEmpty()) {
            int qsize = q.size();

            for (int i = 0; i < qsize; i++) {
                int num = q.poll();
                BFS[idx++] = num;

                for (int j = 0; j < list.get(num).size(); j++) {
                    int next = list.get(num).get(j);

                    if (!visited[next]) {
                        visited[next] = true;
                        q.add(next);
                    }
                }
            }
        }

        for (int i = 0; i < N; i++) {
            if (DFS[i] != 0) {
                System.out.print(DFS[i] + " ");
            }
        }

        System.out.println();
        for (int i = 0; i < N; i++) {
            if (BFS[i] != 0) {
                System.out.print(BFS[i] + " ");
            }
        }
    }

    public static void DFS(int cur) {
        visited[cur] = true;
        DFS[idx++] = cur;
        for (int i = 0; i < list.get(cur).size(); i++) {
            int next = list.get(cur).get(i);

            if (!visited[next]) {
                DFS(next);
            }
        }

    }
}
