import java.util.*;
import java.io.*;

class Solution {
    
    static List<Integer> li[][];      //[0][]: 정방향, [1][]: 역방향
    static int canWin[];
    static int canNotWin[];
    static int conn[][];
    static int num;
    static boolean check[];
    
    static void dfs(int start, int idx, int flag){
        for(int i=0;i<li[flag][idx].size();i++){
            int next = li[flag][idx].get(i);
            if(!check[next]){
                if(flag==0) canWin[start]++;
                else canNotWin[start]++;
                check[next]=true;
                dfs(start,next,flag);
            }
        }
    }
    
    public int solution(int n, int[][] results) {
        int answer = 0;
        //초기화
        num = n;
        canWin = new int[n+1];
        canNotWin = new int[n+1];
        li = new List[2][n+1];
        conn = new int[2][n+1];
        for(int i=0;i<2;i++)
            for(int j=1;j<=n;j++)
                li[i][j] = new ArrayList<>();
        
        for(int i=0;i<results.length;i++){
            int a = results[i][0];      //승
            int b = results[i][1];      //패
            li[0][a].add(b);
            li[1][b].add(a);
            conn[0][b]++;
            conn[1][a]++;
        }
        
        for(int i=0;i<2;i++)
            for(int j=1;j<=n;j++){
                check = new boolean[n+1];
                dfs(j,j,i);
            }
                
        for(int i=1;i<=n;i++){
            int sum = canNotWin[i]+canWin[i];
            if(sum==n-1)
                answer++;
        }
        return answer;
    }
}
