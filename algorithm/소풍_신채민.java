import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static int K, N, F;
	public static int[][] friend;
	public static boolean flag;
	public static boolean[] visit;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		K = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		F = Integer.parseInt(st.nextToken());
		friend = new int[N + 1][N + 1];
		int[] cntFriend = new int[N + 1];

		for (int i = 0; i < F; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			friend[x][y] = 1;
			friend[y][x] = 1;

			cntFriend[x]++;
			cntFriend[y]++;
		}

		visit = new boolean[N + 1];
		for (int i = 1; i <= N; i++) {
			if (cntFriend[i] < K - 1)
				continue;
			flag = false;
			visit[i] = true;
			sol(i, 1);
			visit[i] = false;
			if (flag) {
				return;
			}
		}

		System.out.println("-1");
	}

	public static void sol(int node, int cnt) {
		if (flag)
			return;

		if (cnt == K) {
			for (int i = 1; i <= N; i++) {
				if (visit[i])
					System.out.println(i);
			}
			flag = true;
			return;
		}

		for (int i = node + 1; i <= N; i++) {

			if (friend[node][i] == 1 && isFriend(i)) {
				visit[i] = true;
				sol(i, cnt + 1);
				visit[i] = false;
			}
		}
	}

	public static boolean isFriend(int target) {
		for (int i = 1; i <= N; i++) {
			if (visit[i] && friend[target][i] != 1) {
				return false;
			}
		}
		return true;
	}

}
