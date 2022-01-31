import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String numStr = br.readLine();
		String keyword = br.readLine();
		String tmpStr = numStr.replaceAll("[0-9]", "");

		System.out.println(kmp(tmpStr, keyword));
		br.close();
	}

	public static int kmp(String s, String p) {
		int[] pi = getPi(s);
		char[] parents = s.toCharArray();
		char[] patterns = p.toCharArray();
		int k = 0;
		for (int i = 0; i < parents.length; i++) {
			while (k > 0 && parents[i] != patterns[k]) {
				k = pi[k - 1];
			}
			if (parents[i] == patterns[k]) {
				if (k == patterns.length - 1)
					return 1;
				else
					k++;
			}
		}
		return 0;
	}

	public static int[] getPi(String s) {
		char[] patterns = s.toCharArray();
		int[] res = new int[patterns.length];
		int k = 0;
		for (int i = 1; i < patterns.length; i++) {
			while (k > 0 && patterns[i] != patterns[k]) {
				k = res[k - 1];
			}
			if (patterns[i] == patterns[k]) {
				res[i] = ++k;
			}
		}
		return res;
	}

}
