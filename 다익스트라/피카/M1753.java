package Algorithm.algorithm.Y4주차;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;

public class M1753 {

    static ArrayList<ArrayList<Pair>> list;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] input = br.readLine().split(" ");

        int V = Integer.parseInt(input[0]);
        int E = Integer.parseInt(input[1]);
        int K = Integer.parseInt(br.readLine());

        list = new ArrayList<>();

        for (int i = 0; i <= V; i++) {
            list.add(new ArrayList<>());
        }

        for (int i = 0; i < E; i++) {
            String[] route = br.readLine().split(" ");
            int u = Integer.parseInt(route[0]);
            int v = Integer.parseInt(route[1]);
            int w = Integer.parseInt(route[2]);

            list.get(u).add(new Pair(u, v, w));
        }

        PriorityQueue<Node> q = new PriorityQueue<>();
        int[] dist = new int[V + 1];
        boolean[] visited = new boolean[V + 1];

        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[K] = 0;

        q.add(new Node(K, dist[K]));

        while (!q.isEmpty()) {
            Node n = q.poll();

            int start = n.index;
            visited[start] = true;

            for (int i = 0; i < list.get(start).size(); i++) {
                int next = list.get(start).get(i).v;
                int distance = list.get(start).get(i).weight;

                if (!visited[next] && dist[next] > dist[start] + distance) {
                    dist[next] = dist[start] + distance;
                    q.add(new Node(next, dist[next]));
                }
            }
        }

        for (int i = 1; i <= V; i++) {
            if(dist[i] == Integer.MAX_VALUE) {
                System.out.println("INF");
            } else {
                System.out.println(dist[i]);
            }
        }
    }

    public static class Node implements Comparable<Node> {

        int index, distance;

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

        public int u, v, weight;

        public Pair(int u, int v, int weight) {
            this.u = u;
            this.v = v;
            this.weight = weight;
        }
    }
}
