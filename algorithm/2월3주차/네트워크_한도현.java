import java.util.*;
class Solution {
    public static int par[];
    public static int find(int x){
        if(par[x] == x) return x;
        return par[x] = find(par[x]);
    }
    public static void union(int x, int y){
        int a = find(x);
        int b = find(y);
        if(a!=b){
            par[a] = b;
        }
    }
    public static int solution(int n, int[][] computers) {
        int answer = 0;
        ArrayList<Integer> adj[] = new ArrayList[n];
        par = new int[n];

        for(int i=0;i<n;i++){
            adj[i] = new ArrayList<Integer>();
            par[i] = i;
        }
        for(int i=0;i<computers.length;i++){
            for(int j=i+1;j<computers[i].length;j++){
                if(computers[i][j] == 1){
                    union(i,j);
                }
            }
        }
        boolean isCounted[] = new boolean[n];
        for(int i=0;i<n;i++){
            int parent = find(i);
            if(isCounted[parent]) continue;
            isCounted[parent] = true;
            answer++;
        }
        return answer;
    }
}
