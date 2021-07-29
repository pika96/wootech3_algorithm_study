package com.binghe.acmicpc.string;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Q1543 {

    private static int search(String document, String query) {
        int count = 0;
        int currentIdx = 0;
        int documentLength = document.length();
        int queryLength = query.length();
        while (currentIdx <= documentLength - queryLength) {
            String checkWord = document.substring(currentIdx, currentIdx + queryLength);
            if (checkWord.equals(query)) {
                currentIdx += queryLength;
                count += 1;
                continue;
            }
            currentIdx += 1;
        }

        return count;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String document = br.readLine();
        String query = br.readLine();

        System.out.println(search(document, query));
    }
}
