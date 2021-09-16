package com.binghe.programmers.kakao_2021;

import java.util.ArrayList;

public class Q60057 {

    public int solution(String s) {
        int answer = Integer.MAX_VALUE;
        int len = s.length();

        if(len == 1)
            return 1;

        String tmp;
        int end;
        ArrayList<String> split;
        // i개씩 자르기 ( 꼭 딱 맞춰서 잘라져야 하므로 len/2까지만 )
        for(int i = 1; i <= len/2; i++) {
            split = new ArrayList<>();
            for(int start = 0; start <  len; start+=i) {
                if(start + i > len)
                    end = len;
                else
                    end = start+i;
                tmp = s.substring(start, end);
                split.add(tmp);
            }
            int cnt = 0;
            int sum = 0;
            for(int j = 0; j < split.size(); j++) {
                cnt++;
                if(j+1 >= split.size() || !split.get(j).equals(split.get(j+1))) {
                    if(cnt == 1)
                        sum += split.get(j).length();
                    else
                        sum += (int)Math.log10(cnt) + 1 + split.get(j).length();
                    cnt = 0;
                }
            }
            if(answer > sum)
                answer = sum;
//            StringBuilder res = new StringBuilder();
//            for(int j = 0; j < split.size(); j++) {
//                cnt++;
//                if(j+1 >= split.size() || !split.get(j).equals(split.get(j+1))) {
//                    if(cnt == 1)
//                        res.append(split.get(j));
//                    else
//                        res.append(cnt+""+split.get(j));
//                    cnt = 0;
//                }
//            }
//            System.out.println(res.toString());
        }
        return answer;
    }
}
