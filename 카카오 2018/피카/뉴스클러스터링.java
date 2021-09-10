package Algorithm.algorithm.Y6주차;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class 뉴스클러스터링 {

    private static String str1 = "FRANCE";
    private static String str2 = "french";

    public static void main(String[] args) {
        System.out.println(solution(str1, str2));
    }

    public static int solution(String str1, String str2) {
        int answer = 0;

        str1 = str1.toLowerCase();
        str2 = str2.toLowerCase();

        Pattern p = Pattern.compile("[^a-z]");


        ArrayList<String> group1 = new ArrayList<>();
        ArrayList<String> group2 = new ArrayList<>();

        for (int i = 0; i < str1.length() - 1; i++) {
            StringBuilder sb = new StringBuilder();
            StringBuilder append = sb.append(str1.charAt(i))
                    .append(str1.charAt(i + 1));

            Matcher m1 = p.matcher(sb);

            if(m1.find()) {
                continue;
            }

            group1.add(append.toString());
        }

        for (int i = 0; i < str2.length() - 1; i++) {
            StringBuilder sb = new StringBuilder();
            StringBuilder append = sb.append(str2.charAt(i))
                    .append(str2.charAt(i + 1));

            Matcher m2 = p.matcher(sb);

            if(m2.find()) {
                continue;
            }

            group2.add(append.toString());
        }

        int group1Size = group1.size();
        int group2Size = group2.size();

        if(group1Size == 0 && group2Size == 0) {
            answer = 65536;
            return answer;
        }

        double inter = 0;

        for (int i = 0; i < group1.size(); i++) {
            String set1 = group1.get(i);

            for (int j = 0; j < group2.size(); j++) {
                String set2 = group2.get(j);

                if(set1.equals(set2)) {
                    group2.remove(set2);
                    inter++;
                    break;
                }
            }
        }

        double union = group1Size + group2Size - inter;

        answer = (int) Math.floor(inter / union * 65536);

        return answer;
    }
}
