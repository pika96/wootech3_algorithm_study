package kakao2021;

public class 신규아이디추천 {

     public static void main(String[] args) {

          String input = "...!@BaT#*..y.abcdefghijklm";
          String answer = solution(input);

          System.out.println(answer);
     }

     public static String solution(String new_id) {
          // 1 단계 모든 대문자를 소문자로 치환
          StringBuilder id = new StringBuilder(new_id.toLowerCase());

          // 2 단계 소문자, 숫자, -, _, . 제외한 문제 제거
          int len = id.length();
          String temp = id.toString();
          for (int i = 0; i < len; i++) {
               char current = temp.charAt(i);
               if (!Character.isAlphabetic(current) && !Character.isDigit(current) &&
                   current != '-' && current != '_' && current != '.') {
                    id = new StringBuilder(id.toString().replace(String.valueOf(current), ""));
               }
          }

          // 3 단계 마침표 두개 이상 반복 . 로 줄이기
          int exist = 1;
          while (exist == 1) {
               exist = 0;
               if (id.toString().contains("..")) {
                    exist = 1;
                    id = new StringBuilder(id.toString().replace("..", "."));
               }
          }

          // 4 단계 마침표가 처음이나 끝이면 remove
          if (id.toString().endsWith(".")) {
               id = new StringBuilder(id.substring(0, id.length() - 1));
          }

          if (id.toString().startsWith(".")) {
               id = new StringBuilder(id.substring(1));
          }

          // 5단계 빈 문자열이면 a 대입
          if (id.length() == 0) {
               id = new StringBuilder("a");
          }

          // 6단계 16자 이상이라면 15자까지 제거 후 끝 마침표 제거
          if (id.length() >= 16) {
               id = new StringBuilder(id.substring(0, 15));
               if (id.toString().endsWith(".")) {
                    id = new StringBuilder(id.substring(0, 14));
               }
          }

          // 7단계 2자 이하라면 마지막 문제 3일때까지 이어붙이기
          if (id.length() <= 2) {
               while (id.length() < 3) {
                    id.append(id.charAt(id.length() - 1));
               }
          }

          return id.toString();
     }
}
