package kakao2019;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class OpenChatting {
	public static void main(String[] args) {
        
        String[] record = {"Enter uid1234 Muzi", "Enter uid4567 Prodo","Leave uid1234","Enter uid1234 Prodo","Change uid4567 Ryan"};
        String[] ans = solution(record);

        for (int i = 0; i < ans.length; i++)
        System.out.println(ans[i]);
    }

    public static String[] solution(String[] record) {
        Map<String, String> nickName = new HashMap<>();

        for (int i = 0; i < record.length; i++) {
            String[] temp = record[i].split(" ");
            
            if (temp[0].equals("Leave")) {
                continue;
            }
            
            nickName.put(temp[1], temp[2]);
        }

        String enter = "님이 들어왔습니다.";
        String exit = "님이 나갔습니다.";

        List<String> ans = new ArrayList<>();

        for(int i = 0; i < record.length; i++) {
            String[] temp = record[i].split(" ");
            if (temp[0].equals("Enter")) {
                ans.add(nickName.get(temp[1]) + enter);
            } else if (temp[0].equals("Leave")) {
                ans.add(nickName.get(temp[1]) + exit);
            }
        }

        return ans.toArray(new String[0]);
    }
}
