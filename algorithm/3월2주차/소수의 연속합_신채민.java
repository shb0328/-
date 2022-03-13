import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] primeArr = new int[N];
		int idx = 0;
		for (int i = 2; i <= N; i++) {
			int num = makePrime(i);
			if (num != -1)
				primeArr[idx++] = num;
		}

		int start = 0;
		int end = idx;
		int answer = 0;
		
		while (start <= end) {
			int sum = 0;
			for (int i = start; i < end; i++) {
				sum += primeArr[i];
				if (sum == N)
					answer++;
				else if (sum > N) {
					break;
				}
			}
			start++;
		}

		System.out.println(answer);
		br.close();
	}

	public static int makePrime(int num) {

		if (num == 2)
			return num;

		for (int i = 2; i <= Math.sqrt(num); i++) {
			if (num % i == 0)
				return -1;
		}

		return num;
	}

}
