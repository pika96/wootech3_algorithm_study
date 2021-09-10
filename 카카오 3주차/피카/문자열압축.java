package Algorithm.algorithm.Y7주차;

public class 문자열압축 {

    static String s = "a";

    public static void main(String[] args) {
        System.out.println(solution(s));
    }

    public static int solution(String s) {
        int answer = Integer.MAX_VALUE;

        for (int l = 1; l <= s.length() / 2; l++) {

            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < s.length(); i++) {

                if(i + l > s.length()) {
                    String last = s.substring(i , s.length());
                    sb.append(last);
                    break;
                }
                String cur = s.substring(i, i + l);
                int count = comp(cur, i, l, s);

                if(count == 1) {
                    sb.append(cur);
                } else {
                    sb.append(count + cur);
                }

                i = i + l * count - 1;
            }

            answer = Integer.min(answer, sb.length());
        }

        // 테스트 케이스 5번 for문에 아예 들어가지 않음
        if(s.length() == 1) {
            answer = 1;
        }

        return answer;
    }

    private static int comp(String cur, int start, int l, String word) {
        int count = 1;

        while(true) {

            int idx = start + l * count;

            if(idx >= word.length()) {
                break;
            }

            String next = "";
            if(idx + l > word.length()) {
                next = word.substring(idx, word.length());
            } else {
                next = word.substring(idx, idx + l);
            }

            if(!cur.equals(next)) {
                break;
            }

            count++;
        }

        return count;
    }
}
