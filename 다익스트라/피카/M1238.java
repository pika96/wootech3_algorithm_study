package Algorithm.algorithm.Y4주차;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;

public class M1238 {

    static ArrayList<ArrayList<Pair>> list;
    static int N;
    static int M;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] input = br.readLine().split(" ");

        N = Integer.parseInt(input[0]);
        M = Integer.parseInt(input[1]);
        int X = Integer.parseInt(input[2]);

        list = new ArrayList<>();

        for (int i = 0; i <= N; i++) {
            list.add(new ArrayList<>());
        }

        for (int i = 0; i < M; i++) {
            String[] node = br.readLine().split(" ");

            int start = Integer.parseInt(node[0]);
            int end = Integer.parseInt(node[1]);
            int weight = Integer.parseInt(node[2]);

            list.get(start).add(new Pair(start, end, weight));
        }

        int[][] dist = new int[N + 1][N + 1];

        for (int i = 1; i <= N; i++) {
            dist[i] = dijkstra(i);
        }

        int ans = 0;

        for (int i = 1; i <= N; i++) {
            ans = Math.max(dist[i][X] + dist[X][i], ans);
        }

        System.out.println(ans);
    }

    private static int[] dijkstra(int start) {
        int[] dist = new int[N + 1];
        boolean[] visited = new boolean[N + 1];
        PriorityQueue<Node> q = new PriorityQueue<>();

        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[start] = 0;
        q.add(new Node(start, 0));

        while (!q.isEmpty()) {
            Node n = q.poll();

            int curIndex = n.index;
            visited[curIndex] = true;

            for (int i = 0; i < list.get(curIndex).size(); i++) {
                int nextIndex = list.get(curIndex).get(i).end;
                int weight = list.get(curIndex).get(i).weight;

                if (!visited[nextIndex] && dist[nextIndex] > dist[curIndex] + weight) {
                    dist[nextIndex] = dist[curIndex] + weight;
                    q.add(new Node(nextIndex, dist[nextIndex]));
                }
            }
        }

        return dist;
    }

    public static class Node implements Comparable<Node> {

        public int index, distance;

        public Node(int index, int distance) {
            this.index = index;
            this.distance = distance;
        }

        @Override
        public int compareTo(Node n) {
            return this.distance - n.distance;
        }
    }

    public static class Pair {

        public int start, end, weight;

        Pair(int start, int end, int weight) {
            this.start = start;
            this.end = end;
            this.weight = weight;
        }
    }
}
