import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static class Info{
		int y,x;
		public Info(int y, int x) {
			this.y=y;
			this.x=x;
		}
	};
	
	static int row,col;
	static int arr[][];
	static int check[][];
	static int cnt[];		//치료제 보유 마을,1,2,3번 바이러스 감염마을 수
	final static int dx[] = {0,1,0,-1};
	final static int dy[] = {-1,0,1,0};
	static Queue<Info> q;
	
	static void bfs() {
		Queue<Info> dup = new LinkedList<>();
		
		while(true) {
			int sum = 0;
			for(int i=0;i<4;i++)
				sum+=cnt[i];
			if(sum==row*col) break;
			check = new int[row][col];
			
			int len = q.size();
			while(len-->0) {
				Info ii = q.poll();
				int cx = ii.x;
				int cy = ii.y;
				int cv = arr[cy][cx];
				for(int i=0;i<4;i++) {
					int nx = cx+dx[i];
					int ny = cy+dy[i];
					if(nx>=0 && ny>=0 && nx<col && ny<row && arr[ny][nx]==0) {
						int nv = check[ny][nx];
						if(nv>=3) continue;
						else if(nv==cv) continue;
						else {
							check[ny][nx]+=cv;
							if(nv==0) 
								dup.offer(new Info(ny,nx));
						}
					}
				}
			}
			while(!dup.isEmpty()) {
				Info ii = dup.poll();
				int cx = ii.x;
				int cy = ii.y;
				int cv = check[cy][cx];
				arr[cy][cx]=cv;
				cnt[cv]++;
				if(cv<3)
					q.offer(new Info(cy,cx));
			}
		}
	}
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str = br.readLine();
		StringTokenizer st = new StringTokenizer(str);
		row = Integer.parseInt(st.nextToken());
		col = Integer.parseInt(st.nextToken());
		
		//초기화
		arr = new int[row][col];
		q = new LinkedList<>();
		cnt = new int[4];
		
		for(int i=0;i<row;i++) {
			str = br.readLine();
			st = new StringTokenizer(str);
			for(int j=0;j<col;j++) {
				int val = Integer.parseInt(st.nextToken());
				arr[i][j] = val;
				if(val>0) q.offer(new Info(i,j));
				if(val!=0) {
					if(val==-1) val=0;
					cnt[val]++;
				}
			}
		}
		bfs();
		System.out.println(cnt[1]+" "+cnt[2]+" "+cnt[3]);
	}
}
