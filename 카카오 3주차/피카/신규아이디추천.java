package Algorithm.algorithm.Y7주차;

public class 신규아이디추천 {

    static String new_id = "...!@BaT#*..y.abcdefghijklm";

    public static void main(String[] args) {
        System.out.println(solution(new_id));
    }

    public static String solution(String new_id) {
        String answer = "";

        // 1단계
        String step1 = new_id.toLowerCase();
        String step2 = step1.replaceAll("[^[a-z][0-9]-_.]", "");
        String step3 = step2.replaceAll("\\.+", ".");
        String step4 = step4(step3);
        String step5 = step5(step4);
        String step6 = step6(step5);
        String step7 = step7(step6);

        answer = step7;
        return answer;
    }

    private static String step7(String step6) {
        String result = step6;

        if(result.length() <= 2) {
            char c = result.charAt(result.length()-1);
            while(true) {
                if(result.length() == 3) {
                    break;
                }
                result += c;
            }
        }

        return result;
    }

    private static String step6(String step5) {
        String result = step5;

        if(step5.length() >= 16) {
            result = step4(step5.substring(0, 15));
        }

        return result;
    }

    private static String step5(String step4) {
        if(step4.length() == 0) {
            step4 = "a";
        }

        return step4;
    }

    private static String step4(String step3) {
        if(step3.startsWith(".")) {
            step3 = step3.substring(1, step3.length());
        }

        if(step3.endsWith(".")) {
            step3 = step3.substring(0, step3.length()- 1);
        }

        return step3;
    }
}
