package com.binghe.programmers.kakao_2018;

public class Q17682 {

    public int solution(String dartResult) {
        int[] result = new int[3];
        int idx = -1;

        for(int i = 0; i < dartResult.length(); i++){
            char ch = dartResult.charAt(i);
            if(ch >= '0' && ch <= '9'){
                if(ch == '0' && i >= 1 && dartResult.charAt(i-1) == '1'){
                    result[idx] = 10;
                    continue;
                }
                result[++idx] = ch - '0';
            } else {
                switch (ch){
                    case 'D':
                        result[idx] = (int) Math.pow(result[idx], 2);
                        break;
                    case 'T':
                        result[idx] = (int) Math.pow(result[idx], 3);
                        break;
                    case '*':
                        if(idx == 0){
                            result[idx] *= 2;
                        } else {
                            result[idx] *=2;
                            result[idx-1] *= 2;
                        }
                        break;
                    case '#':
                        result[idx] *= -1;
                }
            }
        }

        int answer = 0;
        for(int i : result)
            answer += i;

        return answer;
    }
}
