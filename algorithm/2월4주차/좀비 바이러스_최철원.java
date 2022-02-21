import java.util.*;
import java.lang.*;
import java.io.*;

public class Main {
	static class Info{
		int y,x,flag,val;
		public Info(int y, int x, int flag, int val) {
			this.y=y;
			this.x=x;
			this.flag=flag;
			this.val=val;
		}
	};
	
	static int row,col;
	static int arr[][];
	static int check[][][];
	static int cnt[];		//치료제 보유 마을,1,2,3번 바이러스 감염마을 수
	final static int dx[] = {0,1,0,-1};
	final static int dy[] = {-1,0,1,0};
	final static int MAXI = 987654321;
	static Queue<Info> q;
	
	static void bfs() {
		
		while(!q.isEmpty()) {
		    Info ii = q.poll();
		    int cx = ii.x;
		    int cy = ii.y;
		    int cf = ii.flag;
		    int cv = ii.val;
		    int v1 = check[cy][cx][1]==MAXI ? 0 : 1;
		    int v2 = check[cy][cx][2]==MAXI ? 0 : 2;
		    arr[cy][cx]=v1+v2;
		    cnt[v1+v2]++;
		    if(v1+v2==3) continue;
		    for(int i=0;i<4;i++){
		        int nx = cx+dx[i];
		        int ny = cy+dy[i];
		        if(nx>=0 && ny>=0 && nx<col && ny<row && arr[ny][nx]==0 && check[ny][nx][cf]>cv+1 && check[ny][nx][3-cf]>cv){
		            check[ny][nx][cf]=cv+1;
		            if(check[ny][nx][3-cf]==MAXI) q.offer(new Info(ny,nx,cf,cv+1));
		        }
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
		check = new int[row][col][3];
		for(int i=0;i<row;i++)
		    for(int j=0;j<col;j++)
		        for(int k=0;k<3;k++)
		            check[i][j][k]=MAXI;

		for(int i=0;i<row;i++) {
			str = br.readLine();
			st = new StringTokenizer(str);
			for(int j=0;j<col;j++) {
				int val = Integer.parseInt(st.nextToken());
				arr[i][j] = val;
				if(0<val && val<3){
				    q.offer(new Info(i,j,val,0));
				    check[i][j][val]=0;
				}
			}
		}
		bfs();
		System.out.println(cnt[1]+" "+cnt[2]+" "+cnt[3]);
	}
}
