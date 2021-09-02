package kakao2018;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class NewsClustering {

    public static void main(String[] args) {
        String str1 = "aabbc";
        String str2 = "abbcd";

        int ans = solution(str1, str2);

        System.out.println(ans);
    }

    public static int solution(String str1, String str2) {
        int times = 65536;
        List<String> groupA = new ArrayList<>();
        List<String> groupB = new ArrayList<>();

        for (int i = 0; i < str1.length() - 1; i++) {
            String temp = str1.substring(i, i + 2);
            if (Pattern.matches("^[a-zA-Z]*$", temp) && temp.trim().length() == 2) {
                groupA.add(temp.toLowerCase());
            }
        }

        for (int i = 0; i < str2.length() - 1; i++) {
            String temp = str2.substring(i, i + 2);
            if (Pattern.matches("^[a-zA-Z]*$", temp) && temp.trim().length() == 2) {
                groupB.add(temp.toLowerCase());
            }
        }

        int[] table = new int[groupA.size()];

        for(int i = 0; i < groupB.size(); i++) {
            for (int j = 0; j < groupA.size(); j++) {
                if (groupB.get(i).equals(groupA.get(j)) && table[j] == 0) {
                    table[j] = 1;
                    break;
                }
            }
        }

        int count = 0;
        for (int same: table) {
            if (same == 1) {
                count++;
            }
        }

        int union = groupA.size() + groupB.size() - count;
        Double similarity;

        if (count == 0 && union == 0) {
            similarity = 1.0;
        } else {
            similarity = Double.valueOf(count) / Double.valueOf(union);
        }

        return (int) (similarity * times);
    }
}
