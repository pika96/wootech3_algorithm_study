package Algorithm.algorithm.Y7주차;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;

public class 합승택시요금 {

    static int n = 6;
    static int s = 4;
    static int a = 6;
    static int b = 2;
    static int[][] fares = {{4, 1, 10}, {3, 5, 24}, {5, 6, 2}, {3, 1, 41}, {5, 1, 24}, {4, 6, 50},
            {2, 4, 66}, {2, 3, 22}, {1, 6, 25}};

//    static int n = 7;
//    static int s = 3;
//    static int a = 4;
//    static int b = 1;
//    static int[][] fares = {{5, 7, 9}, {4, 6, 4}, {3, 6, 1}, {3, 2, 3}, {2, 1, 6}};

    public static void main(String[] args) {
        System.out.println(solution(n, s, a, b, fares));
    }

    static ArrayList<ArrayList<Pair>> list;
    static final int INT_MAX = 99999999;

    public static int solution(int n, int s, int a, int b, int[][] fares) {
        int answer = INT_MAX;

        list = new ArrayList<>();

        for (int i = 0; i <= n; i++) {
            list.add(new ArrayList<>());
        }

        for (int i = 0; i < fares.length; i++) {
            int[] fare = fares[i];

            int v1 = fare[0];
            int v2 = fare[1];
            int w = fare[2];

            list.get(v1).add(new Pair(v1, v2, w));
            list.get(v2).add(new Pair(v2, v1, w));
        }

        int[][] dist = new int[n + 1][n + 1];

        dist[s] = dijkstra(s, n);
        dist[a] = dijkstra(a, n);
        dist[b] = dijkstra(b, n);

        for (int i = 1; i <= n; i++) {
            int fare = dist[s][i] + dist[a][i] + dist[b][i];

            answer = Math.min(answer, fare);
        }

        return answer;
    }

    private static int[] dijkstra(int start, int n) {
        int[] dist = new int[n + 1];
        boolean[] visited = new boolean[n + 1];
        PriorityQueue<Node> q = new PriorityQueue<>();

        Arrays.fill(dist, INT_MAX);
        q.add(new Node(start, 0));
        visited[start] = true;

        dist[start] = 0;

        while (!q.isEmpty()) {
            Node p = q.poll();

            int curIdx = p.idx;
            visited[curIdx] = true;

            for (int i = 0; i < list.get(curIdx).size(); i++) {
                int next = list.get(curIdx).get(i).end;
                int w = list.get(curIdx).get(i).w;

                if (!visited[next] && dist[next] > dist[curIdx] + w) {
                    dist[next] = dist[curIdx] + w;
                    q.add(new Node(next, dist[next]));
                }
            }

        }

        return dist;
    }

    static class Node implements Comparable<Node> {

        int idx;
        int distance;

        Node(int idx, int distance) {
            this.idx = idx;
            this.distance = distance;
        }

        @Override
        public int compareTo(Node o) {
            return this.distance - o.distance;
        }
    }

    static class Pair {

        int start;
        int end;
        int w;

        Pair(int start, int end, int w) {
            this.start = start;
            this.end = end;
            this.w = w;
        }
    }
}
