import java.util.*;
import java.io.*;

public class 소풍{
	static boolean arr[][],finish;
	static int willGo, num, relation;
	static List<Integer> li;
	static int temp[],ans[];
	
	static void dfs(int idx, int cnt) {
		if(finish) return;
		if(cnt==willGo) {
			boolean canFin = true;
			for(int i=0;i<willGo;i++) {
				for(int j=0;j<willGo;j++) {
					if(i==j) continue;
					if(!arr[temp[i]][temp[j]]) {
						canFin=false;
						break;
					}
				}
				if(!canFin) break;
			}
			if(canFin) {
				finish=true;
				ans = temp.clone();
			}
			return;
		}
		for(int i=idx;i<li.size();i++) {
			temp[cnt]=li.get(i);
			dfs(idx+1,cnt+1);
			temp[cnt]=0;
		}
	}
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str = br.readLine();
		StringTokenizer st = new StringTokenizer(str);
		
		willGo = Integer.parseInt(st.nextToken());
		num = Integer.parseInt(st.nextToken());
		relation = Integer.parseInt(st.nextToken());
		
		//초기화
		arr = new boolean[num+1][num+1];
		li = new ArrayList<>();
		temp = new int[willGo];
		ans = new int[willGo];
		finish=false;
		
		for(int i=0;i<relation;i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			arr[a][b]=true;
			arr[b][a]=true;
		}
		
		for(int i=1;i<=num;i++) {
			int cnt = 0;
			for(int j=1;j<=num;j++) 
				if(arr[i][j]) cnt++;
			if(cnt>=willGo-1)
				li.add(i);
		}
		
		dfs(0,0);
		if(!finish) System.out.println(-1);
		else {
			for(int a: ans)
				System.out.println(a);
		}
	}
}