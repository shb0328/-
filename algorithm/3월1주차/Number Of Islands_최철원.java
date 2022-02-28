class Solution {
    public static int dir[][] = {{-1,0},{0,1},{1,0},{0,-1}};
    public static int N,M;
    public static void dfs(char[][] grid, boolean[][] visited,int y,int x){
        for(int i=0;i<4;i++){
            int ny = y + dir[i][0];
            int nx = x + dir[i][1];
            if(ny < 0 || nx <0 || ny >= N || nx >= M) continue;
            if(grid[ny][nx] == '0' || visited[ny][nx]) continue;
            visited[ny][nx] = true;
            dfs(grid,visited,ny,nx);
        }
    }
    public static int numIslands(char[][] grid) {
        int answer = 0;
        N = grid.length;
        M = grid[0].length;
        boolean visited[][] = new boolean[N][M];

        for(int i=0;i<N;i++){
            for(int j=0;j<M;j++){
                if(visited[i][j] || grid[i][j]=='0') continue;
                visited[i][j] = true;
                answer++;
                dfs(grid,visited,i,j);
            }
        }
        return answer;
    }
}
