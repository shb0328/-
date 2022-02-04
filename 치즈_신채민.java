//미완..
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	public static int[] di = { -1, 0, 1, 0 };
	public static int[] dj = { 0, -1, 0, 1 };
	public static int n, m;
	public static int[][] paper;
	public static Queue<int[]> queue = new LinkedList<int[]>();
	public static Queue<int[]> end = new LinkedList<int[]>();

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		paper = new int[n][m];

		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < m; j++) {
				paper[i][j] = Integer.parseInt(st.nextToken());
				if (paper[i][j] == 1)
					queue.offer(new int[] { i, j, 0 });
			}
		}

		int time = 0;
		while (!queue.isEmpty()) {
			int size = queue.size();
			for (int i = 0; i < size; i++) {
				int[] curr = queue.poll();
				bfs(curr[0], curr[1]);
			}
			while (!end.isEmpty()) {
				int[] curr = end.poll();
				paper[curr[0]][curr[1]]--;
			}
			time++;
		}

		System.out.println(time-1);
		br.close();
	}

	public static void bfs(int i, int j) {
		int ni = i;
		int nj = j;
		int cnt = 0;
		for (int d = 0; d < 4; d++) {
			ni += di[d];
			nj += dj[d];
			if (ni >= 0 && ni < n && nj >= 0 && nj < m && paper[ni][nj] == 0) {
				cnt++;
			}
		}
		if (cnt >= 2)
			end.offer(new int[] { i, j });
		else
			queue.offer(new int[] { i, j });
	}

}
