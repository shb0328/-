import java.util.*;
import java.io.*;

class Solution {
    final static int maxi = 987654321;
    final static int dx[] = {0,1,0,-1};
    final static int dy[] = {-1,0,1,0};
    
    static class Info{
        int y,x,val;
        public Info(int y, int x, int val){
            this.y=y;
            this.x=x;
            this.val=val;
        }
    };
    
    public int solution(int[][] maps) {
        int answer = -1;
        int row = maps.length;
        int col = maps[0].length;
        int check[][] = new int[row][col];
        for(int i=0;i<row;i++)
            for(int j=0;j<col;j++)
                check[i][j]=maxi;
        check[0][0]=1;
        Queue<Info> q = new LinkedList<>();
        q.offer(new Info(0,0,1));
        
        while(!q.isEmpty()){
            Info ii = q.poll();
            int cx = ii.x;
            int cy = ii.y;
            int cv = ii.val;
            if(cy==row-1 && cx==col-1){
                answer = cv;
                break;
            }
            for(int k=0;k<4;k++){
                int nx = cx+dx[k];
                int ny = cy+dy[k];
                if(nx>=0 && ny>=0 && nx<col && ny<row && maps[ny][nx]==1 && check[ny][nx]>cv+1){
                    check[ny][nx]=cv+1;
                    q.offer(new Info(ny,nx,cv+1));
                }
            }
        }
        return answer;
    }
}
