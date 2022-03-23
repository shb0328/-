import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static class Info{
        int y,x;
        public Info(int y, int x){
            this.x=x;
            this.y=y;
        }
    };
    final static int dx[]={0,1,0,-1};
    final static int dy[]={-1,0,1,0};
    static int row,col;
    static char arr[][];
    
    static void gravity(){
        for(int j=0;j<6;j++){
            String str = "";
            for(int i=11;i>=0;i--)
                if(arr[i][j]!='.')
                    str+=arr[i][j];
            int len = str.length();
            int idx = 0;
            while(idx<len){
                arr[11-idx][j]=str.charAt(idx);
                idx++;
            }
            for(int i=11-idx;i>=0;i--)
                arr[i][j]='.';
        }
    }
    
    static boolean bfs(){
        boolean check[][] = new boolean[row][col];
        boolean flag = false;
        
        for(int i=0;i<row;i++){
            for(int j=0;j<col;j++){
                char c = arr[i][j];
                if(c=='.' || check[i][j]) continue;
                check[i][j]=true;
                int cnt=1;
                Queue<Info> q = new LinkedList<>();
                Queue<Info> willExplode = new LinkedList<>();
                q.offer(new Info(i,j));
                willExplode.offer(new Info(i,j));
                
                while(!q.isEmpty()){
                    Info ii = q.poll();
                    int cx = ii.x;
                    int cy = ii.y;
                    for(int k=0;k<4;k++){
                        int nx = cx+dx[k];
                        int ny = cy+dy[k];
                        if(nx>=0 && ny>=0 && nx<col && ny<row && !check[ny][nx] && arr[ny][nx]==c){
                            check[ny][nx]=true;
                            q.offer(new Info(ny,nx));
                            willExplode.offer(new Info(ny,nx));
                        }
                    }
                }
                
                if(willExplode.size()>3){
                    while(!willExplode.isEmpty()){
                        Info ii = willExplode.poll();
                        int cx = ii.x;
                        int cy = ii.y;
                        arr[cy][cx]='.';
                    }
                    flag = true;
                }
            }
        }
        return flag;
    }
    
	public static void main (String[] args) throws java.lang.Exception {
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    int answer = 0;
	    row = 12;
	    col=6;
		String str;
		//초기화
		arr = new char[row][col];
		for(int i=0;i<row;i++){
		    str = br.readLine();
		    arr[i] = str.toCharArray();
		}
		
		while(true){
		    gravity();
		    if(bfs())   answer++;
		    else break;
		}
		System.out.println(answer);
	}
}
