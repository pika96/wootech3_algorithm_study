package kakao2018;

import java.util.ArrayList;
import java.util.List;

public class 다트게임 {

    public static void main(String[] args) {

        String input = "1D#2S*3S";
        System.out.println(solution(input));

    }

    private static int solution(String dartResult) {
        List<Long> nums = new ArrayList<>();

        int index = -1;
        for (int i = 0; i < dartResult.length(); i++) {
            char current = dartResult.charAt(i);

            StringBuilder sb = new StringBuilder();
            sb.append(current);

            if (Character.isDigit(current)) {
                while (Character.isDigit(dartResult.charAt(i + 1))) {
                    sb.append(dartResult.charAt(i + 1));
                    i++;
                }

                index++;
                nums.add(Long.parseLong(sb.toString()));
                continue;
            }

            if (current == 'S' || current == 'D' || current == 'T') {
                long temp = nums.get(index);
                if (current == 'D') {
                    nums.remove(index);
                    nums.add((long) Math.pow(temp, 2));
                } else if (current == 'T') {
                    nums.remove(index);
                    nums.add((long) Math.pow(temp, 3));
                }

                continue;
            }

            if (current == '*' || current == '#') {
                long temp = nums.get(index);
                if (current == '*') {
                    if ((index - 1) >= 0) {
                        long prev = nums.get(index - 1);
                        nums.remove(index - 1);
                        nums.add(index - 1, prev * 2);
                    }

                    nums.remove(index);
                    nums.add(temp * 2);
                } else if (current == '#') {
                    nums.remove(index);
                    nums.add(temp * (-1));
                }
            }
        }

        int ans = 0;
        for (Long num: nums) {
            ans += num;
        }

        return ans;
    }
}
