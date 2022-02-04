class Solution {
    public static int solution(int n, int[][] results) {
        int answer = 0;
        boolean isConnected[][] = new boolean[n+1][n+1];
        for(int i=0;i<results.length;i++){
            int a = results[i][0];
            int b = results[i][1];
            isConnected[a][b] = true;
        }
        for(int i=1;i<=n;i++){
            for(int j=1;j<=n;j++){
                for(int k=1;k<=n;k++){
                    if(isConnected[j][i] && isConnected[i][k]){
                        isConnected[j][k] = true;
                    }
                }
            }
        }
        for(int i=1;i<=n;i++){
            int cnt = 0;
            for(int j=1;j<=n;j++){
                if(isConnected[i][j] || isConnected[j][i]) cnt++;
            }
            if(cnt == (n-1)) answer++;
        }
        return answer;
    }
}
