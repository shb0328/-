import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		int[] W = new int[N];
		int[] V = new int[N];
		int[][] DP = new int[N + 1][K + 1];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			W[i] = Integer.parseInt(st.nextToken());
			V[i] = Integer.parseInt(st.nextToken());
		}

		int max = 0;
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= K; j++) {
				if (W[i - 1] <= j)
					DP[i][j] = Math.max(V[i - 1] + DP[i - 1][j - W[i - 1]], DP[i - 1][j]);
				else
					DP[i][j] = DP[i - 1][j];
				max = Math.max(DP[i][j], max);
			}
		}

		System.out.println(max);
		br.close();
	}

}
