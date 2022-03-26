import java.util.*;
import java.lang.*;
import java.io.*;

public class Main {
	static class Info{
		int idx,num;
		public Info(int idx, int num) {
			this.idx=idx;
			this.num=num;
		}
	};
	
	static int node;
	static List<Info> li[];
	static int arriveNum[];
	static boolean isArticultaionPoint[];
	static boolean isArticultaionEdge[];
	static int cnt;

	static int findArticPoint(int idx, boolean isRoot) {
		int len = li[idx].size();
		arriveNum[idx] = cnt++; 	// 도착 순번
		int temp = arriveNum[idx];

		for (int i = 0; i < len; i++) {
			int next = li[idx].get(i).idx;
			if (arriveNum[next] != 0) { 	// 이미 도달한 적 있는 경우
				temp = Math.min(temp, arriveNum[next]);
			} else {
				int result = findArticPoint(next, false);
				if (!isRoot && result >= arriveNum[idx])
					isArticultaionPoint[idx] = true;
				temp = Math.min(temp, result);
			}
		}
		if (isRoot && len > 1)
			isArticultaionPoint[idx] = true;
		return temp;
	}

	static int findArticEdge(int cur, int pre) {
		arriveNum[cur] = cnt++;
		int temp = arriveNum[cur];
		
		for(int i=0;i<li[cur].size();i++) {
			int next = li[cur].get(i).idx;
			if(arriveNum[next]!=0) {
				temp = Math.min(temp, arriveNum[next]);
			} else {
				int result = findArticEdge(next,cur);
				if(result>=arriveNum[cur])
					isArticultaionEdge[li[cur].get(i).num]=true;
				temp = Math.min(temp, result);
			}
		}
		return temp;
	}
	
	public static void main(String[] args) throws java.lang.Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		node = Integer.parseInt(br.readLine());

		// 초기화
		li = new List[node + 1];
		for (int i = 1; i <= node; i++)
			li[i] = new ArrayList<>();
		arriveNum = new int[node + 1];
		cnt = 1;
		isArticultaionPoint = new boolean[node + 1];
		isArticultaionEdge = new boolean[node + 1];		

		for (int i = 1; i < node; i++) {
			String str = br.readLine();
			StringTokenizer st = new StringTokenizer(str);
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			li[a].add(new Info(b,i));
			li[b].add(new Info(a,i));
		}
		findArticPoint(1, true);
		cnt=1;
		arriveNum = new int[node + 1];
		findArticEdge(1,0);
		int query = Integer.parseInt(br.readLine());
		for (int q = 0; q < query; q++) {
			String str = br.readLine();
			StringTokenizer st = new StringTokenizer(str);
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			if (a == 1)
				System.out.println(isArticultaionPoint[b] ? "yes" : "no");
			else
				System.out.println(isArticultaionEdge[b] ? "yes" : "no");
		}
	}
}
