package Algorithm.algorithm.Y5주차;

import java.util.PriorityQueue;

public class 길찾기게임 {

    static int[][] nodeinfo = {{5, 3}, {11, 5}, {13, 3}, {3, 5}, {6, 1}, {1, 3}, {8, 6}, {7, 2},
            {2, 2}};

    public static void main(String[] args) {
        int[][] solution = solution(nodeinfo);
        for (int i = 0; i < solution.length; i++) {
            for (int j = 0; j < solution[0].length; j++) {
                System.out.print(solution[i][j] + " ");
            }
            System.out.println();
        }
    }

    static int[][] answer;
    static int idx = 0;
    public static int[][] solution(int[][] nodeinfo) {

        answer = new int[2][nodeinfo.length];

        PriorityQueue<Info> q = new PriorityQueue<>();

        for (int i = 0; i < nodeinfo.length; i++) {
            q.add(new Info(nodeinfo[i][0], nodeinfo[i][1], i + 1));
        }

        Info root = q.poll();
        Node rootNode = new Node(root.num, root.x, root.y);

        while (!q.isEmpty()) {
            Info curInfo = q.poll();
            Node curNode = new Node(curInfo.num, curInfo.x, curInfo.y);
            rootNode.insertNode(curNode);
        }

        rootNode.preOrder();
        idx = 0;
        rootNode.postOrder();
        return answer;
    }

    public static class Info implements Comparable<Info> {

        public int x, y, num;

        public Info(int x, int y, int num) {
            this.x = x;
            this.y = y;
            this.num = num;
        }

        @Override
        public int compareTo(Info i) {
            return i.y - this.y;
        }
    }

    public static class Node {

        public Node right;
        public Node left;
        public int value;
        public int x;
        public int y;

        public Node(int value, int x, int y) {
            right = null;
            left = null;
            this.value = value;
            this.x = x;
            this.y = y;
        }

        public void insertNode(Node node) {
            if (x < node.x) {
                if (right == null) {
                    right = node;
                } else {
                    right.insertNode(node);
                }
            } else if (x > node.x) {
                if (left == null) {
                    left = node;
                } else {
                    left.insertNode(node);
                }
            }
        }

        public void preOrder() {
            answer[0][idx++] = value;

            if(left != null) {
                left.preOrder();
            }

            if(right != null) {
                right.preOrder();
            }
        }

        public void postOrder() {
            if(left != null) {
                left.postOrder();
            }

            if(right != null) {
                right.postOrder();
            }

            answer[1][idx++] = value;
        }
    }
}
