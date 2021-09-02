package com.binghe.acmicpc.graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

public class Q1238 {
    private static final int INF = Integer.MAX_VALUE;

    private static int N, M, X;
    private static List<List<Node>> list; // 문제 입력 그대로
    private static List<List<Node>> reverseList; // 문제 입력 반대로

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] input = br.readLine().split(" ");
        N = Integer.parseInt(input[0]);
        M = Integer.parseInt(input[1]);
        X = Integer.parseInt(input[2]);

        // 입력값 받는 그래프 초기화
        list = new ArrayList<>();
        reverseList = new ArrayList<>();
        for (int i = 0; i <= N; i++) {
            list.add(new ArrayList<>());
            reverseList.add(new ArrayList<>());
        }

        for (int i = 1; i <= M; i++) {
            input = br.readLine().split(" ");
            int from = Integer.parseInt(input[0]);
            int to = Integer.parseInt(input[1]);
            int weight = Integer.parseInt(input[2]);

            list.get(from).add(new Node(to, weight));
            reverseList.get(to).add(new Node(from, weight));
        }

        int[] toHome = dijkstra(list, X);
        int[] toParty = dijkstra(reverseList, X);

        int result = 0;
        for (int i = 1; i <= N; i++) {
            result = Math.max(result, toHome[i] + toParty[i]);
        }

        System.out.println(result);
    }

    private static int[] dijkstra(List<List<Node>> graph, int start) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(start, 0));

        boolean[] visited = new boolean[N+1];
        int[] dist = new int[N+1];
        Arrays.fill(dist, INF);
        dist[start] = 0;

        while (!pq.isEmpty()) {
            Node now = pq.poll();
            int idx = now.end;

            if (!visited[idx]) {
                visited[idx] = true;

                for (Node n : graph.get(idx)) {
                    if (!visited[n.end] && dist[n.end] > dist[idx] + n.weight) {
                        dist[n.end] = dist[idx] + n.weight;
                        pq.add(new Node(n.end, dist[n.end]));
                    }
                }
            }
        }
        return dist;
    }

    static class Node implements Comparable<Node> {
        int end;
        int weight;

        public Node(int end, int weight) {
            this.end = end;
            this.weight = weight;
        }

        @Override
        public int compareTo(Node o) {
            return this.weight - o.weight;
        }
    }
}
