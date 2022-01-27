import java.io.*;
import java.util.*;
public class Main {
	public static class Node{
		int weight;
		int value;
	}
	public static int N,K;
	public static int [][]dp;
	public static Node []arr;
	public static int dfs(int idx,int remained_weight) {
		if(idx==N+1)return 0;
		if(dp[idx][remained_weight]!=-1) {
			return dp[idx][remained_weight];
		}
		dp[idx][remained_weight] = 0;
		return dp[idx][remained_weight] = Math.max(dfs(idx+1,remained_weight),
				(arr[idx].weight <= remained_weight)?
						arr[idx].value+dfs(idx+1,remained_weight-arr[idx].weight):dp[idx][remained_weight]);
		
	}
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		dp = new int[N+1][K+1];
		arr = new Node[N+1];
		for(int i=1;i<=N;i++) {
			arr[i] = new Node();
			st = new StringTokenizer(br.readLine());
			arr[i].weight = Integer.parseInt(st.nextToken());
			arr[i].value = Integer.parseInt(st.nextToken());
			for(int j=0;j<=K;j++) {
				dp[i][j] = -1;
			}
		}
		System.out.println(dfs(1,K));
	}

}
