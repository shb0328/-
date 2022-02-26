import java.util.*;

class Solution {
    public static int answer;
    public static int[] di = {0,0,-1,1};
    public static int[] dj = {-1,1,0,0};
    
    public int solution(int n, int[][] computers) {
        answer = 0;
        for(int i=0;i<n;i++){
            if(computers[i][i]!=-1){
                bfs(i,n,computers);
                answer++;
            }
        }
        return answer;
    }
    
    public static void bfs(int i, int n, int[][] computers){
        boolean[] visited = new boolean[n];
        Queue<int[]> queue = new LinkedList<int[]>();
        queue.offer(new int[]{i,i});
        computers[i][i]=-1;
        for(int k=0;k<n;k++){
            if(visited[k]) continue;
            while(!queue.isEmpty()){
                int[] curr = queue.poll();
                
                int y = curr[0];
                int x = curr[1];
                visited[y] = true;
                for(int d=0;d<di.length;d++){
                    int ny = y + di[d];
                    int nx = x + dj[d];
                    if(ny>=0&&ny<n&&nx>=0&&nx<n&&computers[ny][nx]==1){
                        computers[ny][nx] = -1;
                        queue.offer(new int[]{ny,nx});
                    }
                }
            }
        }
        
    }
}
