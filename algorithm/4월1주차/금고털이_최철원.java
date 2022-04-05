import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
	static class Info implements Comparable<Info>{
		int weight,val;
		public Info(int weight, int val) {
			this.weight = weight;
			this.val = val;
		}
		@Override
		public int compareTo(Info o) {
			return Integer.compare(o.val,this.val);
		}
	}
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str = br.readLine();
		StringTokenizer st = new StringTokenizer(str);
		int bagWeight = Integer.parseInt(st.nextToken());
		int num = Integer.parseInt(st.nextToken());
		
		PriorityQueue<Info> pq = new PriorityQueue<>();
		
		for(int i=0;i<num;i++) {
			st = new StringTokenizer(br.readLine());
			int weight = Integer.parseInt(st.nextToken());
			int val = Integer.parseInt(st.nextToken());
			pq.offer(new Info(weight,val));
		}
		long answer = 0;
		
		while(!pq.isEmpty()) {
			Info ii = pq.poll();
			int canGet = Math.min(bagWeight, ii.weight);
			answer+=(canGet*ii.val);
			bagWeight-=canGet;
			if(bagWeight==0) break;
		}
		System.out.println(answer);
	}
}
