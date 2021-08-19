package Algorithm.algorithm.Y4주차;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;

public class M1504 {

    static ArrayList<ArrayList<Pair>> list;
    static int N;
    static int E;
    static int INF = 99999999;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] input = br.readLine().split(" ");

        N = Integer.parseInt(input[0]);
        E = Integer.parseInt(input[1]);

        list = new ArrayList<>();

        for (int i = 0; i <= N; i++) {
            list.add(new ArrayList<>());
        }

        for (int i = 0; i < E; i++) {
            String[] node = br.readLine().split(" ");
            int a = Integer.parseInt(node[0]);
            int b = Integer.parseInt(node[1]);
            int c = Integer.parseInt(node[2]);

            list.get(a).add(new Pair(a, b, c));
            list.get(b).add(new Pair(b, a, c));
        }

        String[] node = br.readLine().split(" ");

        int v1 = Integer.parseInt(node[0]);
        int v2 = Integer.parseInt(node[1]);

        int[] defaultDist = dijkstra(1);
        int[] v1Dist = dijkstra(v1);
        int[] v2Dist = dijkstra(v2);

        // 1 -> v1 -> v2 -> N
        int firstWay = defaultDist[v1] + v1Dist[v2] + v2Dist[N];

        // 1 -> v2 -> v1 -> N
        int secondWay = defaultDist[v2] + v2Dist[v1] + v1Dist[N];

        int ans = 0;
        if(firstWay >= INF && secondWay >= INF) {
            ans = -1;
        } else {
            ans = Math.min(firstWay, secondWay);
        }

        System.out.println(ans);
    }

    private static int[] dijkstra(int start) {
        int[] dist = new int[N + 1];
        boolean[] visited = new boolean[N + 1];
        PriorityQueue<Node> q = new PriorityQueue<>();

        Arrays.fill(dist, INF);
        dist[start] = 0;

        q.add(new Node(start, dist[start]));

        while (!q.isEmpty()) {
            Node n = q.poll();

            int curIndex = n.index;
            visited[curIndex] = true;

            for (int i = 0; i < list.get(curIndex).size(); i++) {
                int nextIndex = list.get(curIndex).get(i).v;
                int weight = list.get(curIndex).get(i).w;

                if(!visited[nextIndex] && dist[nextIndex] > dist[curIndex] + weight) {
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
        public int compareTo(Node o) {
            return this.distance - o.distance;
        }
    }

    public static class Pair {

        public int u, v, w;

        public Pair(int u, int v, int w) {
            this.u = u;
            this.v = v;
            this.w = w;
        }
    }
}
