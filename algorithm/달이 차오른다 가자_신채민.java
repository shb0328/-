import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Node{
	int x;
	int y;
	int key;
	Node(int x, int y, int key){
		this.x=x;
		this.y=y;
		this.key=key;
	}
}
public class Main{
	public static int N,M,cnt;
	public static char[][] map;
	public static boolean[][][] visit;
	public static int[] di= {-1,1,0,0};
	public static int[] dj= {0,0,-1,1};
	public static Queue<Node> q=new LinkedList<Node>();
	
	public static void sol(int i, int j) {
		Node tmp=null;
		cnt=0;
		while(!q.isEmpty()) {
			int size=q.size();
			for(int s=0;s<size;s++) {
				tmp=q.poll();
				int tx=tmp.x;
				int ty=tmp.y;
				if(map[tx][ty]=='1')return;
				for(int d=0;d<di.length;d++) {
					int nx=tx+di[d];
					int ny=ty+dj[d];
					int tkey=tmp.key;
					if(nx>=0&&nx<N&&ny>=0&&ny<M) {
						if(map[nx][ny]=='#')continue;
						if(map[nx][ny]>='a'&&map[nx][ny]<='f') {
							tkey|=(1<<(map[nx][ny]-'a'));
						}
						if(map[nx][ny]>='A'&&map[nx][ny]<='F') {
							if((tkey&(1<<(map[nx][ny]-'A')))==0) continue;
						}
						if(visit[nx][ny][tkey]) continue;
						visit[nx][ny][tkey]=true;
						q.offer(new Node(nx,ny,tkey));
					}
				}
			}
			cnt++;
		}
		cnt=-1;
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine());
		N=Integer.parseInt(st.nextToken());
		M=Integer.parseInt(st.nextToken());
		int startX=0,startY=0;
		map=new char[N][M];
		visit=new boolean[N][M][128];
		for(int i=0;i<N;i++) {
			map[i]=br.readLine().toCharArray();
			if(q.isEmpty()) {
				for(int j=0;j<M;j++) {
					if(map[i][j]=='0') {
						startX=i;
						startY=j;
						q.offer(new Node(startX, startY, 0));//1~6:key, 0:empty
						visit[startX][startY][0]=true;
					}
				}
			}
		}
		sol(startX,startY);
		System.out.println(cnt);
	}
}
