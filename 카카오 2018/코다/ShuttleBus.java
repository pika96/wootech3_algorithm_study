package kakao2018;

import java.awt.print.Pageable;
import java.util.Arrays;
import java.util.Comparator;

public class ShuttleBus {

    public static void main(String[] args) {

        int n = 1;
        int t = 1;
        int m = 5;
        String[] input = {"00:01", "00:01", "00:01", "00:01", "00:01"};

        String answer = solution(n, t, m, input);
        System.out.println(answer);
    }

    public static String solution(int n, int t, int m, String[] timetable) {
        int[][] bus = new int[n][2];
        bus[0][0] = 9;
        bus[0][1] = 0;

        for (int i = 1; i < n; i++) { //bus timetable init
            int hr = bus[i - 1][0];
            int min = bus[i - 1][1] + t;

            if (min > 59) {
                hr++;
                min -= 60;
            }

            bus[i][0] = hr;
            bus[i][1] = min;
        }

        int[][] crew = new int[timetable.length][2];
        for (int i = 0; i < timetable.length; i++) { //crew timetable init
            String[] hrAndMin = timetable[i].split(":");

            crew[i][0] = Integer.parseInt(hrAndMin[0]);
            crew[i][1] = Integer.parseInt(hrAndMin[1]);
        }

        Arrays.sort(crew, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if (o1[0] == o2[0]) {
                    return o1[1] - o2[1];
                }
                return o1[0] - o2[0];
            }
        });


        int[] busCapacity = new int[n];
        int crewIndex = 0;
        int cnt = 0;
        for (int i = 0; i < n; i++) {
            while (cnt < m && crewIndex < timetable.length) {

                if ((crew[crewIndex][0] < bus[i][0]) || ((crew[crewIndex][0] == bus[i][0]) && (crew[crewIndex][1] <= bus[i][1]))) {
                    cnt++;
                    crewIndex++;
                } else {
                    break;
                }
            }

            busCapacity[i] = cnt;
            cnt = 0;
        }

        int myHr;
        int myMin;

        if (crewIndex == timetable.length && busCapacity[n - 1] == m) {
            myHr = crew[crew.length - 1][0];
            myMin = crew[crew.length - 1][1] - 1;
        } else if (crewIndex < timetable.length && busCapacity[n - 1] == m) {
            myHr = crew[crewIndex - 1][0];
            myMin = crew[crewIndex - 1][1] - 1;
        } else {
            myHr = bus[n - 1][0];
            myMin = bus[n - 1][1];
        }

        if (myMin < 0) {
            myHr--;
            myMin = 60 + myMin;
        }

        String hour;
        String minuet;
        if (myHr < 10) {
            hour = "0" + myHr;
        } else {
            hour = Integer.toString(myHr);
        }

        if (myMin < 10) {
            minuet = "0" + myMin;
        } else {
            minuet = Integer.toString(myMin);
        }

        return hour + ":" + minuet;
    }
}
