import java.util.*;
import java.io.*;

class Solution {
    static class Info{
        int y,x,dir,val;
    };
    final static int dx[] = {0,1,0,-1};
    final static int dy[] = {-1,0,1,0};
    final static int MAX = 101;
    
    static int bigX,smallX,bigY,smallY;
        
    static boolean cal(int x1, int y1, int x2, int y2){
        int first = x1*MAX+y1;
        int sec = x2*MAX+y2;
        int val = first*MAX
        if()
    }
    
    public int solution(int[][] board) {
        int answer = 0;
        int row = board.length;
        int col = board[0].length;
        HashSet<Long> set = new HashSet<>();
        Queue<Info> q = new LinkedList<>();
        q.offer(new Info(0,0,1,0));
        set.add(0);
        
        while(!q.isEmpty()){
            Info ii = q.poll();
            int cx = ii.x;
            int cy = ii.y;
            int cv = ii.val;
            int cd = ii.dir;
            int sx = ii.x+dx[cd];
            int sy = ii.y+dy[cd];
            if((cx==col-1 && cy==row-1) || (sx==col-1 && sy==row-1)){
                answer = cv;
                break;
            }
            for(int i=0;i<4;i++){
                if(i==cd) continue;
                int nsx = cx+dx[i];
                int nsy = cy+dy[i];
                if(nsx>=0 && nsy>=0 && nsx<col && nsy<row && board[nsy][nsx]==0){
                    cal(nsx,nsy,cx,cy);
                    int 
                    if()
                }
            }
        }
        return answer;
    }
}
