import java.util.*;
import java.io.*;

public class Main {
	static class Info{
		int y,x;
		public Info(int y, int x) {
			this.x=x;
			this.y=y;
		}
	};
	final static int dx[] = {0,1,0,-1};
	final static int dy[] = {-1,0,1,0};
	
	public static void main(String[] args) throws Exception{
		BufferedReader br =  new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int num = Integer.parseInt(st.nextToken());
		int maxV = Integer.parseInt(st.nextToken());	
		
		//초기화
		int arr[][] = new int[num][num];
		Queue<Info> q[] = new Queue[maxV+1];
		for(int i=1;i<=maxV;i++)
			q[i] = new LinkedList<>();
		
		for(int i=0;i<num;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<num;j++) {
				int val = Integer.parseInt(st.nextToken());
				arr[i][j] = val;
				if(val>0) 
					q[val].offer(new Info(i,j));
			}
		}
		st = new StringTokenizer(br.readLine());
		int time = Integer.parseInt(st.nextToken());
		int ty = Integer.parseInt(st.nextToken());
		int tx = Integer.parseInt(st.nextToken());
		
		while(time-->0) {
			for(int i=1;i<=maxV;i++) {
				if(q[i].isEmpty()) continue;
				int len = q[i].size();
				for(int j=0;j<len;j++) {
					Info ii = q[i].poll();
					int cx = ii.x;
					int cy = ii.y;
					for(int k=0;k<4;k++) {
						int nx = cx+dx[k];
						int ny = cy+dy[k];
						if(nx>=0 && ny>=0 && nx<num && ny<num && arr[ny][nx]==0) {
							arr[ny][nx]=i;
							q[i].offer(new Info(ny,nx));
						}
					}
				}
			}
		}
		System.out.println(arr[ty-1][tx-1]);
	}
}
