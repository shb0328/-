import java.util.*;
import java.io.*;
public class Main {
    public static int N,M;
    public static int []inorder;
    public static ArrayList<Integer> adj[];
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        inorder = new int[N+1];
        adj = new ArrayList[N+1];
        for(int i=1;i<=N;i++){
            adj[i] = new ArrayList<Integer>();
        }
        for(int i=0;i<M;i++){
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            adj[s].add(e);
            inorder[e]++;
        }
        PriorityQueue<Integer> pq = new PriorityQueue<Integer>();
        for(int i=1;i<=N;i++){
            if(inorder[i]==0){
                pq.add(i);
            }
        }
        StringBuilder ans = new StringBuilder();
        while(!pq.isEmpty()){
            int nodeNum = pq.poll();
            ans.append(nodeNum +" ");
            for(int i=0;i<adj[nodeNum].size();i++){
                inorder[adj[nodeNum].get(i)]--;
                if(inorder[adj[nodeNum].get(i)]==0){
                    pq.add(adj[nodeNum].get(i));
                }
            }
        }
        System.out.println(ans.toString());
    }
}
