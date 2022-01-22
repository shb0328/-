import java.io.*;
import java.util.*;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

public class Main {
    public static int K,N,F;
    public static boolean relation[][];
    public static ArrayList<Integer> adj[];
    public static boolean visited[];

    public static void dfs(int cur, ArrayList<Integer> related){
        //cur과 연결된 애들이 현재까지의 그룹에 속할 수 있는지 확인
        for(int i=0;i<adj[cur].size();i++){
            int next = adj[cur].get(i);
            if(visited[next]) continue;
            visited[next] = true;
            boolean suc = true;
            for(int j=0;j<related.size();j++){
                int member = related.get(j);
                if(relation[member][next]==false) {
                    suc = false;
                    break;
                }
            }
            if(suc){
                related.add(next);
                dfs(next,related);
            }
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        K = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        F = Integer.parseInt(st.nextToken());
        adj = new ArrayList[N+1];
        relation = new boolean[N+1][N+1];

        for(int i=1;i<=N;i++){
            adj[i] = new ArrayList<Integer>();
        }

        for(int i=0;i<F;i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            relation[a][b] = true;
            relation[b][a] = true;
            adj[a].add(b);
            adj[b].add(a);
        }
        ArrayList<Integer> candi = new ArrayList<Integer>();
        for(int i=1;i<=N;i++){
            visited = new boolean[N+1];
            ArrayList<Integer> related = new ArrayList<Integer>();
            related.add(i);
            visited[i] = true;
            dfs(i,related);
            Collections.sort(related);
            //related가 K이상이면 성공
            if(related.size()>=K){
                if(candi.size() == 0){
                    for(int j=0;j<related.size();j++){
                        candi.add(related.get(j));
                    }
                    Collections.sort(candi);
                }else{
                    int idx = 0;
                    int isLeft = -1; // 1이면 candi 그대로, 2면 related(새로운것으)로
                    while(idx < candi.size() && idx < related.size()){
                        if(candi.get(idx) < related.get(idx)){
                            isLeft = 1;
                            break;
                        }else if(candi.get(idx) > related.get(idx)){
                            isLeft = 2;
                            break;
                        }
                        idx++;
                    }
                    if(isLeft == 2){
                        candi = new ArrayList<Integer>();
                        for(int j=0;j<related.size();j++){
                            candi.add(related.get(j));
                        }
                    }
                }
            }
        }
        if(candi.size()<K){
            System.out.println(-1);
        }else{
            for(int i=0;i<candi.size();i++){
                if(i==K) break;
                System.out.println(candi.get(i));
            }
        }
    }
}
