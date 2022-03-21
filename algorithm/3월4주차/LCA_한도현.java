import java.util.*;
import java.io.*;
public class Main {
    public static int N,M;
    public static ArrayList<Integer> adj[];
    public static int par[][];
    public static boolean visited[];
    public static int depth[];
    public static void main(String[] args)  throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        adj = new ArrayList[N+1];
        par = new int[N+1][17];
        depth = new int[N+1];
        visited = new boolean[N+1];
        for(int i=1;i<=N;i++){
            adj[i] = new ArrayList<Integer>();
            depth[i] = -1;
        }
        StringTokenizer st;
        for(int i=0;i<N-1;i++){
            int s,e;
            st = new StringTokenizer(br.readLine());
            s = Integer.parseInt(st.nextToken());
            e = Integer.parseInt(st.nextToken());
            adj[s].add(e);
            adj[e].add(s);
        }
        Queue<Integer> q = new LinkedList<Integer>();
        q.add(1);
        visited[1] = true;
        depth[1] = 0;
        par[1][0] = 1;
        while(!q.isEmpty()){
            int curNode = q.poll();
            for(int i=0;i<adj[curNode].size();i++){
                int nextNode = adj[curNode].get(i);
                if(visited[nextNode]) continue;
                visited[nextNode] = true;
                par[nextNode][0] = curNode;
                depth[nextNode] = depth[curNode] + 1;
                q.add(nextNode);
            }
        }
        for(int i=1;i<=16;i++){
            for(int j=1;j<=N;j++){
                par[j][i] = par[par[j][i-1]][i-1];
            }
        }
        M = Integer.parseInt(br.readLine());
        for(int i=0;i<M;i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            if(depth[a]<depth[b]){
                for(int j=16;j>=0;j--){
                    while(a!=b && depth[a] <= depth[par[b][j]]){
                        b = par[b][j];
                    }
                }
            }else if(depth[a] > depth[b]){
                for(int j=16;j>=0;j--){
                    while(a!=b && depth[b] <= depth[par[a][j]]){
                        a = par[a][j];
                    }
                }
            }

            for(int j=16;j>=0;j--){
                while(a!=b && par[a][j] != par[b][j]){
                    a = par[a][j];
                    b = par[b][j];
                }
            }
            if(a==b){
                System.out.println(a);
            }else{
                System.out.println(par[a][0]);
            }
        }
    }
}
