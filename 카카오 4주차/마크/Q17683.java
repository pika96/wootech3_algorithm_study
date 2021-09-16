package com.binghe.programmers.kakao_2018;

public class Q17683 {

    private static final String NONE_ANSWER = "(None)";

    public String solution(String m, String[] musicinfos) {
        String answer = NONE_ANSWER;
        m = convert(m);

        // 최장재생시간
        int max = 0;
        for (int i = 0; i < musicinfos.length; i++) {
            String[] str = musicinfos[i].split(",");
            String title = str[2];
            String music = convert(str[3]);
            String[] start = str[0].split(":");
            String[] end = str[1].split(":");
            int hour = (Integer.parseInt(end[0]) - Integer.parseInt(start[0])) * 60;
            int minute = Integer.parseInt(end[1]) - Integer.parseInt(start[1]);
            int time = hour + minute;
            StringBuilder sb = new StringBuilder();

            // 전체멜로디 만들기
            for (int j = 0; j < time; j++) {
                sb.append(music.charAt(j % music.length()));
            }

            // 방금그곡 전체멜로디에 존재여부
            if (sb.toString().contains(m)) {
                // 가장 긴 멜로디인지
                if (max < sb.toString().length()) {
                    max = sb.toString().length();
                    answer = title;
                }
            }
        }

        System.out.println(answer);
        return answer;
    }

    // 두글자(음표#) 한글자로 치환
    private String convert(String m) {
        m = m.replaceAll("A#", "a");
        m = m.replaceAll("C#", "c");
        m = m.replaceAll("D#", "d");
        m = m.replaceAll("F#", "f");
        m = m.replaceAll("G#", "g");
        return m;
    }
}
