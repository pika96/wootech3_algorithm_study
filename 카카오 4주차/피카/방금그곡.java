package Algorithm.algorithm.Y8주차;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class 방금그곡 {

    static String m = "CC#BCC#BCC#BCC#B";
    static String[] musicinfos = {"12:00,12:14,HELLO,C#DEFGAB", "13:00,13:05,WORLD,ABCDEF"};

    public static void main(String[] args) {
        System.out.println(solution(m, musicinfos));
    }

    public static String solution(String m, String[] musicinfos) {
        String answer = "";

        ArrayList<Music> musics = new ArrayList<>();

        StringBuilder sbm = new StringBuilder();
        for (int j = 0; j < m.length(); j++) {
            if (m.charAt(j) == '#') {
                continue;
            }

            if (j != m.length() - 1) {
                if (m.charAt(j + 1) == '#') {
                    sbm.append(String.valueOf(m.charAt(j)).toLowerCase());
                } else {
                    sbm.append(m.charAt(j));
                }
            } else {
                sbm.append(m.charAt(j));
            }
        }

        m = sbm.toString();

        for (int i = 0; i < musicinfos.length; i++) {
            String[] parse = musicinfos[i].split(",");

            int startTime = timeToInt(parse[0]);
            int endTime = timeToInt(parse[1]);
            String melodys = parse[3];
            StringBuilder sb = new StringBuilder();

            for (int j = 0; j < melodys.length(); j++) {
                if (melodys.charAt(j) == '#') {
                    continue;
                }

                if (j != melodys.length() - 1) {
                    if (melodys.charAt(j + 1) == '#') {
                        sb.append(String.valueOf(melodys.charAt(j)).toLowerCase());
                    } else {
                        sb.append(melodys.charAt(j));
                    }
                } else {
                    sb.append(melodys.charAt(j));
                }
            }

            musics.add(new Music(endTime - startTime, parse[2], sb.toString(), i));
        }

        Queue<Music> q = new LinkedList<>();

        for (int i = 0; i < musics.size(); i++) {
            Music music = musics.get(i);
            int time = music.time;
            String melody = music.melody;

            StringBuilder sb = new StringBuilder();

            int count = time / melody.length();
            for (int j = 0; j < count; j++) {
                sb.append(melody);
            }

            int remain = time % melody.length();

            for (int j = 0; j < remain; j++) {
                sb.append(music.melody.charAt(j));
            }

            if (sb.toString().contains(m)) {
                q.add(music);
            }
        }

        if (q.size() == 1) {
            answer = q.poll().title;
        } else if (q.size() == 0) {
            answer = "(None)";
        } else {

            int time = 0;
            int curIdx = 0;

            while (!q.isEmpty()) {
                Music music = q.poll();

                if (music.time > time) {
                    time = music.time;
                    curIdx = music.idx;
                    answer = music.title;
                } else if (music.time == time) {
                    if (curIdx > music.idx) {
                        time = music.time;
                        curIdx = music.idx;
                        answer = music.title;
                    }
                }

            }
        }

        return answer;
    }

    static class Music {

        int time;
        String title;
        String melody;
        int idx;

        public Music(int time, String title, String melody, int idx) {
            this.time = time;
            this.title = title;
            this.melody = melody;
            this.idx = idx;
        }
    }

    private static int timeToInt(String time) {
        String[] split = time.split(":");

        int result = 0;

        result += Integer.parseInt(split[0]) * 60;
        result += Integer.parseInt(split[1]);

        return result;
    }
}
