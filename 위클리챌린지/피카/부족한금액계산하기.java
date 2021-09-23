package Algorithm.algorithm.Y9주차;

public class 부족한금액계산하기 {

    static int price = 3;
    static int money = 20;
    static int count = 4;

    public static void main(String[] args) {
        System.out.println(solution(price, money, count));
    }

    public static long solution(int price, int money, int count) {
        long answer = 0;

        long fee = 0;

        for (int i = 1; i <= count; i++) {
            fee += price * i;
        }

        if(money < fee) {
            return fee - money;
        }
        return answer;
    }
}
