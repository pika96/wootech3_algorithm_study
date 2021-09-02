package Algorithm.algorithm.Y6주차;

import java.util.ArrayList;
import java.util.Collections;

public class 셔틀버스 {

    static int n = 2;
    static int t = 1;
    static int m = 2;
    static String[] timetable = {"09:00", "09:00", "09:00", "09:00"};

    public static void main(String[] args) {
        System.out.println(solution(n, t, m, timetable));
    }

    public static String solution(int n, int t, int m, String[] timetable) {
        String answer = "";

        ArrayList<Integer> times = new ArrayList<>();
        for (int i = 0; i < timetable.length; i++) {
            times.add(timeToInt(timetable[i]));
        }

        Collections.sort(times);

        ArrayList<Bus> bus = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            bus.add(new Bus(540 + i * t));
        }

        for (int i = 0; i < bus.size(); i++) {
            int busTime = bus.get(i).time;

            ArrayList<Integer> takeCrew = new ArrayList<>();
            for (int j = 0; j < times.size(); j++) {
                if(bus.get(i).crew.size() >= m) {
                    break;
                }

                if(busTime >= times.get(j)) {
                    bus.get(i).crew.add(times.get(j));
                    takeCrew.add(times.get(j));
                }
            }

            for(int j=0;j<takeCrew.size();j++){
                times.remove(takeCrew.get(j));
            }
        }

        Bus lastBus = bus.get(bus.size() - 1);

        int con = 0;

        if(lastBus.crew.size() == m) {
            con = lastBus.crew.get(m - 1) -1;
        } else {
            con = lastBus.time;
        }

        answer = intToTime(con);

        return answer;
    }

    static class Bus {

        int time;
        ArrayList<Integer> crew = new ArrayList<>();

        Bus(int time) {
            this.time = time;
        }
    }

    private static int timeToInt(String time) {
        String[] split = time.split(":");

        int result = 0;

        result += Integer.parseInt(split[0]) * 60;
        result += Integer.parseInt(split[1]);

        return result;
    }

    private static String intToTime(int time) {
        StringBuilder sb = new StringBuilder();

        int hour = time / 60;
        int min = time % 60;

        if (hour < 10) {
            sb.append(0)
                    .append(hour);
        } else {
            sb.append(hour);
        }

        sb.append(":");

        if (min < 10) {
            sb.append(0)
                    .append(min);
        } else {
            sb.append(min);
        }

        return sb.toString();
    }
}
