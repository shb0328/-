class Solution {
    public static int N,M,MOD;
    public static int board[][];
    public static int dp[][];
    public static int dir[][] = {{0,1},{1,0}};

    public static boolean isBoundry(int y,int x){
        if(y<0 || x<0 || y>=N || x>=M) return false;
        return true;
    }
    public static int dfs(int y, int x){
        if(dp[y][x] != -1) return dp[y][x];
        if(y==(N-1) && x==(M-1)){
            return 1;
        }
        int answer = 0;
        for(int i=0;i<2;i++){
            int ny = y + dir[i][0];
            int nx = x + dir[i][1];
            if(!isBoundry(ny,nx)) continue;
            if(board[ny][nx]==1) continue;
            answer += dfs(ny,nx);
            answer %= MOD;
        }
        return dp[y][x] = answer;
    }
    public static int solution(int m, int n, int[][] puddles) {
        MOD = 1000000007;
        int answer = 0;
        N = n;
        M = m;
        dp = new int [N][M];
        board = new int[N][M];
        for(int i=0;i<N;i++){
            for(int j=0;j<M;j++){ ;
                dp[i][j] = -1;
            }
        }

        for(int i=0;i< puddles.length;i++){
            board[puddles[i][1]-1][puddles[i][0]-1] = 1;
        }
        answer = dfs(0,0);

        return answer;
    }
}
