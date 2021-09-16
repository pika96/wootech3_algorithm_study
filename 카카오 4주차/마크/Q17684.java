package com.binghe.programmers.kakao_2018;

import java.util.*;

public class Q17684 {

    public ArrayList<Integer> solution(String msg) {
        int[] answer = {};
        ArrayList<Integer> ans = new ArrayList<Integer>();
        int ind = 1;

        HashMap<String, Integer> hs = new HashMap<String,Integer>();

        for(char i = 'A'; i <='Z'; i++){
            hs.put(i + "", ind++);
        }

        int size = msg.length();

        for(int i = 0; i < size; i++){
            int a = 1;

            while (i + a <= size && hs.containsKey(msg.substring(i, i + a))){
                a++;
            }

            if (i + a > size){
                ans.add(hs.get(msg.substring(i)));
                break;
            }

            ans.add(hs.get(msg.substring(i,i+a-1)));
            hs.put(msg.substring(i, i + a), ind++);

            if (a > 1) {
                i += a - 2;
            }
        }
        return ans;
    }
}
