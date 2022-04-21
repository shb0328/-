import java.io.*;
import java.util.*;

public class Main {
	static class Info{
		int val,cnt;
		public Info(int val, int cnt) {
			this.val=val;
			this.cnt=cnt;
		}
	};
	
	final static int MAX = 987654321;
	static boolean isPrime[];
	
	static void setPrime() {
		for(int i=2;i<10000;i++)
			isPrime[i]=true;
		for(int i=2;i<Math.sqrt(10000);i++) {
			if(!isPrime[i]) continue;
			for(int j=i+i;j<10000;j+=i)
				isPrime[j]=false;
		}
	}
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int num = Integer.parseInt(br.readLine());
		isPrime = new boolean[10000];
		setPrime();
		
		for(int i=0;i<num;i++) {
			String str = br.readLine();
			StringTokenizer st = new StringTokenizer(str);
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			//초기화
			Set<Integer> set = new HashSet<>();
			Queue<Info> q = new LinkedList<>();
			q.offer(new Info(from,0));
			int answer = MAX;
			
			while(!q.isEmpty()) {
				Info ii = q.poll();
				int cv = ii.val;
				int cc = ii.cnt;
				if(cv==to) {
					answer = cc;
					break;
				}
				for(int j=0;j<4;j++) {
					int mod = (int)Math.pow(10,j);
					int posVal = (cv/mod)%10;
					int newVal = cv-posVal*mod;
					for(int k=0;k<10;k++) {
						if(k==posVal) continue;
						int nv = newVal+k*mod;
						if(nv<1000 || nv>9999) continue;
						if(isPrime[nv] && !set.contains(nv)) {
							set.add(nv);
							q.offer(new Info(nv,cc+1));
						}
					}
				}
			}
			System.out.println(answer == MAX ? "Impossible" : answer);
		}
	}
}
