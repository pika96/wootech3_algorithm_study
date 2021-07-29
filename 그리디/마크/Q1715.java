package com.binghe.acmicpc.string;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class Q1715 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        PriorityQueue<Long> queue = new PriorityQueue<>();

        for (int i = 0; i < N; i++) {
            long card = Long.parseLong(br.readLine());
            queue.add(card);
        }

        long result = 0L;
        while (true) {
            if (queue.size() == 1) {
                break;
            }
            long temp = queue.poll() + queue.poll();
            result += temp;

            if (queue.isEmpty()) {
                break;
            }
            queue.add(temp);
        }
        System.out.println(result);
    }
}
