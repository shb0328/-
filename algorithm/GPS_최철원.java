import java.util.*;
import java.io.*;

class Solution {
    
    public int solution(int n, int m, int[][] edge_list, int k, int[] gps_log) {
        //초기화
        int answer = 987654321;
        int dp[][] = new int[k][n+1];       //[a][b]: a번째 목적지에 b가 왔을 때 다른 최소 횟수
        List<Integer> li[] = new List[n+1];
        for(int i=1;i<=n;i++){
            li[i] = new ArrayList<>();
            li[i].add(i);
        }
        for(int i=0;i<m;i++){
            int a = edge_list[i][0];
            int b = edge_list[i][1];
            li[a].add(b);
            li[b].add(a);
        }
        for(int i=0;i<k;i++)
            for(int j=1;j<=n;j++)
                dp[i][j]=1001;
        dp[0][gps_log[0]]=0;
        
        for(int i=0;i<k-1;i++){
            for(int j=1;j<=n;j++){      //시작지점
                if(dp[i][j]==1001) continue;      //도달 불가
                for(int l=0;l<li[j].size();l++){    //다음 지점
                    int next = li[j].get(l);
                    int nv = 0;
                    if(gps_log[i+1]!=next) nv=1;
                    dp[i+1][next] = Math.min(dp[i+1][next],dp[i][j]+nv);
                }
            }
        }
        answer = dp[k-1][gps_log[k-1]];
        return answer == 1001 ? -1 : answer;
    }
}
