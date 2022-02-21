import java.util.*;
import java.io.*;

public class Main {
	static class Info{
		int y,x,val,keys;
		public Info(int y, int x, int val, int keys) {
			this.y=y;
			this.x=x;
			this.val=val;
			this.keys=keys;
		}
	};
	
	static char arr[][];
	static int check[][][];
	static int row,col,sx,sy;
	final static int dx[] = {0,1,0,-1};
	final static int dy[] = {-1,0,1,0};
	final static int MAX = 987654321;
	 
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str = br.readLine();
		StringTokenizer st = new StringTokenizer(str);
		row = Integer.parseInt(st.nextToken());
		col = Integer.parseInt(st.nextToken());
		
		//초기화
		arr = new char[row][col];
		check = new int[row][col][64];
		int finish = -1;
		
		for(int i=0;i<row;i++) {
			str = br.readLine();
			for(int j=0;j<col;j++) {
				for(int k=0;k<64;k++)
					check[i][j][k]=MAX;
				char c = str.charAt(j);
				if(c=='0') {
					sy = i;
					sx = j;
				}
				arr[i][j]=c;
			}
		}
		Queue<Info> q = new LinkedList<>();
		q.offer(new Info(sy,sx,0,0));
		while(!q.isEmpty()) {
			Info ii = q.poll();
			int cx = ii.x;
			int cy = ii.y;
			int cv = ii.val;
			int ck = ii.keys;
			if(arr[cy][cx]=='1') {
				finish=cv;
				break;
			}
			for(int i=0;i<4;i++) {
				int nx = cx+dx[i];
				int ny = cy+dy[i];
				if(nx>=0 && ny>=0 && nx<col && ny<row && check[ny][nx][ck]>cv+1) {
					check[ny][nx][ck]=cv+1;
					char nc = arr[ny][nx];
					if(nc=='#') continue;
					else if(nc=='.' || nc=='0' || nc=='1') q.offer(new Info(ny,nx,cv+1,ck));
					else if('a'<=nc && nc<='f') {		//열쇠
						int idx = nc-'a';
						q.offer(new Info(ny,nx,cv+1,ck|(1<<idx)));
					}
					else if('A'<=nc && nc<='F'){
						int idx = nc-'A';
						if((ck & (1<<idx))==0) continue;
						q.offer(new Info(ny,nx,cv+1,ck));
					}
				}
			}
		}
		System.out.println(finish);
	}
}
