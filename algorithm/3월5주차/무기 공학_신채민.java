import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int n, m;
	static int[][] map;
	static boolean visit[][];
	static int result = 0;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		map = new int[n + 1][m + 1];
		visit = new boolean[n + 1][m + 1];

		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < m; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		sol(0, 0);
		System.out.println(result);
		br.close();
	}

	public static void sol(int cnt, int sum) {
		if (cnt == n * m) {
			if (result < sum)
				result = sum;
			return;
		}

		int x = cnt / m;
		int y = cnt % m;
		if (!visit[x][y]) {
			if (x + 1 < n && y + 1 < m && !visit[x + 1][y] && !visit[x][y + 1]) {
				visit[x][y] = true;
				visit[x + 1][y] = true;
				visit[x][y + 1] = true;
				int tsum = sum + 2 * (map[x][y]) + map[x + 1][y] + map[x][y + 1];
				sol(cnt + 1, tsum);
				visit[x][y] = false;
				visit[x + 1][y] = false;
				visit[x][y + 1] = false;
			}

			if (x + 1 < n && y - 1 >= 0 && !visit[x + 1][y] && !visit[x][y - 1]) {
				visit[x][y] = true;
				visit[x + 1][y] = true;
				visit[x][y - 1] = true;
				int tsum = sum + 2 * (map[x][y]) + map[x + 1][y] + map[x][y - 1];
				sol(cnt + 1, tsum);
				visit[x][y] = false;
				visit[x + 1][y] = false;
				visit[x][y - 1] = false;
			}

			if (x - 1 >= 0 && y - 1 >= 0 && !visit[x - 1][y] && !visit[x][y - 1]) {
				visit[x][y] = true;
				visit[x - 1][y] = true;
				visit[x][y - 1] = true;
				int tsum = sum + 2 * (map[x][y]) + map[x - 1][y] + map[x][y - 1];
				sol(cnt + 1, tsum);
				visit[x][y] = false;
				visit[x - 1][y] = false;
				visit[x][y - 1] = false;
			}

			if (x - 1 >= 0 && y + 1 < m && !visit[x - 1][y] && !visit[x][y + 1]) {
				visit[x][y] = true;
				visit[x - 1][y] = true;
				visit[x][y + 1] = true;
				int tsum = sum + 2 * (map[x][y]) + map[x - 1][y] + map[x][y + 1];
				sol(cnt + 1, tsum);
				visit[x][y] = false;
				visit[x - 1][y] = false;
				visit[x][y + 1] = false;
			}

		}
		sol(cnt + 1, sum);
	}

}
