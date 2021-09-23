package weeklychallenge;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Queue;
import java.util.stream.Collectors;

public class 퍼즐조각채우기 {

    public static void main(String[] args) {

        int[][] board = {{1,1,0,0,1,0}, {0,0,1,0,1,0}, {0,1,1,0,0,1}, {1,1,0,1,1,1}, {1,0,0,0,1,0}, {0,1,1,1,0,0}};
        int[][] table = {{1,0,0,1,1,0}, {1,0,1,0,1,0}, {0,1,1,0,1,1}, {0,0,1,0,0,0}, {1,1,0,1,1,0}, {0,1,0,0,0,0}};

        System.out.println(solution(board, table));

    }

    public static int solution(int[][] game_board, int[][] table) {
        int[][] newGameBoard = new int[game_board.length + 2][game_board.length + 2];
        int[][] newTable = new int[game_board.length + 2][game_board.length + 2];

        for (int i = 0; i < newGameBoard.length; i++) { // 두 보드에 padding 추가하기
            for (int j = 0; j < newGameBoard.length; j++) {
                if (i == 0 || j == 0 || i == newGameBoard.length - 1
                    || j == newGameBoard.length - 1) {
                    newGameBoard[i][j] = 1;
                    newTable[i][j] = 1;
                } else {
                    newGameBoard[i][j] = game_board[i - 1][j - 1];
                    newTable[i][j] = (table[i - 1][j - 1] + 1) % 2; // 1, 0 바꾸기
                }
            }
        }

        List<Block> blocks = new ArrayList<>();
        bfs(newTable, blocks);

        List<Block> organizedBlocks = blocks.stream()
            .map(block -> reorganizePosition(block))
            .collect(Collectors.toList()); // 블록 위치 0,0 기준으로 재배치

        List<Block> emptyPositions = new ArrayList<>();
        bfs(newGameBoard, emptyPositions); // 보드의 빈 위치 블록 찾기

        List<Block> organizedEmptyPositions = emptyPositions.stream()
            .map(block -> reorganizePosition(block))
            .collect(Collectors.toList());  // 블록 위치 0,0 기준으로 재배치

        int answer = 0;

        for (Block block : organizedBlocks) {
            Block removePosition = null;
            for (Block position : organizedEmptyPositions) {
                Block currentPosition = position;
                for (int i = 0; i < 4; i++) { // 90도 회전하여 비교하기
                    currentPosition = rotateBlock(currentPosition);
                    if (checkBlock(block, currentPosition)) {
                        answer += block.nodes.size();
                        removePosition = position;
                        break;
                    }
                }

                if (removePosition != null) {
                    organizedEmptyPositions.remove(removePosition);
                    break;
                }
            }
        }

        return answer;
    }

    public static Block rotateBlock(Block block) {
        Block newBlock = new Block();

        int maxX = block.maxX();

        block.nodes.forEach(
            node -> newBlock.add(new Node(node.y, (node.x * -1) + maxX))
        );

        Collections.sort(newBlock.nodes);
        return newBlock;
    }

    public static boolean checkBlock(Block block, Block emptyPosition) {
        if (block.nodes.size() != emptyPosition.nodes.size()) {
            return false;
        }

        return block.nodes.equals(emptyPosition.nodes); // 두 블럭의 노트가 같은지 확인
    }

    public static Block reorganizePosition(Block block) {
        int minX = block.minX();
        int minY = block.minY();

        Block newBlock = new Block();

        block.nodes.forEach(
            node -> newBlock.add(new Node(node.x - minX, node.y - minY))
        );

        Collections.sort(newBlock.nodes);
        return newBlock;
    }


    public static void bfs(int[][] table, List<Block> blocks) {
        int[] moveX = {1, -1, 0, 0};
        int[] moveY = {0, 0, 1, -1};

        int[][] visit = new int[table.length][table.length];

        for (int i = 0; i < table.length; i++) {
            for (int j = 0; j < table.length; j++) {
                if (table[i][j] == 0 && visit[i][j] == 0) { //빈 칸이면 이어진 블록 추출하기
                    Block newBlock = new Block();
                    Queue<Node> queue = new LinkedList<>();
                    queue.add(new Node(i, j));
                    visit[i][j] = 1;

                    while (!queue.isEmpty()) {
                        Node current = queue.poll();
                        newBlock.add(current); //블럭에 현재 노드 추가하기

                        for (int k = 0; k < 4; k++) { //4방향을 돌면서 확인하기
                            int nextX = current.x + moveX[k];
                            int nextY = current.y + moveY[k];

                            if (table[nextX][nextY] == 0 && visit[nextX][nextY] == 0) {
                                queue.add(new Node(nextX, nextY));
                                visit[nextX][nextY] = 1;
                            }
                        }
                    }

                    blocks.add(newBlock);
                }
            }
        }
    }

    public static class Block {

        List<Node> nodes = new ArrayList<>();

        public void add(Node node) {
            this.nodes.add(node);
        }

        public int minX() {
            return nodes.stream()
                .mapToInt(node -> node.x)
                .min()
                .getAsInt();
        }

        public int maxX() {
            return nodes.stream()
                .mapToInt(node -> node.x)
                .max()
                .getAsInt();
        }

        public int minY() {
            return nodes.stream()
                .mapToInt(node -> node.y)
                .min()
                .getAsInt();
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            Block block = (Block) o;
            return Objects.equals(nodes, block.nodes);
        }

        @Override
        public int hashCode() {
            return Objects.hash(nodes);
        }
    }

    public static class Node implements Comparable<Node> {

        public int x;
        public int y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public int compareTo(Node o) {
            if (this.x < o.x) {
                return -1;
            }

            if (this.x > o.x) {
                return 1;
            }

            if (this.y < o.y) {
                return -1;
            }

            if (this.y > o.y) {
                return 1;
            }

            return 0;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            Node node = (Node) o;
            return x == node.x && y == node.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
    }
}
