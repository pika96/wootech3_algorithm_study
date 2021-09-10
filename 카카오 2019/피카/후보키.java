package Algorithm.algorithm.Y5주차;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class 후보키 {

//    static String[][] r = {
//            {"a", "1", "aaa", "c", "ng"},
//            {"b", "1", "bbb", "c", "g"},
//            {"c", "1", "aaa", "d", "ng"},
//            {"d", "2", "bbb", "d", "ng"}
//    };

//    static String[][] r = {
//            {"a", "1", "4"},
//            {"2", "1", "5"},
//            {"a", "2", "4"}
//    };

    static String[][] r = {
            {"100", "ryan", "music", "2"},
            {"200", "apeach", "math", "2"},
            {"300", "tube", "computer", "3"},
            {"400", "con", "computer", "4"},
            {"500", "muzi", "music", "3"},
            {"600", "apeach", "music", "2"}
    };

    static ArrayList<String> position = new ArrayList<>();
    static int answer = 0;

    public static void main(String[] args) {
        System.out.println(solution(r));
    }

    public static int solution(String[][] relation) {

        for (int i = 1; i <= relation[0].length; i++) {
            DFS(1, relation[0].length, i, "");
        }
        return answer;
    }

    public static void DFS(int depth, int maxDepth, int length, String cur) {
        if (depth == maxDepth + 1 || cur.length() == length) {
            if (cur.length() == length) {
                checkDuplicated(cur);
            }
            return;
        }

        DFS(depth + 1, maxDepth, length, cur);
        DFS(depth + 1, maxDepth, length, cur + depth);
    }

    private static void checkDuplicated(String cur) {

        // 최소키 검증 123 13 일 경우 contains로는 안된다!
        // list containsAll로 하면 하나씩 검증하기 때문에 (1,3) -> (1,2,3) 된다!
        for (String s : position) {
            if (minkey(cur, s)) {
                return;
            }
        }

        Set<String> set = new HashSet<>();

        String[] indexs = cur.split("");

        for (int i = 0; i < r.length; i++) {
            StringBuffer sb = new StringBuffer();

            for (int j = 0; j < indexs.length; j++) {
                int index = Integer.parseInt(indexs[j]);
                sb.append(r[i][index - 1]);
            }

            set.add(String.valueOf(sb));
        }

        if (set.size() == r.length) {
            position.add(cur);
            answer++;
        }
    }

    private static boolean minkey(String cur, String position) {
        String[] split = position.split("");

        for (int i = 0; i < split.length; i++) {
            if(!cur.contains(split[i])) {
                return false;
            }
        }

        return true;
    }
}
