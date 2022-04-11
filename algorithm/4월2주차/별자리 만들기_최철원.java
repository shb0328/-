import java.util.*;
import java.io.*;

public class Main {
	static class Info implements Comparable<Info>{
		int from,to;
		double val;
		public Info(int from, int to, double val) {
			this.from=from;
			this.to=to;
			this.val=val;
		}
		@Override
		public int compareTo(Info o) {
			return Double.compare(this.val, o.val);
		}
	};
	
	static double x[];
	static double y[];
	static PriorityQueue<Info> pq;
	static int par[];
	
	static int findPar(int x) {
		if(par[x]==x) return x;
		return par[x] = findPar(par[x]);
	}
	
	static void makeUnion(int a, int b) {
		int pa = findPar(a);
		int pb = findPar(b);
		if(pa>pb) 	par[pa] = pb;
		else if(pa<pb) par[pb] = pa;
	}
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int num = Integer.parseInt(br.readLine());
		
		//초기화
		x = new double[num];
		y = new double[num];
		pq = new PriorityQueue<>();
		par = new int[num];
		for(int i=0;i<num;i++)
			par[i]=i;
		double answer = 0;
		
		for(int i=0;i<num;i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			x[i] = Double.parseDouble(st.nextToken());
			y[i] = Double.parseDouble(st.nextToken());
		}

		for(int i=0;i<num-1;i++) 
			for(int j=i+1;j<num;j++) {
				double dx = (x[i]-x[j])*(x[i]-x[j]);
				double dy = (y[i]-y[j])*(y[i]-y[j]);
				double val = Math.sqrt(dx+dy);
				pq.offer(new Info(i,j,val));
			}
		
		while(!pq.isEmpty()) {
			Info ii = pq.poll();
			int from = ii.from;
			int to = ii.to;
			double val = ii.val;
			int pf = findPar(from);
			int pt = findPar(to);
			if(pf==pt) continue;
			makeUnion(from,to);
			answer+=val;
		}
		System.out.println(answer);
	}
}
