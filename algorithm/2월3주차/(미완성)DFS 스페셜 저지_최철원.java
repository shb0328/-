// 73% TLE
import java.io.*;
import java.util.*;

public class DFS_스페셜_저지 {
	
	static int num,answer;
	static List<Integer> li[];
	static boolean check[];
	static int arr[];
	static int idx;
	
	static void dfs(int curNode) {
		for(int i=0;i<li[curNode].size();i++) {
			int next = li[curNode].get(i);
			if(!check[next] && arr[idx]==next) {
				idx++;
				check[next]=true;
				dfs(next);
				i = -1;
			}
		}
	}
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		num = Integer.parseInt(br.readLine());
		
		//초기화
		li = new List[num+1];
		for(int i=1;i<=num;i++)
			li[i] = new ArrayList<>();
		arr = new int[num];
		check = new boolean[num+1];
		
		for(int i=0;i<num-1;i++) {
			String str = br.readLine();
			StringTokenizer st = new StringTokenizer(str);
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			li[a].add(b);
			li[b].add(a);
		}
		String str = br.readLine();
		StringTokenizer st = new StringTokenizer(str);
		for(int i=0;i<num;i++)
			arr[i] = Integer.parseInt(st.nextToken());
		
		if(arr[0]==1) {
			check[1]=true;
			idx=1;
			dfs(1);
		}
		
		System.out.println(idx==num ? 1 : 0);
	}
}
