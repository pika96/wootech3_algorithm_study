package weeklychallenge;

public class 부족한금액계산하기 {

    public static void main(String[] args) {

        solution(3, 20, 4); //ans = 10
    }

    public static long solution(int price, int money, int count) {
        long totalPrice = 0;
        for (int i = 1; i <= count; i++) {
            totalPrice += price * i;
        }

        long answer = totalPrice - money;

        if (answer > 0) {
            return 0;
        }

        return answer;
    }

}
