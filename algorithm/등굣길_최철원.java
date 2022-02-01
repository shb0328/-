import java.util.*;
import java.io.*;

class Solution {
    static final int MOD = 1000000007;
    
    public int solution(int m, int n, int[][] puddles) {
        int answer = 0;
        int check[][] = new int[n][m];
        int arr[][] = new int[n][m];
        
        for(int i=0;i<puddles.length;i++)
            arr[puddles[i][1]-1][puddles[i][0]-1]=1;
        for(int i=0;i<n;i++){
            if(arr[i][0]==1) break;
            else    check[i][0]=1;
        }
        for(int i=0;i<m;i++){
            if(arr[0][i]==1) break;
            else    check[0][i]=1;
        }
        for(int i=1;i<n;i++){
            for(int j=1;j<m;j++){
                if(arr[i][j]==1) continue;      //웅덩이
                int up = check[i-1][j];
                int left = check[i][j-1];
                check[i][j] = (up+left)%MOD;
            }
        }
        return check[n-1][m-1];
    }
}
