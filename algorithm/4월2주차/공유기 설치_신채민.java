import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int c = Integer.parseInt(st.nextToken());
		int[] x = new int[n];
		for (int i = 0; i < n; i++) {
			x[i] = Integer.parseInt(br.readLine());
		}
		Arrays.sort(x);

		int start = 1;
		int end = x[n - 1] - x[0] + 1;

		while (start < end) {
			int mid = (start + end) / 2;
			if (calc(mid, x) < c) {
				end = mid;
			} else {
				start = mid + 1;
			}
		}

		System.out.println(start - 1);
		br.close();
	}

	public static int calc(int mid, int[] x) {
		int cnt = 1;
		int idx = x[0];

		for (int i = 1; i < x.length; i++) {
			int curr = x[i];
			if (curr - idx >= mid) {
				cnt++;
				idx = curr;
			}
		}
		return cnt;
	}

}
