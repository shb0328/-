import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int[] tree = new int[n + 1];
		int[][] bridge = new int[n][2];
		for (int i = 0; i < n - 1; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			bridge[i + 1] = new int[] {x, y};
			tree[x]++;
			tree[y]++;
		}
		
		int q = Integer.parseInt(br.readLine());
		for (int i = 0; i < q; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int t = Integer.parseInt(st.nextToken());
			int k = Integer.parseInt(st.nextToken());
			if(t==1) {//단절점
				if(tree[k]==1) {
					System.out.println("no");
				}else {
					System.out.println("yes");
				}
			}
			else {//단절선
				System.out.println("yes");
			}
		}
		br.close();
	}
	
	
}
