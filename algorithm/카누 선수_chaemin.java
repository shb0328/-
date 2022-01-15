//시간초과,,,
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static int k;
	public static int n;
	public static int[][] student;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int tc = Integer.parseInt(br.readLine());
		for (int t = 0; t < tc; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			k = Integer.parseInt(st.nextToken());
			n = Integer.parseInt(st.nextToken());
			student = new int[4][n];
			for (int i = 0; i < 4; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < n; j++) {
					student[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			sol(0, 0, 0);
			System.out.println(result);
		}
		br.close();
	}

	public static int result;

	public static void sol(int i, int j, int sum) {

		if (Math.abs(sum - k) < Math.abs(result - k)) {
			result = sum;
		} else if (Math.abs(sum - k) == Math.abs(result - k)) {
			result = (result < sum) ? result : sum;
		}

		if (i == 4) {
			return;
		}

		for (int p = 0; p < n; p++) {
			if (j + p < n) {
				sol(i + 1, j + p, sum + student[i][j]);
			}
		}

		return;
	}

}
