import java.util.*;
import java.io.*;

public class 치즈 {
	static class Info{
		int y,x;
		public Info(int y, int x) {
			this.y=y;
			this.x=x;
		}
	};
	
	static int row,col,answer;
	final static int dx[] = {0,1,0,-1};
	final static int dy[] = {-1,0,1,0};
	static int arr[][];
	static boolean check[][];
	static Queue<Info> disappeared;		//사라진 치즈
	
	static void colorOutside() {
		Info ii;
		while(!disappeared.isEmpty()) {
			ii = disappeared.poll();
			int cx = ii.x;
			int cy = ii.y;
			for(int i=0;i<4;i++) {
				int nx = cx+dx[i];
				int ny = cy+dy[i];
				if(nx>=0 && ny>=0 && nx<col && ny<row && !check[ny][nx] && arr[ny][nx]==0) {
					check[ny][nx]=true;
					disappeared.offer(new Info(ny,nx));
				}
			}
		}
	}
	
	static boolean checkFin() {
		boolean finish=true;
		for(int i=0;i<row;i++) {
			for(int j=0;j<col;j++) {
				if(arr[i][j]==1) {
					finish=false;
					break;
				}
			}
			if(!finish) break;
		}
		return finish;
	}
	
	static void cheezeDisappear() {
		Queue<Info> dup = new LinkedList<>();
		for(int i=0;i<row;i++) {
			for(int j=0;j<col;j++) {
				if(arr[i][j]==1) {
					int cnt = 0;
					for(int k=0;k<4;k++) {
						int nx = j+dx[k];
						int ny = i+dy[k];
						if(arr[ny][nx]==0 && check[ny][nx])	cnt++;
					}
					if(cnt>1) {
						disappeared.offer(new Info(i,j));
						dup.offer(new Info(i,j));
					}
				}
			}
		}
		while(!dup.isEmpty()) {
			Info ii = dup.poll();
			int cx = ii.x;
			int cy = ii.y;
			arr[cy][cx]=0;
			check[cy][cx]=true;
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
		check = new boolean[row][col];
		answer = 0;
		disappeared = new LinkedList<>();
		disappeared.offer(new Info(0,0));
		check[0][0]=true;
		
		for(int i=0;i<row;i++) {
			str = br.readLine();
			st = new StringTokenizer(str);
			for(int j=0;j<col;j++)
				arr[i][j] = Integer.parseInt(st.nextToken());
		}
		
		while(true) {
			if(checkFin()) break;
			answer++;
			colorOutside();
			cheezeDisappear();
		}
		System.out.println(answer);
	}
}
