import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int[] dp = new int[10001];
		int n = Integer.parseInt(br.readLine());
		int[] wine = new int[n];
		for (int i = 0; i < n; i++) {
			wine[i] = Integer.parseInt(br.readLine());
		}
		if (n == 1) {
			System.out.println(wine[0]);
			return;
		}
		dp[1] = wine[0];
		dp[2] = wine[0] + wine[1];
		for (int i = 3; i <= n; i++) {
			dp[i] = Math.max(dp[i - 3] + wine[i - 2], dp[i - 2]) + wine[i - 1];
			dp[i] = Math.max(dp[i - 1], dp[i]);
		}
		System.out.println(dp[n]);
		br.close();
	}

}
