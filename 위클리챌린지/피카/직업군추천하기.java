package Algorithm.algorithm.Y9주차;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class 직업군추천하기 {

    static String[] table = {"SI JAVA JAVASCRIPT SQL PYTHON C#",
            "CONTENTS JAVASCRIPT JAVA PYTHON SQL C++", "HARDWARE C C++ PYTHON JAVA JAVASCRIPT",
            "PORTAL JAVA JAVASCRIPT PYTHON KOTLIN PHP", "GAME C++ C# JAVASCRIPT C JAVA"};
    static String[] languages = {"PYTHON", "C++", "SQL"};
    static int[] preference = {7, 5, 5};

    public static void main(String[] args) {
        System.out.println(solution(table, languages, preference));
    }

    public static String solution(String[] table, String[] languages, int[] preference) {
        String answer = "";

        Map<String, Integer> SI = new HashMap<>();
        Map<String, Integer> CONTENTS = new HashMap<>();
        Map<String, Integer> HARDWARE = new HashMap<>();
        Map<String, Integer> PORTAL = new HashMap<>();
        Map<String, Integer> GAME = new HashMap<>();

        String[][] split = new String[5][6];

        for (int i = 0; i < 5; i++) {
            split[i] = table[i].split(" ");
        }

        for (int i = 1; i < split[0].length; i++) {
            SI.put(split[0][i], 5 - i + 1);
        }

        for (int i = 1; i < split[1].length; i++) {
            CONTENTS.put(split[1][i], 5 - i + 1);
        }

        for (int i = 1; i < split[2].length; i++) {
            HARDWARE.put(split[2][i], 5 - i + 1);
        }

        for (int i = 1; i < split[3].length; i++) {
            PORTAL.put(split[3][i], 5 - i + 1);
        }

        for (int i = 1; i < split[4].length; i++) {
            GAME.put(split[4][i], 5 - i + 1);
        }

        int SIScore = 0;
        int CONTENTSScore = 0;
        int HARDWAREScore = 0;
        int PORTALScore = 0;
        int GAMEScore = 0;

        for (int i = 0; i < languages.length; i++) {
            SIScore += SI.getOrDefault(languages[i], 0) * preference[i];
        }

        for (int i = 0; i < languages.length; i++) {
            CONTENTSScore += CONTENTS.getOrDefault(languages[i], 0) * preference[i];
        }

        for (int i = 0; i < languages.length; i++) {
            HARDWAREScore += HARDWARE.getOrDefault(languages[i], 0) * preference[i];
        }

        for (int i = 0; i < languages.length; i++) {
            PORTALScore += PORTAL.getOrDefault(languages[i], 0) * preference[i];
        }

        for (int i = 0; i < languages.length; i++) {
            GAMEScore += GAME.getOrDefault(languages[i], 0) * preference[i];
        }

        int max = Math.max(SIScore, Math.max(CONTENTSScore, Math.max(HARDWAREScore, Math.max(PORTALScore, GAMEScore))));

        ArrayList<String> job = new ArrayList<>();

        if(CONTENTSScore == max) {
            job.add("CONTENTS");
        }

        if(GAMEScore == max) {
            job.add("GAME");
        }

        if(HARDWAREScore == max) {
            job.add("HARDWARE");
        }

        if(PORTALScore == max) {
            job.add("PORTAL");
        }

        if(SIScore == max) {
            job.add("SI");
        }

        answer = job.get(0);

        return answer;
    }
}
