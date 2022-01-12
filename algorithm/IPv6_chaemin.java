import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str = br.readLine();
		String answer = "";
		String[] arr = str.split(":");
		List<String> li = new ArrayList<>();
		List<String> tmp = new ArrayList<>();
		int cnt = 0;

		for (String s1 : arr) {
			int len = s1.length();
			if (len > 0) {
				cnt++;
				if (len < 4) {
					for (int i = len; i < 4; i++) {
						s1 = "0" + s1;
					}
				}
			} else
				s1 = "-1";
			li.add(s1);
		}

		boolean flag = false;
		for (String s2 : li) {
			if (s2.equals("-1")) {
				if (flag)
					continue;
				for (int i = 0; i < 8 - cnt; i++) {
					tmp.add("0000");
				}
				flag = true;
			} else
				tmp.add(s2);
		}
		
		while(cnt<8) {
			tmp.add("0000");
			cnt++;
		}
		for(int i=0;i<8;i++) {
			System.out.print(tmp.get(i));
			if(i<7)System.out.print(":");
		}

		br.close();
	}

}
