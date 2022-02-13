import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

import static java.util.Arrays.asList;

public class 달이차오른다가자_소혜빈 {
    private static int[] dx = {0, 1, 0, -1};
    private static int[] dy = {1, 0, -1, 0};

    private static char[][] map;
    private static boolean[][][] visited;
    private static List<Character> keys;
    private static List<Character> doors;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        map = new char[n][m];
        visited = new boolean[n][m][64]; //000000 ~ 111111
        keys = asList('a', 'b', 'c', 'd', 'e', 'f');
        doors = keys.stream().map(c -> (char) (c + 'A' - 'a')).collect(Collectors.toList());

        int x = 0, y = 0;
        for (int i = 0; i < n; i++) {
            map[i] = br.readLine().toCharArray();
            for (int j = 0; j < m; j++) {
                if (map[i][j] == '0') {
                    x = j;
                    y = i;
                }
            }
        }

        int answer = bfs(x, y);
        System.out.println(answer);
        br.close();
    }//end of main

    private static int bfs(int x, int y) {
        Queue<Node> q = new LinkedList<>();
        q.offer(new Node(x, y, 0, 0));

        while (!q.isEmpty()) {
            Node curr = q.poll();
            if (isEnd(curr.x, curr.y)) return curr.cnt;

            for (int d = 0; d < 4; d++) {
                int nextX = curr.x + dx[d];
                int nextY = curr.y + dy[d];

                if (!isRange(nextX, nextY)) continue;
                if (visited[nextY][nextX][curr.keys]) continue;
                if (isWall(nextX, nextY)) continue;

                if (isKey(nextX, nextY)) {
                    int newKey = (1 << (map[nextY][nextX] - 'a')) | curr.keys;
                    if (visited[nextY][nextX][newKey]) continue;
                    visited[nextY][nextX][newKey] = true;
                    visited[nextY][nextX][curr.keys] = true;
                    q.offer(new Node(nextX, nextY, curr.cnt + 1, newKey));
                } else if (isDoor(nextX, nextY)) {
                    if (hasKey(nextX, nextY, curr.keys)) {
                        visited[nextY][nextX][curr.keys] = true;
                        q.offer(new Node(nextX, nextY, curr.cnt + 1, curr.keys));
                    }
                } else {
                    visited[nextY][nextX][curr.keys] = true;
                    q.offer(new Node(nextX, nextY, curr.cnt + 1, curr.keys));
                }
            }//end of for
        }//end of while
        return -1;
    }//end of bfs

    private static boolean isRange(int x, int y) {
        return y >= 0 && x >= 0 && y < map.length && x < map[y].length;
    }

    private static boolean isEnd(int x, int y) {
        return map[y][x] == '1';
    }

    private static boolean isWall(int x, int y) {
        return map[y][x] == '#';
    }

    private static boolean isKey(int x, int y) {
        return keys.contains(map[y][x]);
    }

    private static boolean isDoor(int x, int y) {
        return doors.contains(map[y][x]);
    }

    private static boolean hasKey(int x, int y, int keys) {
        return ((1 << (map[y][x] - 'A')) & keys) > 0;
    }
}//end of class

class Node {
    public int x, y, cnt, keys;

    public Node(int x, int y, int cnt, int k) {
        this.x = x;
        this.y = y;
        this.cnt = cnt;
        this.keys = k;
    }

    @Override
    public String toString() {
        return "Node{" +
                "x=" + x +
                ", y=" + y +
                ", cnt=" + cnt +
                ", keys=" + keys +
                '}';
    }
}//end of class
