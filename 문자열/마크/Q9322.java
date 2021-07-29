package com.binghe.acmicpc.string;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Q9322 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int iter = Integer.parseInt(br.readLine());

        for (int i = 0; i < iter; i++) {
            int n = Integer.parseInt(br.readLine());
            String[] result = new String[n];

            String[] first = br.readLine().split(" ");
            String[] second = br.readLine().split(" ");

            Map<String, Integer> firstOrder = new HashMap<>();

            for (int order = 0; order < n; order++) {
                firstOrder.put(first[order], order+1);
            }

            int[] log = new int[n];
            for (int order = 0; order < n; order++) {
                log[order] = firstOrder.get(second[order]);
            }

            String[] secret = br.readLine().split(" ");

            for (int j = 0; j < n; j++) {
                result[log[j]-1] = secret[j];
            }

            Arrays.stream(result)
                .forEach(word -> System.out.print(word + " "));
            System.out.println();
        }
    }
}
