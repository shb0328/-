import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		int[] arr = new int[n];
		int[] cnt = new int[1000001];
		for (int i = 0; i < n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
			cnt[arr[i]]++;
		}

		Stack<Integer> s = new Stack<Integer>();
		int[] answer = new int[n];
		for (int i = 0; i < n; i++) {
			while (!s.empty() && cnt[arr[i]] > cnt[arr[s.peek()]]) {
				answer[s.pop()] = arr[i];
			}
			s.push(i);
		}

		while (!s.empty()) {
			answer[s.pop()] = -1;
		}

		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < n; i++) {
			sb.append(answer[i] + " ");
		}
		System.out.println(sb.toString());
	}

}
