package kakao2020;

public class 문자열압축 {

    public static void main(String[] args) {

        String input = "aabbaccc";
        int answer = solution(input);

        System.out.println(answer);
    }

    public static int solution(String s) {

        int min = 1001;
        if (s.length() == 1) {
            return 1;
        }

        for (int i = 1 ; i <= (s.length() / 2); i++) {
            String current = "";
            String compare = s.substring(0, i);
            int count = 0;
            int lengthString = 0;

            for (int j = 1; j <= (s.length() / i); j++) {
                current = s.substring((i * j) - i , i * j);
                if (compare.equals(current)) {
                    count++;
                } else {
                    if (count == 1) {
                        lengthString += compare.length();
                    } else {
                        lengthString += compare.length() + String.valueOf(count).length();
                    }
                    compare = current;
                    count = 1;
                }
            }

            if (count == 1) {
                lengthString += compare.length();
            } else {
                lengthString += compare.length() + String.valueOf(count).length();
            }

            lengthString += s.length() % i;

            if (lengthString < min) {
                min = lengthString;
            }
        }

        return min;
    }
}
