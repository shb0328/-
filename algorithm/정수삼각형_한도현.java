//방법 1 (bottom-up)
class Solution {
    public static int solution(int[][] triangle) {
        int answer = 0;
        int dp[][] = new int[triangle.length][triangle.length];
        dp[0][0] = triangle[0][0];
        for(int i=1;i< triangle.length;i++){
            for(int j=0;j<i+1;j++){
                if(j==0){
                    dp[i][j] = dp[i-1][j] + triangle[i][j];
                }else if(j==i){
                    dp[i][j] = dp[i-1][j-1] + triangle[i][j];
                }else{
                    dp[i][j] = Math.max(dp[i-1][j-1],dp[i-1][j]) + triangle[i][j];
                }
            }
        }
        for(int i=0;i< triangle.length;i++){
            answer = Math.max(answer, dp[triangle.length-1][i]);
        }
        return answer;
    }
}

//방법 2 (top-down)
class Solution {
    public static int dp[][];
    public static int triangles[][];
    public static int dfs(int y,int x){
        if(y==triangles.length-1) return dp[y][x] = triangles[y][x];
        if(dp[y][x] != -1) return dp[y][x];

        return dp[y][x] = Math.max(dfs(y+1,x),dfs(y+1,x+1)) + triangles[y][x];
    }
    public static int solution(int[][] triangle) {
        int answer = 0;
        int N = triangle.length;
        dp = new int[N][N];
        triangles = new int[N][N];
        for(int i=0;i<N;i++){
            for(int j=0;j<N;j++){
                dp[i][j] = -1;
            }
        }
        for(int i=0;i<N;i++){
            for(int j=0;j<i+1;j++){
                triangles[i][j] = triangle[i][j];
            }
        }
        answer = dfs(0,0);
        return answer;
    }
}
