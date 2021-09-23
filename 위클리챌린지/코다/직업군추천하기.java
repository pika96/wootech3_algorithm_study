package weeklychallenge;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class 직업군추천하기 {

    public static void main(String[] args) {

        String[] table = {"SI JAVA JAVASCRIPT SQL PYTHON C#", "CONTENTS JAVASCRIPT JAVA PYTHON SQL C++", "HARDWARE C C++ PYTHON JAVA JAVASCRIPT", "PORTAL JAVA JAVASCRIPT PYTHON KOTLIN PHP", "GAME C++ C# JAVASCRIPT C JAVA"};
        String[] languages = {"PYTHON", "C++", "SQL"};
        int[] pref = {7, 5, 5};

        System.out.println(solution(table, languages, pref));

    }

    public static String solution(String[] table, String[] languages, int[] preference) {
        Map<String, Map<String, Integer>> jobAndScore = new HashMap<>();

        for (int i = 0; i < table.length; i++) {
            String[] split = table[i].split(" ");
            Map<String, Integer> score = new HashMap<>();
            for (int j = 1; j < split.length; j++) {
                score.put(split[j], split.length - j);
            }

            jobAndScore.put(split[0], score);
        }

        Map<String, Integer> langAndPrefer = new HashMap<>();
        for (int i = 0; i < languages.length; i++) {
            langAndPrefer.put(languages[i], preference[i]);
        }

        Map<String, Integer> totalScore = new HashMap<>();

        for (String currentJob : jobAndScore.keySet()) {
            int sum = Arrays.stream(languages)
                .mapToInt(lang -> langAndPrefer.get(lang) * jobAndScore.get(currentJob)
                    .getOrDefault(lang, 0))
                .sum();

            totalScore.put(currentJob, sum);
        }

        int max = totalScore.values().stream()
            .mapToInt(score -> score)
            .max()
            .getAsInt();

        List<String> candidates = totalScore.keySet().stream()
            .filter(job -> totalScore.get(job) == max)
            .collect(Collectors.toList());

        Collections.sort(candidates);

        return candidates.get(0);
    }
}
