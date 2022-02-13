import java.io.*;
import java.util.*;
public class baekjoon_16964_dfs스페셜저지 {
	public static int N;
	public static HashSet<Integer> adj[];
	public static int arr[];
	public static boolean ans;
	public static int prev[];
	public static void dfs(int cur,int order){
		if(order >= N){
			ans = true;
			return;
		}
		int next = arr[order];
		if(adj[cur].contains(next)){
			prev[next] = cur;
			dfs(next,order+1);
		}else if(prev[cur]!=0){
			dfs(prev[cur],order);
		}
	}
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		adj = new HashSet[N+1];
		arr = new int[N];
		prev = new int[N+1];
		for(int i=1;i<=N;i++){
			adj[i] = new HashSet<Integer>();
		}
		StringTokenizer st;
		for(int i=0;i<N-1;i++){
			st = new StringTokenizer(br.readLine());
			int a= Integer.parseInt(st.nextToken());
			int b= Integer.parseInt(st.nextToken());
			adj[a].add(b);
			adj[b].add(a);
		}
		
		st = new StringTokenizer(br.readLine());
		for(int i=0;i<N;i++){
			arr[i] = Integer.parseInt(st.nextToken());
		}
		ans = false;
		dfs(arr[0],1);
		if(ans && arr[0] == 1){
			System.out.println(1);
		}else{
			System.out.println(0);
		}
	}

}
