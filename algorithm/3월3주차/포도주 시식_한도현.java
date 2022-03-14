//18시50분 시작 => 18시58분 끝
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static int N;
    public static int arr[];
    public static int dp[][];
    public static int dfs(int idx, int continueCnt){
        if(idx>=N) return 0;
        if(dp[idx][continueCnt] != -1){
            return dp[idx][continueCnt];
        }
        int ret = 0;
        if(continueCnt==2){
            return dp[idx][continueCnt] = dfs(idx+1,0);
        }
        ret = Math.max(dfs(idx+1,0),dfs(idx+1,continueCnt+1)+arr[idx]);
        return dp[idx][continueCnt] = ret;
    }
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new int[N];
        dp = new int[N][3];
        for(int i=0;i<N;i++){
            arr[i] = Integer.parseInt(br.readLine());
            for(int j=0;j<3;j++){
                dp[i][j] = -1;
            }
        }
        System.out.println(dfs(0,0));
    }
}
