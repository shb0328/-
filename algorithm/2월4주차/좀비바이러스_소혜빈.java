import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    private static int[] dx = {0, 1, 0, -1};
    private static int[] dy = {-1, 0, 1, 0};
    private static int n, m;
    private static int[][] time;
    private static int[][] town;
    private static boolean[][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        time = new int[n][m];
        town = new int[n][m];
        visited = new boolean[n][m];

        int[] cnt = new int[4];
        Queue<Pos> q = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                int v = Integer.parseInt(st.nextToken());
                if (v == 0) continue;
                visited[i][j] = true;
                town[i][j] = v;
                if (v == 1 || v == 2) {
                    q.offer(new Pos(j, i, v, 0));
                }
            }
        }
        while (!q.isEmpty()) {
            Pos cur = q.poll();
            if (infectedVirus3(cur.x, cur.y)) continue;
            for (int d = 0; d < 4; d++) {
                int nx = cur.x + dx[d];
                int ny = cur.y + dy[d];
                if (!isRange(nx, ny)) continue;
                if (hasVaccine(nx, ny)) continue;
                if (infectedVirus3(nx, ny)) continue;
                if (isVisited(nx, ny)) {
                    if (time[ny][nx] != cur.time + 1) continue;
                    if (town[ny][nx] == cur.virus) continue;
                    town[ny][nx] = 3;
                } else {
                    visited[ny][nx] = true;
                    town[ny][nx] = cur.virus;
                    time[ny][nx] = cur.time + 1;
                    q.offer(new Pos(nx, ny, cur.virus, cur.time + 1));
                }
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                int v = town[i][j];
                if (v == 0 || v == -1) continue;
                cnt[v]++;
            }
        }

        StringBuilder sb = new StringBuilder();
        sb.append(cnt[1]).append(" ").append(cnt[2]).append(" ").append(cnt[3]);
        System.out.println(sb);
        br.close();
    }

    private static boolean isRange(int x, int y) {
        return x >= 0 && y >= 0 && x < m && y < n;
    }

    private static boolean hasVaccine(int nx, int ny) {
        return town[ny][nx] == -1;
    }

    private static boolean infectedVirus3(int nx, int ny) {
        return town[ny][nx] == 3;
    }

    private static boolean isVisited(int x, int y) {
        return visited[y][x];
    }

    private static class Pos {
        int x, y, virus, time;

        public Pos(int x, int y, int virus, int time) {
            this.x = x;
            this.y = y;
            this.virus = virus;
            this.time = time;
        }
    }
}
