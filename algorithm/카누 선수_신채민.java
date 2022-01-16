import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
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

			int[] sum1 = new int[n * n];
			int[] sum2 = new int[n * n];
			int index = 0;
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					sum1[index] = student[0][i] + student[1][j];
					sum2[index++] = student[2][i] + student[3][j];
				}
			}
			Arrays.sort(sum2);

			// 이분탐색
			int answer = sum1[0] + sum2[0];
			for (int i = 0; i < n*n; i++) {
				int start = 0;
				int end = n * n - 1;
				int mid = 0;
				while (start <= end) {
					mid = (start + end) / 2;
					int tmp = sum1[i] + sum2[mid];
					if (Math.abs(tmp - k) < Math.abs(answer - k)) {
						answer = tmp;
					} else if (Math.abs(tmp - k) == Math.abs(answer - k)) {
						answer = tmp < answer ? tmp : answer;
					}

					if (tmp < k) {
						start = mid + 1;
					} else if (tmp > k) {
						end = mid - 1;
					}else {
						answer = k;
						break;
					}
				}
			}

			System.out.println(answer);
		}
		br.close();
	}

}
