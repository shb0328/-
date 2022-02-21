import java.util.*;
import java.io.*;

class Solution {
    static int dp[][];
    
    public int solution(int[][] triangle) {
        int answer = 0;
        int num = triangle.length;
        dp = new int[num][num];
        dp[0][0]=triangle[0][0];
        for(int i=0;i<num-1;i++){
            for(int j=0;j<=i;j++){
                dp[i+1][j] = Math.max(dp[i+1][j],dp[i][j]+triangle[i+1][j]);  //왼쪽
                dp[i+1][j+1] = Math.max(dp[i+1][j+1],dp[i][j]+triangle[i+1][j+1]);  //왼쪽   
            }
        }
        for(int i=0;i<num;i++)
            answer = Math.max(answer,dp[num-1][i]);
        return answer;
    }
}
