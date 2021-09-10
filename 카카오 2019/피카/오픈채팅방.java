package Algorithm.algorithm.Y5주차;

import java.util.ArrayList;
import java.util.HashMap;

public class 오픈채팅방 {

    static String[] record = {"Enter uid1234 Muzi", "Enter uid4567 Prodo", "Leave uid1234",
            "Enter uid1234 Prodo", "Change uid4567 Ryan"};

    public static void main(String[] args) {
        String[] answer = solution(record);

        for (String s : answer) {
            System.out.println("s = " + s);
        }

    }

    public static String[] solution(String[] record) {
        String[] answer = {};

        String[][] parseRecord = new String[record.length][3];

        for (int i = 0; i < record.length; i++) {
            String[] parse = record[i].split(" ");

            if (parse[0].equals("Leave")) {
                parseRecord[i][0] = parse[0];
                parseRecord[i][1] = parse[1];
                parseRecord[i][2] = "";
            } else {
                parseRecord[i][0] = parse[0];
                parseRecord[i][1] = parse[1];
                parseRecord[i][2] = parse[2];
            }
        }

        HashMap<String, String> hashMap = new HashMap<>();

        for (int i = record.length - 1; i >= 0; i--) {
            if (!parseRecord[i][0].equals("Leave")) {
                if (!hashMap.containsKey(parseRecord[i][1])) {
                    hashMap.put(parseRecord[i][1], parseRecord[i][2]);
                }
            }
        }

        ArrayList<String> answerList = new ArrayList<>();

        for (int i = 0; i < record.length; i++) {
            if (parseRecord[i][0].equals("Enter")) {
                String lastName = hashMap.get(parseRecord[i][1]);
                answerList.add(lastName + "님이 들어왔습니다.");
            } else if (parseRecord[i][0].equals("Leave")) {
                String lastName = hashMap.get(parseRecord[i][1]);
                answerList.add(lastName + "님이 나갔습니다.");
            }
        }

        answer = new String[answerList.size()];

        for (int i = 0; i < answerList.size(); i++) {
            answer[i] = answerList.get(i);
        }

        return answer;
    }
}
