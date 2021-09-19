package kakao2018;

import static java.util.stream.Collectors.toList;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class 방금그곡 {

    public static void main(String[] args) {
//        String m = "ABCDEFG";
        String m = "CC#BCC#BCC#BCC#B";
        String[] infos = {"03:00,03:30,FOO,CC#B", "04:00,04:08,BAR,CC#BCC#BCC#B"};
//        String[] infos = {"12:00,12:14,HELLO,CDEFGAB", "13:00,13:05,WORLD,ABCDEF"};

        System.out.println(solution(m, infos));
    }

    private static String solution(String m, String[] musicinfos) {
        Map<String, Integer> mWithTime = new LinkedHashMap<>();

        String[][] info = new String[musicinfos.length][4];
        for (int i = 0; i < musicinfos.length; i++) {
            info[i] = musicinfos[i].split(",");
        }

        Arrays.stream(info)
            .forEach(
                detail -> mWithTime.put(detail[2], timeToInt(detail[1]) - timeToInt(detail[0])));

        Arrays.stream(info)
            .forEach(detail -> {
                detail[3] = detail[3].replace("C#", "M");
                detail[3] = detail[3].replace("D#", "N");
                detail[3] = detail[3].replace("F#", "X");
                detail[3] = detail[3].replace("G#", "Y");
                detail[3] = detail[3].replace("A#", "Z");
            });

        Arrays.stream(info)
            .forEach(detail -> {
                String totalMusic = detail[3];
                while (totalMusic.length() < mWithTime.get(detail[2])) {
                    totalMusic = totalMusic + detail[3];
                }

                detail[3] = totalMusic.substring(0, mWithTime.get(detail[2]));
            });

        m = m.replace("C#", "M");
        m = m.replace("D#", "N");
        m = m.replace("F#", "X");
        m = m.replace("G#", "Y");
        m = m.replace("A#", "Z");

        String tofind = m;

        List<String> candidates = Arrays.stream(info)
            .filter(detail -> detail[3].contains(tofind))
            .map(detail -> detail[2])
            .collect(toList());

        if (candidates.isEmpty()) {
            return "(None)";
        }

        int max = candidates.stream()
            .mapToInt(candidate -> mWithTime.get(candidate))
            .max()
            .getAsInt();

        candidates = candidates.stream()
            .filter(candidate -> mWithTime.get(candidate) == max)
            .collect(toList());

        return candidates.get(0);
    }

    private static int timeToInt(String time) {
        String[] split = time.split(":");

        int result = 0;

        result += Integer.parseInt(split[0]) * 60;
        result += Integer.parseInt(split[1]);

        return result;
    }

}
