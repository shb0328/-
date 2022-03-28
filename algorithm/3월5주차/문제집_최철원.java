import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int num,query;
		String str = br.readLine();
		StringTokenizer st = new StringTokenizer(str);
		num = Integer.parseInt(st.nextToken());
		query = Integer.parseInt(st.nextToken());
		
		//초기화
		int conn[] = new int[num+1];
		PriorityQueue<Integer> pq = new PriorityQueue<>();
		List<Integer> li[] = new List[num+1];
		for(int i=1;i<=num;i++) 
			li[i] = new ArrayList<>();
		
		for(int i=0;i<query;i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			conn[b]++;
			li[a].add(b);
		}
		
		for(int i=1;i<=num;i++) 
			if(conn[i]==0)
				pq.offer(i);
		while(!pq.isEmpty()) {
			int cidx = pq.poll();
			System.out.print(cidx+" ");
			for(int i=0;i<li[cidx].size();i++) {
				int next = li[cidx].get(i);
				if(--conn[next]==0)
					pq.offer(next);
			}
		}
	}
}
