import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str = br.readLine();
		String answer = "";
		String[] arr = str.split(":");
		int cnt = 0;
		for (String s : arr) {
			if (s.length() == 0) {
				if (cnt == 1) {
					int num = arr.length - 2;
					for (int i = 0; i < 8 - num; i++)
						answer += "0000:";
					cnt = 0;
				} else {
					cnt++;
				}
				continue;
			}
			if (s.length() == 1) {
				answer += "000" + s + ":";
			} else if (s.length() == 2) {
				answer += "00" + s + ":";
			} else if (s.length() == 3) {
				answer += "0" + s + ":";
			} else {
				answer += s + ":";
			}
		}
		if(str.substring(str.length()-2,str.length()).equals("::")){
			int num = arr.length;
			for (int i = 0; i < 8 - num; i++)
				answer += "0000:";
		}
		System.out.println(answer.substring(0, answer.length() - 1));
		br.close();
	}

}
